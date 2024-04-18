package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo

class Chart24HLineFragmentViewModel : ViewModel(){
    //model-->xml
    @JvmField
    var mPairsDTO= ObservableField<DexScreenTokenInfo.PairsDTO>()
    @JvmField
    var mType= ObservableField<Int>()

}