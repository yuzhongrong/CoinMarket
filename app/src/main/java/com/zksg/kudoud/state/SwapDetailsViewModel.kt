package com.zksg.kudoud.state

import android.text.TextUtils
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.entitys.QuoEntity
import com.netease.lib_network.entitys.QuoPubkey58Entity
import com.netease.lib_network.entitys.ReqSwapTransation
import com.netease.lib_network.entitys.SubmmitVerTxReqBodyEntity
import com.zksg.kudoud.callback.QuoCallback
import com.zksg.kudoud.callback.SwapTransationCallback
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.DigitUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class SwapDetailsViewModel : BaseLoadingViewModel() {
//    public ObservableField<List<FeedTip>> datas=new ObservableField<>();

    @JvmField
    var from = MutableResult<UiWalletToken>()

    @JvmField
    var to = MutableResult<UiWalletToken>()

    @JvmField
    var mTransaction=MutableResult<ReqSwapTransation>()

    @JvmField
    var from_amount = MutableResult("0") //默认from amount=1
    @JvmField
    var quo=MutableResult<QuoEntity>()

    //最终返回的兑换链上交易id
    @JvmField
    var signatureOnChain=MutableResult("")

    @JvmField
    var quosolfee=MutableResult("0.0")


    @JvmField
    var wallet=ObservableField("")

    //控制倒计时是否开始
    @JvmField
    var countDown=ObservableField(false)


    //控制倒计时完成后的ui
    @JvmField
    var countDownFinish=ObservableField(false)

    fun getQuoForCallback(from: String,to: String,amount:String,fromdecimal: Int,mQuoCallback: QuoCallback){
        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getQuo(from,to,amount,fromdecimal){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getQuo-->", GsonUtil.toJson(it.result.data))
                        if(it.result!=null){
                            quo.postValue(it.result.data)
                            postRouterFee(it.result.data)
                            mQuoCallback.QuoCallbackResult(it.result.data)
                            //开始倒计时循环
//                            startCirc.set(true)
                            loadingVisible.postValue(false)

                        }

                    }
                }
            }

        }

    }


    fun postRouterFee(quo:QuoEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.getInstance().postRouterFee(quo){
                    if(it.responseStatus.isSuccess){
                        Log.d("----postRouterFee-->", BigDecimal(it.result.data).toPlainString())
                        quosolfee.postValue(DigitUtils.formatNumberWithCommas(BigDecimal(it.result.data).toDouble(),9))

                    }
                }
            }

        }
    }


    fun reqSwapTransationCallback(quopubkey58: QuoPubkey58Entity, mSwapTransationCallback: SwapTransationCallback){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.getInstance().reqSwapTransation(quopubkey58){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null){
                            mTransaction.postValue(it.result.data)
                            mSwapTransationCallback.MSwapTransationCallback(it.result.data)
                        }

                    }
                }
            }

        }
    }


    fun submmitSwapTx(body: SubmmitVerTxReqBodyEntity){
        viewModelScope.launch {
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().submmitSwapTx(body){
                    if(it.responseStatus.isSuccess){
//                        Log.d("----submmitSwapTx-->",it.result.data)
//                        if(!TextUtils.isEmpty(it.result.data)){
//
//                        }
                        if(!TextUtils.isEmpty(it.result.data)){
                            signatureOnChain.value=it.result.data
                            Log.d("----tx success-->",it.result.data)
                            loadingVisible.postValue(false)
                        }

                    }else{
                        Log.d("----tx fail-->",it.responseStatus.msg)
                    }
                }
            }

        }
    }



}