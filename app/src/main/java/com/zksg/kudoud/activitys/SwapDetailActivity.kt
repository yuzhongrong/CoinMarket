package com.zksg.kudoud.activitys

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_network.entitys.QuoEntity
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
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.callback.SwapTransationCallback
import com.zksg.kudoud.callback.WalletCreateFingPrintCallback
import com.zksg.kudoud.contants.GlobalConstant.MMKV_SWAP_STATE
import com.zksg.kudoud.dialogs.CreateWalletfingprintDialog
import com.zksg.kudoud.entitys.SwapStateEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.utils.GsonUtil
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.widgets.CircularProgressBarCountDown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwapDetailActivity :BaseDialogActivity() {
    var mSwapDetailsViewModel: SwapDetailsViewModel? = null
    var sharedViewModel: SharedViewModel?=null
    var mSolanaAccount:SolanaAccount?=null
    var from:UiWalletToken?=null
    var to:UiWalletToken?=null
    var fromamount:String?=null
    override fun initViewModel() {
        mSwapDetailsViewModel = getActivityScopeViewModel(SwapDetailsViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_swap_detail, BR.vm, mSwapDetailsViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.listener, listener)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitObsever()
        InitData()
    }

    private fun InitObsever(){
        mSwapDetailsViewModel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        mSwapDetailsViewModel!!.signatureOnChain.observe(this){
            //提交完成,获取tx 并且打印出来
            Log.d("------submit tx complete---->",it)
            var mSwapStateEntity= SwapStateEntity(mSwapDetailsViewModel!!.from.value,mSwapDetailsViewModel!!.to.value,it,"processed")
            //保存最新交易到本地缓存
            MMKV.mmkvWithID(MMKV_SWAP_STATE+"_"+mSolanaAccount!!.publicKey.toBase58()).encode(MMKV_SWAP_STATE,GsonUtils.toJson(mSwapStateEntity))
            ToastUtils.showShort(R.string.str_transation_pendding)
            this.finish()
        }

    }

    private fun InitData() {
        mSolanaAccount= WalletUtils.getSolanaAccount(sharedViewModel!!,"")
         from=intent.extras?.get("from") as UiWalletToken
         to=intent.extras?.get("to") as UiWalletToken
        fromamount=intent.extras?.getString("fromamount")

        mSwapDetailsViewModel!!.from.value=from
        mSwapDetailsViewModel!!.to.value=to
        mSwapDetailsViewModel!!.from_amount.value=fromamount
        mSwapDetailsViewModel!!.wallet.set(mSolanaAccount!!.publicKey.toBase58())
        getQuo(from!!.mint,to!!.mint,fromamount!!,from!!.decimal.toInt())
    }

    private fun getQuo(fromMint:String,toMint:String,fromAmount:String,decimal:Int){
        //获取最新报价
        mSwapDetailsViewModel!!.getQuoForCallback(fromMint,toMint,fromAmount,decimal){

            //开始倒计时
            mSwapDetailsViewModel!!.countDown.set(true)
            }
    }


     fun signAndSubmitTx(mQuoEntity: QuoEntity){
         mSwapDetailsViewModel!!.loadingVisible.postValue(true)
        var mQuoPubkey58Entity= QuoPubkey58Entity(mQuoEntity,mSolanaAccount!!.publicKey.toBase58())
        mSwapDetailsViewModel!!.reqSwapTransationCallback(mQuoPubkey58Entity){
            //签名
            var signatureProvider = TweetNaclFast.Signature(ByteArray(0), mSolanaAccount!!.secretKey)
            var signature = signatureProvider.detached(Base58.decode(it.msgserialize))
            var signature58 = Base58.encode(signature)
            Log.d("----tx-sign--->", signature58)

            //提交交易
            var mSubmmitVerTxReqBodyEntity= SubmmitVerTxReqBodyEntity(it.swap64,it.lastValidBlockHeight,mSolanaAccount!!.publicKey.toBase58(),signature58)
            mSwapDetailsViewModel!!.submmitSwapTx(mSubmmitVerTxReqBodyEntity)
        }


    }

    var listener= CircularProgressBarCountDown.OnCountDownFinishListener {
        Log.d("----count down--->","OnCountDownFinishListener")
        mSwapDetailsViewModel!!.countDownFinish.set(true)
        mSwapDetailsViewModel!!.countDown.set(false)

        //还原标识
//        meViewModel!!.startCirc.set(false)
//        meViewModel!!.getQuo(meViewModel!!.from.value!!.mint,meViewModel!!.to.value!!.mint,tempInputAmount,meViewModel!!.from.value!!.decimal.toInt())
    }



    inner class ClickProxy {





        fun startSwap(){
            if(mSwapDetailsViewModel!!.countDownFinish.get()==true){//报价过期
                mSwapDetailsViewModel!!.countDownFinish.set(false)
                getQuo(from!!.mint,to!!.mint,fromamount!!,from!!.decimal.toInt())

            }else{
                VerfityUser()
            }


        }

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
                        var quo= mSwapDetailsViewModel!!.quo.value
                        if(quo==null){
                            ToastUtils.showShort(getString(R.string.str_notget_quo))
                            return
                        }
                        signAndSubmitTx(quo)
                    }

                    override fun walletCreateFingPrintFail(errcode: Int) {

                    }


                }))
                .show()

        }

    }
}