package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo1

class Chart6HLineFragmentViewModel : ViewModel(){
    //model-->xml
    @JvmField
    var mPairsDTO= ObservableField<DexScreenTokenInfo1.PairsDTO>()
    @JvmField
    var mType= ObservableField<Int>()

}