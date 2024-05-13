package com.zksg.kudoud.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.JupToken
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SendCoinNumberdapter
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CusAddCoinActivityViewmodel
import com.zksg.kudoud.state.SendCoinActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.constants.Constants
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import java.math.BigDecimal

class SendCoinActivity : BaseDialogActivity() {
    var token: UiWalletToken? = null
    var mSendCoinActivityViewmodel: SendCoinActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    var adapter:SendCoinNumberdapter?=null
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
            .addBindingParam(BR.inputCoinNumber, inputCoinNumber)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

        mSendCoinActivityViewmodel!!.numberText.observe(this){

            if(TextUtils.isEmpty(it)||it.equals("0.")||BigDecimal(it).toDouble()==0.0){
                mSendCoinActivityViewmodel!!.isapass.set(false)
                return@observe
            }

            //余额-输入的数值<0.001的话无法 保证账号租金 不让转账
            var balancebigdecimal=BigDecimal("0.01")
            var inputvalue=BigDecimal(it)
            //
            if(mSendCoinActivityViewmodel!!.currentToken.get()!!.mint.equals(TOKEN_SOL_CONTRACT)){
                var calif=balancebigdecimal.subtract(inputvalue).toDouble() //余额扣除你所需要转的钱>0.001
                if(calif>0.001){//有余额的前提下给予提示
                    mSendCoinActivityViewmodel!!.isapass.set(true)
                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }

            }else{
                if(balancebigdecimal.toDouble()<inputvalue.toDouble()){ //spL代币不足
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(true)
                }

            }


        }

        token = intent.getSerializableExtra("token") as UiWalletToken?
        token?.let { mSendCoinActivityViewmodel!!.currentToken.set(it) }
        adapter!!.submitList(listOf("1","2","3","4","5","6","7","8","9",".","0","\u232B"))
        mSendCoinActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }


    }



    inner class ClickProxy {

        fun close() {
            finish()

        }

        fun sendCoinNext(){


        }

        fun actionMax(){
           var balance= mSendCoinActivityViewmodel!!.currentToken.get()!!.balance
            var mint=mSendCoinActivityViewmodel!!.currentToken.get()!!.mint
            var balanceBig=BigDecimal(balance)
            var inputValue=mSendCoinActivityViewmodel!!.numberText.value
            var inputValueBig=BigDecimal(inputValue)
            if(balanceBig.toDouble()>0){
                //判断是不是sol
                if(mint.equals(TOKEN_SOL_CONTRACT)){
                    if(balanceBig.toDouble()<0.001){
                        ToastUtils.showShort(getString(R.string.str_transfor_tip))
                    }else{
                        mSendCoinActivityViewmodel!!.numberText.postValue(balanceBig.subtract(BigDecimal(0.001)).toString())
                    }

                }else{
                    mSendCoinActivityViewmodel!!.numberText.postValue(balance)
                }


            }else{
                ToastUtils.showShort(getString(R.string.str_balance_not))
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

                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }
            }

        }
        override fun afterTextChanged(s: Editable) {

        }
    }


    private val inputCoinNumber: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

       override fun afterTextChanged(s: Editable) {}
        }











}