package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.entitys.UiWalletToken
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections
import java.util.stream.Collectors

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class TransationHistorysActivityViewModel : BaseLoadingViewModel() {

    @JvmField
    var walletAddress = MutableResult<String>("")

    @JvmField
    var localdatas = MutableResult<List<UiWalletToken>>()
    @JvmField
    var historys = MutableResult(listOf<TransationHistoryEntity>())
    @JvmField
    var isfinishRefresh=MutableResult(false)

    //没有更多数据
   var nomoredata=ObservableField(false)

    //提供展示搜索内容用的=localdatas+hotdatas
    @JvmField
    var amountdatas = MutableResult<List<UiWalletToken>>()
    //提供给搜索用的初始化还原数据源
    @JvmField
    var amountdatascache = MutableResult<List<UiWalletToken>>()
    //clear all to show and click to set ""
    @JvmField
    var clearAll=State(false)
    @JvmField
    var empty=State("")


    fun getAllHistorys(wallet: String,before:String,isShowDialog:Boolean){
        viewModelScope.launch {
            if(isShowDialog)loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getAllHistorys(wallet,before){
                    if(it.result!=null){
                       var plusResult= historys.value?.plus(it.result.data)
                        historys.postValue(plusResult)
                        if(isShowDialog)loadingVisible.postValue(false)
                    }
                    isfinishRefresh.postValue(true)
                }


            }

        }

    }




}