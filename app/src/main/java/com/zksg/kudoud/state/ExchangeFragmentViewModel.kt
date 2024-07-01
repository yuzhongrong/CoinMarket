package com.zksg.kudoud.state



import android.text.TextUtils
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.entitys.QuoEntity
import com.netease.lib_network.entitys.QuoPubkey58Entity
import com.netease.lib_network.entitys.ReqSwapTransation
import com.netease.lib_network.entitys.SubmmitVerTxReqBodyEntity
import com.zksg.kudoud.callback.QuoGasCallback
import com.zksg.kudoud.callback.TokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.DigitUtils
import com.zksg.kudoud.utils.WalletUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class ExchangeFragmentViewModel : BaseLoadingViewModel() {
    @JvmField
    var hasSelectWallet = MutableResult(false)

    @JvmField
    var from = MutableResult<UiWalletToken>()

    @JvmField
    var to = MutableResult<UiWalletToken>()


    @JvmField
    var from_amount = MutableResult("0") //默认from amount=1


    @JvmField
    var to_amount = MutableResult(0) //默认from amount=1
    @JvmField
    var from_wallet_amount = MutableResult("0.0") //默认from amount=1
    @JvmField
    var to_wallet_amount = MutableResult("0.0") //默认to amount=1

    @JvmField
    var swap_out_amount=MutableResult("0.0")

    @JvmField
    var quo=MutableResult<QuoEntity>()

    @JvmField
    var quosolfee=MutableResult("0.0")

    @JvmField
    var mTransaction=MutableResult<ReqSwapTransation>()
    //账号租金 一般账号默认0.00089088 账号租金
    @JvmField
    var AccountRent= ObservableField("0.00089088")

    //默认显示余额不足
    @JvmField
    var insufficient_sol_balance=MutableResult(false)

    @JvmField
    var startAnimation=ObservableField(false)


    @JvmField
    var startCirc=ObservableField(false)

    fun getQuo(from: String,to: String,amount:String,fromdecimal: Int){
        viewModelScope.launch {
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getQuo(from,to,amount,fromdecimal){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getQuo-->",GsonUtil.toJson(it.result.data))
                        if(it.result!=null){
                            quo.postValue(it.result.data)
                            postRouterFee(it.result.data)
                            //开始倒计时循环
//                            startCirc.set(true)
//                            loadingVisible.postValue(false)
//                            var mQuoPubkey58Entity= QuoPubkey58Entity(it.result.data,"2uhu96aU75jbiMzLoguvHmADY39rb3q84qBwJqyPhpzh")
//                            reqSwapTransation(mQuoPubkey58Entity)
                        }

                    }
                }
            }

        }
    }


    fun getNetworkGas(feeMints:String,callback:QuoGasCallback){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getNetworkGas(feeMints){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getNetworkGas-->",GsonUtil.toJson(it.result.data))
                        val tokenData: Map<String, TokenInfo> = parseJson(it.result.data)
                        callback.postQuoGas(tokenData)

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
                        Log.d("----postRouterFee-->",BigDecimal(it.result.data).toPlainString())
                        quosolfee.postValue(DigitUtils.formatNumberWithCommas(BigDecimal(it.result.data).toDouble(),9))

                    }
                }
            }

        }
    }


    fun reqSwapTransation(quopubkey58: QuoPubkey58Entity){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.getInstance().reqSwapTransation(quopubkey58){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null){
                            mTransaction.postValue(it.result.data)
                        }

                    }
                }
            }

        }
    }

    fun submmitSwapTx(body: SubmmitVerTxReqBodyEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.getInstance().submmitSwapTx(body){
                    if(it.responseStatus.isSuccess){
//                        Log.d("----submmitSwapTx-->",it.result.data)
//                        if(!TextUtils.isEmpty(it.result.data)){
//
//                        }

                    }
                }
            }

        }
    }



    fun parseJson(jsonString: String): Map<String, TokenInfo> {
        val gson = Gson()
        val type = object : TypeToken<Map<String, TokenInfo>>() {}.type
        return gson.fromJson(jsonString, type)
    }



    fun getRentForAccount(wallet: String){
        viewModelScope.launch {

            withContext(Dispatchers.IO){
                DataRepository.getInstance().getRentForAccount(wallet){
                    if(it.result!=null){
                        Log.d("----getRentForAccount---->",it.result.data)
                        AccountRent.set(it.result.data)

                    }
                }

            }

        }

    }


}


