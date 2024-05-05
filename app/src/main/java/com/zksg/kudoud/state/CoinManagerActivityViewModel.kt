package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.entitys.JubToken
import com.zksg.kudoud.entitys.UiWalletToken

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CoinManagerActivityViewModel : ViewModel() {
    @JvmField
    var localdatas = MutableResult<List<UiWalletToken>>()
    @JvmField
    var hotdatas = MutableResult<List<UiWalletToken>>()
}