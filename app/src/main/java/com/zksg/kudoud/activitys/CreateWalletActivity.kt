package com.zksg.kudoud.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.WalletCreateCallback
import com.zksg.kudoud.state.CreateWalletActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import java.lang.Exception

class CreateWalletActivity : BaseDialogActivity() {

    var mCreateWalletActivityViewmodel: CreateWalletActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        mCreateWalletActivityViewmodel = getActivityScopeViewModel(
            CreateWalletActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

//        mCreateWalletActivityViewmodel?.getBalance()
        mCreateWalletActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

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

        fun createWallet(){
            var name=mCreateWalletActivityViewmodel?.walletname?.get()
            var pwd=mCreateWalletActivityViewmodel?.walletpwd?.get()
            var pwdConfirm=mCreateWalletActivityViewmodel?.walletconfirmpwd?.get()
            if(TextUtils.isEmpty(name)){
                ToastUtils.showShort(getString(R.string.str_wallet_name_notnull))
                return
            }
            if(TextUtils.isEmpty(pwd)){
                ToastUtils.showShort(getString(R.string.str_invalid_pwd))
                return
            }
            if(TextUtils.isEmpty(pwdConfirm)){
                ToastUtils.showShort(getString(R.string.str_invalid_pwd))
                return
            }

            if(!pwd!!.equals(pwdConfirm)){
                ToastUtils.showShort(getString(R.string.str_pwd_not_same))
                return
            }

            mCreateWalletActivityViewmodel?.createWallet(object : WalletCreateCallback {
                override fun walletCreateComplete(keyAlias: String?) {
                    if(!TextUtils.isEmpty(keyAlias)){
                        runOnUiThread {
                            ToastUtils.showShort(getString(R.string.str_wallet_create_success))
                            close()
                        }

                    }
                }
                override fun walletCreateFail(err: Exception?) {
                    runOnUiThread {

                        ToastUtils.showShort(getString(R.string.str_wallet_create_fail))
                        close()
                    }

                }

            })


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