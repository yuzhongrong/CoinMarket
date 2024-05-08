package com.zksg.kudoud.state

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.entitys.UiWalletToken
import com.kunminx.architecture.ui.state.State

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CoinManagerActivityViewModel : ViewModel() {
    @JvmField
    var localdatas = MutableResult<List<UiWalletToken>>()
    @JvmField
    var hotdatas = MutableResult<List<UiWalletToken>>()
    @JvmField
    var showSearchLayout=State(false)
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



    fun  getNewAmountCacheData():List<UiWalletToken>{
        var newCacheData=arrayListOf<UiWalletToken>()
        newCacheData.addAll(localdatas.value!!)
        newCacheData.addAll(hotdatas.value!!)
        return newCacheData
    }




}