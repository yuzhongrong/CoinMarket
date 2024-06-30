package com.zksg.kudoud.activitys

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.SendVerifyActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import java.util.*

class SendVerifyPwdActivity : BaseDialogActivity() {

    var mSendVerifyActivityViewmodel: SendVerifyActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    var sender: String? = null
    var receiver: String? = null
    var number: String? = null
    var token: UiWalletToken?=null
    var rent: String? = null
    var sol: UiWalletToken? = null


    override fun initViewModel() {
        mSendVerifyActivityViewmodel = getActivityScopeViewModel(
            SendVerifyActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_verify_pwd,
            BR.vm,
            mSendVerifyActivityViewmodel!!
        ).addBindingParam(BR.click, ClickProxy()).addBindingParam(BR.WalletPwdTextWatcher, WalletPwdTextWatcher)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){
        mSendVerifyActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        sender = intent.getStringExtra("sender")
        receiver = intent.getStringExtra("receiver")
        number= intent.getStringExtra("number")
        token= intent.getSerializableExtra("token") as UiWalletToken?
        sol= intent.getSerializableExtra("sol") as UiWalletToken?
        rent=intent.getStringExtra("rent")


    }



    inner class ClickProxy {

        fun close() {
            finish()

        }

        fun ShowEye(){
           var result= mSendVerifyActivityViewmodel!!.pwd.get()
            if(result==false){
                mSendVerifyActivityViewmodel!!.pwd.set(true)
            }else{
                mSendVerifyActivityViewmodel!!.pwd.set(false)
            }
        }

        fun  ShowEyeConfirm(){
            var result= mSendVerifyActivityViewmodel!!.pwdConfirm.get()
            if(result==false){
                mSendVerifyActivityViewmodel!!.pwdConfirm.set(true)
            }else{
                mSendVerifyActivityViewmodel!!.pwdConfirm.set(false)
            }

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getSolanaWallet(){
            var pwd=mSendVerifyActivityViewmodel?.walletpwd?.get()
            if(TextUtils.isEmpty(pwd)){
                ToastUtils.showShort(getString(R.string.str_invalid_pwd))
                return
            }

            try {

//
//                if(solanaAccount!=null){//验证成功密码是ok的
//
//                    IntentUtils.openIntent(
//                        this@SendVerifyPwdActivity,
//                        Intent(
//                            this@SendVerifyPwdActivity,
//                            SendCoinConfirmActivity::class.java
//                        ).putExtra("receiver",receiver)
//                            .putExtra("sender",sender)
//                            .putExtra("number",number)
//                            .putExtra("token",token)
//                            .putExtra("rent",rent)
//                            .putExtra("sol",sol)
//
//
//                    )
//                }
            }catch (e:Exception){


                ToastUtils.showShort(getString(R.string.str_pwd_not_ok))
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
                mSendVerifyActivityViewmodel?.walletpwd?.set(walletName)
            }
        }
    }






}