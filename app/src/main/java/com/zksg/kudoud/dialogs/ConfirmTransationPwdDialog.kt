package com.zksg.kudoud.dialogs

import android.app.Activity
import android.os.Build
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ToastUtils
import com.lxj.xpopup.core.BottomPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.state.SendCoinConfirmActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.WalletUtils

class ConfirmTransationPwdDialog(context: Activity,mSharedViewModel: SharedViewModel,mSendCoinConfirmActivityViewmodel: SendCoinConfirmActivityViewmodel) : BottomPopupView(context) {

    private var mcontext=context
    private var eyeshow=false
    private var mSharedViewModel=mSharedViewModel
    private var mSendCoinConfirmActivityViewmodel=mSendCoinConfirmActivityViewmodel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        var bt_edit_pwd=findViewById<EditText>(R.id.edit_input_pwd)
        var bt_confirm_pwd=findViewById<Button>(R.id.bt_confirm_pwd)

        var eye=findViewById<ImageView>(R.id.eye)

//        bt_confirm_pwd.setOnClickListener { this.delayDismiss(500) }


        eye.setOnClickListener {
            (it as ImageView).let {
                if (eyeshow) {
                    eyeshow=false
                    it.setImageResource(R.mipmap.ic_eye_show)
                    bt_edit_pwd.setTransformationMethod(null)
                } else {
                    eyeshow=true
                    it.setImageResource(R.mipmap.ic_eye_hide)
                    bt_edit_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance())
                }
            }

        }


        bt_confirm_pwd.setOnClickListener {

            var pwd=bt_edit_pwd.text.toString()
            if(!TextUtils.isEmpty(pwd.trim())){
                var  solanaAccount= WalletUtils.getSolanaAccount(mSharedViewModel!!,pwd!!)
                if(solanaAccount==null){//密码不正确
                    ToastUtils.showShort(context.getString(R.string.str_pwd_not_ok))
                }else{//密码正确
                    mSendCoinConfirmActivityViewmodel.getLastBlockHash(solanaAccount)
                    this.dismiss()
                }
            }else{
                ToastUtils.showShort(context.getString(R.string.str_pwd_not_ok))
            }



        }


    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_transation_pwd
    }
}