package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo
import com.netease.lib_network.entitys.DexScreenTokenInfo.PairsDTO
import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter
import com.zksg.kudoud.customviews.NoTouchScrollViewpager
import com.zksg.kudoud.entitys.Base2QuoEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Comparator
import java.util.stream.Collectors
import com.kunminx.architecture.ui.state.State

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class Kline2OrderActivityViewModel : BaseLoadingViewModel() {

    //加载webview的stringhtml
    @JvmField
    var htmlStr=ObservableField<String>()
    @JvmField
    var progress=ObservableField<Int>()


}