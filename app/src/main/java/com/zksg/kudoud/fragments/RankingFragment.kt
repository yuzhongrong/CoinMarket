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
    private var mRankingAdapter_V:RankingAdapter_V?=null

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
        val categorys = resources.getStringArray(R.array.category_str)
        mRankingAdapter_V=RankingAdapter_V(
            R.layout.item_today_app_h,categorys)
        rankingViewModel?.coininstallAdapter?.set(mRankingAdapter_V)

        rankingViewModel?.mRankingApks?.observe(this){
            mRankingAdapter_V?.setList(it)
        }
        rankingViewModel?.getRankFragment(1,50,"app_download_count","descending")

    }

    inner class ClickProxy{
        fun CreateEnv(){
           startActivity(Intent(requireContext(),CreateEnvActivity::class.java))
        }
   }

}