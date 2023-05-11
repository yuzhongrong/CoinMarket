package com.zksg.kudoud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CycMusicAdapter
import com.zksg.kudoud.state.CycMusicFragmentViewModel
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.lib_api.playlist.BasicMusicInfo

class CycMusicFragment:BaseFragment(){
    private var  meViewModel: CycMusicFragmentViewModel ?=null
    //TODO:tip oncreate 时候调用
    override fun initViewModel() {
        meViewModel=getFragmentScopeViewModel(CycMusicFragmentViewModel::class.java)
    }



    //TODO:tip oncreateview 时候调用
    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_cyc_music,BR.vm,meViewModel!!)
           .addBindingParam(BR.adapter,CycMusicAdapter(context))

    }


    override fun loadInitData() {
        initData()
    }

    fun  initData() {
        var datas = mutableListOf(
            BasicMusicInfo(true, "音乐名称", "5:20"),
            BasicMusicInfo(false, "音乐名称", "6:05"),
            BasicMusicInfo(false, "音乐名称", "9:11"),
            BasicMusicInfo(false, "音乐名称", "3:07"),
            BasicMusicInfo(false, "音乐名称", "9:27"),
            BasicMusicInfo(false, "音乐名称", "4:12"),
            BasicMusicInfo(false, "音乐名称", "4:22"),
            BasicMusicInfo(false, "音乐名称", "3:12"),
            BasicMusicInfo(false, "音乐名称", "2:42"),
            BasicMusicInfo(false, "音乐名称", "4:22"),
            BasicMusicInfo(false, "音乐名称", "0:45"),
            BasicMusicInfo(false, "音乐名称", "2:45"),
        )
        meViewModel?.datas?.set(datas)
    }





    }



