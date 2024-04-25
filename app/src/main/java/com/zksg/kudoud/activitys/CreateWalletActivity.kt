package com.zksg.kudoud.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.state.CreateWalletActivityViewmodel

class CreateWalletActivity : BaseActivity() {
    var mCreateWalletActivityViewmodel: CreateWalletActivityViewmodel? = null
    override fun initViewModel() {
        mCreateWalletActivityViewmodel = getActivityScopeViewModel(
            CreateWalletActivityViewmodel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_create_wallet,
            BR.vm,
            mCreateWalletActivityViewmodel!!
        ).addBindingParam(BR.click, ClickProxy())
         .addBindingParam(BR.WalletnameTextWatcher, WalletnameTextWatcher)
            .addBindingParam(BR.WalletPwdTextWatcher, WalletPwdTextWatcher)
            .addBindingParam(BR.WalletConfirmPwdTextWatcher, WalletConfirmPwdTextWatcher)
    }



    inner class ClickProxy {

        fun close() {
            finish()
        }

        fun ShowEye(){
           var result= mCreateWalletActivityViewmodel!!.pwd.get()
            if(result==false){
                mCreateWalletActivityViewmodel!!.pwd.set(true)
            }else{
                mCreateWalletActivityViewmodel!!.pwd.set(false)
            }
        }

        fun  ShowEyeConfirm(){
            var result= mCreateWalletActivityViewmodel!!.pwdConfirm.get()
            if(result==false){
                mCreateWalletActivityViewmodel!!.pwdConfirm.set(true)
            }else{
                mCreateWalletActivityViewmodel!!.pwdConfirm.set(false)
            }

        }

    }

    private val WalletnameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var walletName=s.toString().trim()
                mCreateWalletActivityViewmodel?.walletname?.set(walletName)
            }
        }
    }


    private val WalletPwdTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var walletName=s.toString().trim()
                mCreateWalletActivityViewmodel?.walletpwd?.set(walletName)
            }
        }
    }


    private val WalletConfirmPwdTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var walletName=s.toString().trim()
                mCreateWalletActivityViewmodel?.walletconfirmpwd?.set(walletName)
            }
        }
    }




}