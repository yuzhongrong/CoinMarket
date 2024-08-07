package com.zksg.kudoud.fragments

import android.content.Intent
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.CreateEnvActivity
import com.zksg.kudoud.activitys.SearchActivity
import com.zksg.kudoud.adapters.CategoryPagerAdapter
import com.zksg.kudoud.adapters.MemeCategoryPagerAdapter
import com.zksg.kudoud.adapters.SceneAdapter
import com.zksg.kudoud.beans.CommonCategoryDataEnum
import com.zksg.kudoud.state.MarketsFragmentViewModel
import com.zksg.kudoud.state.SceneFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel

class MarketsFragment:BaseFragment(){
    private var  mMarketsFragmentViewModel: MarketsFragmentViewModel ?=null
    private var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        mMarketsFragmentViewModel=getFragmentScopeViewModel(MarketsFragmentViewModel::class.java)
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {

       return DataBindingConfig(R.layout.fragment_markets,BR.vm,mMarketsFragmentViewModel!!)
//           .addBindingParam(BR.adapter, SceneAdapter(context))
           .addBindingParam(BR.click,ClickProxy())

    }

    override fun loadInitData() {
        //meme category
        mMarketsFragmentViewModel?.indicatorTitle?.set(
            arrayOf(
                "筹码集中",
                "金狗",
                "pump内盘",
                "pump外盘",
                "大鲸鱼钱包",
            )
        )




    }

    inner class ClickProxy{



   }

}