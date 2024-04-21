package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.zksg.kudoud.callback.WebViewClientCallback
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.entitys.WebViewParmarsEntity

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
    var callback=ObservableField<WebViewClientCallback>()
    @JvmField
    var progress=ObservableField<Int>()

}