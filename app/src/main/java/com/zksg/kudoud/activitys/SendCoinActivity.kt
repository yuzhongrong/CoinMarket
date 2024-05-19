package com.zksg.kudoud.activitys

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SendCoinNumberdapter
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.SendCoinActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import com.zksg.kudoud.wallet.data.SolanaAccount
import java.math.BigDecimal

class SendCoinActivity : BaseDialogActivity() {
    var token: UiWalletToken? = null
    var sol:UiWalletToken?=null
    var wallet:SimpleWallet?=null
    var mSendCoinActivityViewmodel: SendCoinActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    var adapter:SendCoinNumberdapter?=null
    var solanaAccount:SolanaAccount?=null

    override fun initViewModel() {
        mSendCoinActivityViewmodel = getActivityScopeViewModel(
            SendCoinActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        adapter=SendCoinNumberdapter(this)
        return DataBindingConfig(R.layout.activity_sendcoin_input, BR.vm, mSendCoinActivityViewmodel!!)
            .addBindingParam(BR.adapter, adapter!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.contractNameTextWatcher, contractNameTextWatcher)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

        wallet= WalletUtils.getCurrentSimpleWallet(mSharedViewModel!!)
        token = intent.getSerializableExtra("token") as UiWalletToken?
        sol= intent.getSerializableExtra("sol") as UiWalletToken?


        mSendCoinActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        //订阅关闭这个页面的通知
        mSharedViewModel!!.fristPageClose.observe(this){
            this.finish()
        }
        mSendCoinActivityViewmodel!!.numberText.observe(this){
            //这里负责判断校验
            var mint=token!!.mint
            if(mint.equals(TOKEN_SOL_CONTRACT)){//sol的情况需要考虑到租金rent的费用
                var numberText=mSendCoinActivityViewmodel!!.numberText.value
                var numberTextBig= BigDecimal(numberText)
                var balanceBig=BigDecimal(token!!.balance)
                var rent=mSendCoinActivityViewmodel!!.AccountRent.get()
                if(rent.equals("0.0")){
                    ToastUtils.showShort(getString(R.string.str_invalidate_rent))
                    return@observe
                }
                var maxbalance=balanceBig.subtract(BigDecimal(rent))
                if(numberTextBig.toDouble()>0&&numberTextBig.toDouble()<=maxbalance.toDouble()){
                    if(mSendCoinActivityViewmodel!!.iscontractpass.get()==true){
                        mSendCoinActivityViewmodel!!.isapass.set(true)
                    }
                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }

            }else{//spl token的话 直接满足  0<input<=balance 即可
                var numberText=mSendCoinActivityViewmodel!!.numberText.value
                var numberTextBig=BigDecimal(numberText)
                var balanceBig=BigDecimal(token!!.balance)
                //spl token 只要 0<input<balance
                if(numberTextBig.toDouble()>0&&numberTextBig.toDouble()<=balanceBig.toDouble()){
                    if(mSendCoinActivityViewmodel!!.iscontractpass.get()==true){
                        mSendCoinActivityViewmodel!!.isapass.set(true)
                    }
                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }

            }


        }


        if(token!!.mint.equals(TOKEN_SOL_CONTRACT)){
            mSendCoinActivityViewmodel!!.AccountRentShow.set(true)
        }

        //spl和sol转账都要获取租金-转spl-token需要扣除手续费，如果账号sol太低 扣除手续费都不够租金的情况呢
        //这里只不过转spl-token不显示租金而已
        mSendCoinActivityViewmodel!!.getRentForAccount(wallet!!.address)


        token?.let { mSendCoinActivityViewmodel!!.currentToken.set(it) }
        adapter!!.submitList(listOf("1","2","3","4","5","6","7","8","9",".","0","\u232B"))



    }



    inner class ClickProxy {

        fun close() {
            finish()

        }

        fun sendCoinNext(){
            //这里发送前再次校验下 地址和数量
            if(mSendCoinActivityViewmodel!!.iscontractpass.get()!=true||mSendCoinActivityViewmodel!!.isapass.get()!=true){
                ToastUtils.showShort(getString(R.string.str_contract_address_err))
                return
            }






            IntentUtils.openIntent(
                this@SendCoinActivity,
                Intent(
                    this@SendCoinActivity,
                    SendCoinConfirmActivity::class.java
                ).putExtra("receiver",mSendCoinActivityViewmodel!!.contract.get())
                    .putExtra("sender",wallet!!.address)
                    .putExtra("number",mSendCoinActivityViewmodel!!.numberText.value)
                    .putExtra("token",mSendCoinActivityViewmodel!!.currentToken.get())
                    .putExtra("rent",mSendCoinActivityViewmodel!!.AccountRent.get())
                    .putExtra("sol",sol)

            )

        }

        fun isSol(mint:String):Boolean{
            if(!TextUtils.isEmpty(mint)&&mint.equals(TOKEN_SOL_CONTRACT)){
                return true
            }
            return false
        }

        fun actionMax(){



            //必须先输入合约
            if(mSendCoinActivityViewmodel!!.iscontractpass.get()==false){
                ToastUtils.showShort(getString(R.string.str_input_receiver_address))
                return
            }
            var balance= mSendCoinActivityViewmodel!!.currentToken.get()!!.balance
            var rent=mSendCoinActivityViewmodel!!.AccountRent.get()

            if(rent.equals("0.0")){
                ToastUtils.showShort(getString(R.string.str_invalidate_rent))
                return
            }
            if(isSol(token!!.mint)){
               var result= BigDecimal(balance).subtract(BigDecimal(rent))
                if(result.toDouble()<=0.0){
                    ToastUtils.showShort(getString(R.string.str_not_2_pay))
                }else{
                    mSendCoinActivityViewmodel!!.numberText.postValue(result.toString())
                }
            }else{
                mSendCoinActivityViewmodel!!.numberText.postValue(balance)
            }

        }



    }

    private val contractNameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var contract=s.toString().trim()
                if(contract.length==44){//粘贴合约上来的时候去请求
                    mSendCoinActivityViewmodel!!.iscontractpass.set(true)
                    mSendCoinActivityViewmodel!!.contract.set(contract)
                }else{
                    mSendCoinActivityViewmodel!!.iscontractpass.set(false)
                }
            }else{
                mSendCoinActivityViewmodel!!.iscontractpass.set(false)
            }

        }
        override fun afterTextChanged(s: Editable) {

        }
    }













}