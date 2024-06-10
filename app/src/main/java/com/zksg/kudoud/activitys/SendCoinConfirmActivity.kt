package com.zksg.kudoud.activitys

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.github.ajalt.reprint.core.Reprint
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_common_ui.utils.GsonUtil
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.dialogs.ConfirmTransationfingprintDialog
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.SendCoinConfirmActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.CopyUtils
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.wallet.constants.Constants
import java.math.BigDecimal


class SendCoinConfirmActivity : BaseDialogActivity() {
    var sender: String? = null
    var receiver: String? = null
    var number: String? = null
    var token:UiWalletToken?=null
    var rent: String? = null
    var sol: UiWalletToken? = null
    var mSendCoinConfirmActivityViewmodel: SendCoinConfirmActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        mSendCoinConfirmActivityViewmodel = getActivityScopeViewModel(
            SendCoinConfirmActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_send_coin_confirm,
            BR.vm,
            mSendCoinConfirmActivityViewmodel!!
        ).addBindingParam(BR.click, ClickProxy())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

        mSendCoinConfirmActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        mSendCoinConfirmActivityViewmodel!!.commit.observe(this){
            if(it!=null){
                //记录所有钱包下的交易 group:钱包地址 ,下面地址存储以key=txid
                mSharedViewModel!!.fristPageClose.postValue(true)
                ToastUtils.showShort(getString(R.string.str_transation_pendding))
                this.finish()
            }
        }

        mSendCoinConfirmActivityViewmodel!!.gas.observe(this){
            //如果余额-支出>=够网络费用+租金 就广播交易
            var payamount= BigDecimal(0)
            if(token!!.mint.equals(Constants.TOKEN_SOL_CONTRACT)){
                payamount=BigDecimal(number).add(BigDecimal(it)).add(BigDecimal(rent))
            }else{
                payamount=BigDecimal(it).add(BigDecimal(rent))
            }
            var result= BigDecimal(sol!!.balance).subtract(payamount)
            if(result.toDouble()>=0){//可以转账
                mSendCoinConfirmActivityViewmodel!!.issend.set(true)
            }else{
                mSendCoinConfirmActivityViewmodel!!.issend.set(false)
            }
            mSendCoinConfirmActivityViewmodel!!.keepsol.set(payamount.toString())
        }

        sender = intent.getStringExtra("sender")
        receiver = intent.getStringExtra("receiver")
        number= intent.getStringExtra("number")
        token= intent.getSerializableExtra("token") as UiWalletToken?
        sol= intent.getSerializableExtra("sol") as UiWalletToken?
        rent=intent.getStringExtra("rent")
        mSendCoinConfirmActivityViewmodel!!.sender.set(sender)
        mSendCoinConfirmActivityViewmodel!!.receiver.set(receiver)
        mSendCoinConfirmActivityViewmodel!!.number.set(number)
        mSendCoinConfirmActivityViewmodel!!.currentToken.set(token)
        mSendCoinConfirmActivityViewmodel!!.sol.set(sol)
        mSendCoinConfirmActivityViewmodel!!.rent.set(rent)





        if(token!!.mint.equals(Constants.TOKEN_SOL_CONTRACT)) {
            var lamports=WalletUtils.sol2lamports(number!!)
            //获取sol转账gas
            mSendCoinConfirmActivityViewmodel!!.getEstimatedFee(sender!!,receiver!!,lamports!!)
        }else{
            //获取spl转账gas
            mSendCoinConfirmActivityViewmodel!!.getSplEstimatedFee(sender!!,receiver!!,token!!.mint,BigDecimal(number!!).toLong())
        }
    }






   inner  class ClickProxy {
        fun close() {
            finish()
        }



        fun copySenderAddress(){
            CopyUtils.copyToClipboard(this@SendCoinConfirmActivity,mSendCoinConfirmActivityViewmodel!!.sender.get()!!)
            ToastUtils.showShort(getString(R.string.str_copy_success))

        }
       fun copyReceiverAddress(){
           CopyUtils.copyToClipboard(this@SendCoinConfirmActivity,mSendCoinConfirmActivityViewmodel!!.receiver.get()!!)
           ToastUtils.showShort(getString(R.string.str_copy_success))

       }

       //请求网络转账
       fun sendCoin(){
           //验证指纹
           XPopup.Builder(this@SendCoinConfirmActivity)
               .autoOpenSoftInput(false)
               .dismissOnTouchOutside(false)
               .dismissOnBackPressed(false)
               .moveUpToKeyboard(false)
               .asCustom(ConfirmTransationfingprintDialog(this@SendCoinConfirmActivity,mSharedViewModel!!,mSendCoinConfirmActivityViewmodel!!))
               .show()




//           val passwordDialog = PasswordDialogFragment()
//           passwordDialog.show(this@SendCoinConfirmActivity.supportFragmentManager, "PasswordDialogFragment")

           //2.首先获取最新区块hash

           //3.构造一个交易获取预估网络费用

           //4.广播交易
       }





    }











}