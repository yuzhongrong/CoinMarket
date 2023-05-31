package com.zksg.kudoud.fragments

import android.content.Intent
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.CreateEnvActivity
import com.zksg.kudoud.adapters.CategoryPagerAdapter
import com.zksg.kudoud.adapters.CommonAdapter_V
import com.zksg.kudoud.adapters.RankingAdapter_V
import com.zksg.kudoud.adapters.SceneAdapter
import com.zksg.kudoud.beans.CommonCategoryDataEnum
import com.zksg.kudoud.beans.CommonDataEnum
import com.zksg.kudoud.state.RankingFragmentViewModel
import com.zksg.kudoud.state.SceneFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.lib_api.beans.HomeItem

class RankingFragment:BaseFragment(){
    private var  rankingViewModel: RankingFragmentViewModel ?=null
    private var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        rankingViewModel=getFragmentScopeViewModel(RankingFragmentViewModel::class.java)
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_ranking,BR.vm,rankingViewModel!!)
           .addBindingParam(BR.adapter, SceneAdapter(context))
           .addBindingParam(BR.click,ClickProxy())

    }

    override fun loadInitData() {
        var homeitems= mutableListOf(
            HomeItem(R.mipmap.item_heart_rate,"86",getString(R.string.str_item_rate)),
            HomeItem(R.mipmap.item_breathe,"22",getString(R.string.str_item_breathe)),
            HomeItem(R.mipmap.item_weight,"21",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"23",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"25",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"29",getString(R.string.str_item_weight)),
        )
        rankingViewModel?.coininstallAdapter?.set(
            RankingAdapter_V(
                R.layout.item_avatarprocess,
                homeitems
            )
        )


    }

    inner class ClickProxy{
        fun CreateEnv(){
           startActivity(Intent(requireContext(),CreateEnvActivity::class.java))
        }
   }

}