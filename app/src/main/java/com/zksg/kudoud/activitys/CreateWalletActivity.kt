package com.zksg.kudoud.activitys

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt.PromptInfo
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.WalletCreateCallback
import com.zksg.kudoud.callback.WalletCreateFingPrintCallback

import com.zksg.kudoud.dialogs.CreateWalletfingprintDialog
import com.zksg.kudoud.state.CreateWalletActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel


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


        //初始化指纹
//        val executor: Executor = ContextCompat.getMainExecutor(this)



        mCreateWalletActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }




    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private fun createPromptInfo(): PromptInfo? {
        return PromptInfo.Builder()
            .setTitle("钱包安全认证")
            .setSubtitle("创建钱包前请指纹认证")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setNegativeButtonText(" ") // 使用空格字符作为负按钮文本
            .build()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun CreateSolanaWallet(){

        mCreateWalletActivityViewmodel?.createWallet(object : WalletCreateCallback {
            override fun walletCreateComplete(keyAlias: String?) {
                if(!TextUtils.isEmpty(keyAlias)){
                    runOnUiThread {
                        ToastUtils.showShort(getString(R.string.str_wallet_create_success))
                         finish()
                    }

                }
            }
            override fun walletCreateFail(err: Exception?) {
                runOnUiThread {

                    ToastUtils.showShort(getString(R.string.str_wallet_create_fail))
                    finish()
                }

            }

        })

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

            XPopup.Builder(this@CreateWalletActivity)
                .autoOpenSoftInput(false)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .moveUpToKeyboard(false)
                .asCustom(CreateWalletfingprintDialog(this@CreateWalletActivity,object:WalletCreateFingPrintCallback{
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun walletCreateComplete(success: Int) {
                      CreateSolanaWallet()
                    }

                    override fun walletCreateFingPrintFail(errcode: Int) {

                    }


                }))
                .show()




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