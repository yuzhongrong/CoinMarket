package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo1
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter
import com.zksg.kudoud.state.load.BaseLoadingViewModel

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class Kline2OrderActivityViewModel : BaseLoadingViewModel() {

    //加载webview的stringhtml
//    @JvmField
//    var htmlStr=ObservableField<String>()
//    @JvmField
//    var progress=ObservableField<Int>()
    @JvmField
    var htmlStr = ObservableField<String>()
    @JvmField
    var progress=ObservableField<Int>()
    @JvmField
    var symbol=ObservableField<String>()
    @JvmField
    var tabAdapter = ObservableField<SimpleFragmentPagerAdapter>()
    @JvmField
    var indicatorTitle = ObservableField(arrayOf("池子", "安全", "简介"))
    @JvmField
    var viewpagerid = ObservableField(R.id.view_pager_kline)

    var pairs=MutableResult<List<DexScreenTokenInfo1.PairsDTO>>()
    @JvmField
    var loadFinish=ObservableField(false)



}