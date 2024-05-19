package com.zksg.kudoud.dialogs

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.github.ajalt.reprint.core.AuthenticationFailureReason
import com.github.ajalt.reprint.core.AuthenticationListener
import com.github.ajalt.reprint.core.Reprint
import com.lxj.xpopup.core.CenterPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.state.SendCoinConfirmActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.WalletUtils

class ConfirmTransationfingprintDialog(context: Activity, mSharedViewModel: SharedViewModel, mSendCoinConfirmActivityViewmodel: SendCoinConfirmActivityViewmodel?) : CenterPopupView(context) {

    private var mcontext=context
    private var eyeshow=false
    private var mSharedViewModel=mSharedViewModel
    private var mSendCoinConfirmActivityViewmodel=mSendCoinConfirmActivityViewmodel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        var cancel=findViewById<TextView>(R.id.cancel)

//        bt_confirm_pwd.setOnClickListener { this.delayDismiss(500) }
        cancel.setOnClickListener {
            this.dismiss()
        }


        Reprint.authenticate(object : AuthenticationListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onSuccess(moduleTag: Int) {
                Log.d("---moduleTag-success--->",moduleTag.toString())
                        var  solanaAccount= WalletUtils.getSolanaAccount(mSharedViewModel!!,"")
                if(solanaAccount!=null){
                    mSendCoinConfirmActivityViewmodel!!.getLastBlockHash(solanaAccount!!)
                    dismiss()
                }

            }

            override fun onFailure(
                failureReason: AuthenticationFailureReason, fatal: Boolean,
                errorMessage: CharSequence, moduleTag: Int, errorCode: Int
            ) {
                Log.d("---moduleTag-fail--->",errorMessage.toString())
                dismiss()
            }
        })




    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_transation_fingprint
    }
}