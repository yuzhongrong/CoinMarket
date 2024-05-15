package com.zksg.kudoud.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.core.content.ContentProviderCompat.requireContext
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.JupToken
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CusAddCoinActivityViewmodel
import com.zksg.kudoud.state.SendCoinConfirmActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.CopyUtils
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.constants.Constants

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
        mSendCoinConfirmActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        mSendCoinConfirmActivityViewmodel!!.getEstimatedFee(sender!!,receiver!!,number!!)
    }



   inner  class ClickProxy {
        fun close() {
            finish()
        }



        fun copyAddress(){
//            CopyUtils.copyToClipboard(requireContext(),"xxx")
//            ToastUtils.showShort(getString(R.string.str_copy_success))
        }

       //请求网络转账
       fun sendCoin(){

       }



    }











}