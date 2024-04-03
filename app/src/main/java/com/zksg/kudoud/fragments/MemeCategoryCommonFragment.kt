package com.zksg.kudoud.fragments

import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.HomeMemeCWAdapter_V
import com.zksg.kudoud.adapters.MemeCommonListdapter
import com.zksg.kudoud.state.MemeCategoryCommonFragmentViewModel
import com.zksg.kudoud.utils.DataFilterUtils
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.lib_api.beans.MemeCommonEntry

class MemeCategoryCommonFragment(type: Int) : BaseFragment() {
    var mType=type.toString()
    var mCategoryCommonFragmentViewModel: MemeCategoryCommonFragmentViewModel? = null
    override fun initViewModel() {
        mCategoryCommonFragmentViewModel = getFragmentScopeViewModel(
            MemeCategoryCommonFragmentViewModel::class.java
        )
        Log.e("HeartRateDayFragment", "initViewModel:$mCategoryCommonFragmentViewModel")
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.list_meme_coins, BR.vm, mCategoryCommonFragmentViewModel!!)
            .addBindingParam(BR.adapter,MemeCommonListdapter(context))
    }

    override fun loadInitData() {


       var json= LocalJsonResolutionUtils.getJson(context,"memesimulator.json")
//        Log.d("---xxx-loadmemejson", json)
       var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
        mCategoryCommonFragmentViewModel?.mHotMeme?.value=DataFilterUtils.filterNonNullName(simulators.tokens)





    }
}