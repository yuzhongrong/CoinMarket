package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.netease.lib_network.entitys.TransationHistoryEntity


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class TransationHistoryDetailActivityViewmodel : BaseLoadingViewModel() {

    var history=ObservableField<TransationHistoryEntity>()
    var wallet=ObservableField("")
}