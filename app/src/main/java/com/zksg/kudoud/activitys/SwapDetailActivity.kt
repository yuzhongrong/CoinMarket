package com.zksg.kudoud.activitys

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_network.entitys.QuoPubkey58Entity
import com.netease.lib_network.entitys.SubmmitVerTxReqBodyEntity
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.state.SwapDetailsViewModel
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.wallet.utils.TweetNaclFast
import com.paymennt.crypto.lib.Base58
import com.zksg.kudoud.callback.SwapTransationCallback
import com.zksg.kudoud.callback.WalletCreateFingPrintCallback
import com.zksg.kudoud.dialogs.CreateWalletfingprintDialog
import com.zksg.kudoud.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwapDetailActivity : BaseActivity() {
    var mSwapDetailsViewModel: SwapDetailsViewModel? = null
    var sharedViewModel: SharedViewModel?=null
    var pubkey58:String?=null
    override fun initViewModel() {
        mSwapDetailsViewModel = getActivityScopeViewModel(SwapDetailsViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_swap_detail, BR.vm, mSwapDetailsViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitData()
    }

    private fun InitData() {
        pubkey58= WalletUtils.getSolanaAccount(sharedViewModel!!,"")?.publicKey?.toBase58()
        var from=intent.extras?.get("from") as UiWalletToken
        var to=intent.extras?.get("to") as UiWalletToken
        var fromamount=intent.extras?.getString("fromamount")

        mSwapDetailsViewModel!!.from.value=from
        mSwapDetailsViewModel!!.to.value=to
        mSwapDetailsViewModel!!.from_amount.value=fromamount
        mSwapDetailsViewModel!!.wallet.set(pubkey58)
        getQuo(from.mint,to.mint,fromamount!!,from.decimal.toInt())
    }

    private fun getQuo(fromMint:String,toMint:String,fromAmount:String,decimal:Int){
        //获取最新报价
        mSwapDetailsViewModel!!.getQuoForCallback(fromMint,toMint,fromAmount,decimal){
                var solanaAccount= WalletUtils.getSolanaAccount(sharedViewModel!!,"")
                var mQuoPubkey58Entity= QuoPubkey58Entity(it,solanaAccount!!.publicKey.toBase58())
//                mSwapDetailsViewModel!!.reqSwapTransationCallback(mQuoPubkey58Entity){
//
//                    var solanaAccount= WalletUtils.getSolanaAccount(sharedViewModel!!,"")
//
//                    //签名
//                    var signatureProvider = TweetNaclFast.Signature(ByteArray(0), solanaAccount!!.secretKey)
//                    var signature = signatureProvider.detached(Base58.decode(it.msgserialize))
//                    var signature58 = Base58.encode(signature)
//                    Log.d("----tx-sign--->", signature58)
//
//                    //提交交易
//                    var mSubmmitVerTxReqBodyEntity= SubmmitVerTxReqBodyEntity(it.swap64,it.lastValidBlockHeight,solanaAccount.publicKey.toBase58(),signature58)
//                    mSwapDetailsViewModel!!.submmitSwapTx(mSubmmitVerTxReqBodyEntity)
//
//
//                }
            }
    }





    inner class ClickProxy {

        //验证用户
        fun VerfityUser(){
            XPopup.Builder(this@SwapDetailActivity)
                .autoOpenSoftInput(false)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .moveUpToKeyboard(false)
                .asCustom(CreateWalletfingprintDialog(this@SwapDetailActivity,object:
                    WalletCreateFingPrintCallback {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun walletCreateComplete(success: Int) {

                    }

                    override fun walletCreateFingPrintFail(errcode: Int) {

                    }


                }))
                .show()

        }

    }
}