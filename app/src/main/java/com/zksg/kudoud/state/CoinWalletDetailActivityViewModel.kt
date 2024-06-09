package com.zksg.kudoud.state

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.entitys.NewWalletToken
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.zksg.kudoud.callback.WalletSolBalanceCallback
import com.zksg.kudoud.callback.WalletUpdateTokensBalanceCallback
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.manager.SimpleWallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class CoinWalletDetailActivityViewModel : BaseLoadingViewModel() {
    var account = ObservableField<String>()
    @JvmField
    var rv_empter = ObservableField(true)
    @JvmField
    var nodata = ObservableField(false)
    @JvmField
    var isfinishRefresh=MutableResult(false)

    var account_show = State(View.GONE)
    @JvmField
    var walletAddress = ObservableField<String>()
    @JvmField
    var historys = MutableResult(listOf<TransationHistoryEntity>())
    @JvmField
    var next = MutableResult("")


    @JvmField
    var currentToken = ObservableField<UiWalletToken>()





    @JvmField
    var isAutoRefresh=MutableResult<Boolean>()











    fun getSplHistorys(wallet: String, mint: String,before:String,showloading:Boolean){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                if(showloading)loadingVisible.postValue(true)
                DataRepository.getInstance().getSplHistorys(wallet,mint,before){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null&&it.result.data.size>0){
                            //更新uitokenInfos列表第一个数据
                            Log.d("----apiinfo---->",GsonUtil.toJson(it.result.data))
                            var plusResult = historys.value?.plus(it.result.data)
                            historys.postValue(plusResult)
                            loadingVisible.postValue(false)
                            nodata.set(false)
                            next.postValue(plusResult!!.last().signature)
                        }else{ //请求了没有，此时不让其继续再请求
                            nodata.set(true)
                        }
                    }
                    isfinishRefresh.postValue(true)
                }
            }
        }

    }

    fun getSolHistorys(wallet: String,before:String,showloading:Boolean){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                if(showloading)loadingVisible.postValue(true)
                DataRepository.getInstance().getSolHistorys(wallet,before){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null&&it.result.data.size>0){
                            //更新uitokenInfos列表第一个数据
                            Log.d("----apiinfo---->",GsonUtil.toJson(it.result.data))
                            var plusResult = historys.value?.plus(it.result.data)
                            historys.postValue(plusResult)
                            loadingVisible.postValue(false)
                            nodata.set(false)
                            next.postValue(plusResult!!.last().signature)
                        }else{ //请求了没有，此时不让其继续再请求
                            nodata.set(true)
                        }
                    }
                    isfinishRefresh.postValue(true)
                }

            }
        }

    }



}