package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo1


class IntroduceFragmentViewModel : ViewModel(){

    //集合中第一个的PairsDTO
    @JvmField
    var pairs= ObservableField<List<DexScreenTokenInfo1.PairsDTO>>()

    @JvmField
    var website= ObservableField<String>()

    var socials=ObservableField<List<DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO>>()
    @JvmField
    var contract= ObservableField<String>()

    @JvmField
    var socialsAdapter = ObservableField<BaseQuickAdapter<*, *>>()


}