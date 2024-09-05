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
import com.zksg.kudoud.beans.CategoryEnum
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
                getString(R.string.str_golddoge),
                getString(R.string.str_alpha),
                getString(R.string.str_pumpout),
                getString(R.string.str_pumpin),

            )
        )

        mMarketsFragmentViewModel?.memecategoryadapter?.set(
            MemeCategoryPagerAdapter(
                childFragmentManager,
                arrayOf(
                    CategoryEnum.GOLDENDOG,
                    CategoryEnum.ALPHA,
                    CategoryEnum.PUMP_OUT_HOT,
                    CategoryEnum.PUMP_IN_HOT,

                )
            )
        )
    }

    inner class ClickProxy{



   }

}