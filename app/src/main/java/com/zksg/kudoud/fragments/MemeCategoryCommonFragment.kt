package com.zksg.kudoud.fragments

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.MemeCommonListdapter
import com.zksg.kudoud.beans.CommonCategoryDataEnum
import com.zksg.kudoud.state.MemeCategoryCommonFragmentViewModel
import com.zksg.kudoud.utils.DataFilterUtils
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.lib_api.beans.MemeCommonEntry

class MemeCategoryCommonFragment : BaseFragment() {


    var mCategory=""
    var mCategoryCommonFragmentViewModel: MemeCategoryCommonFragmentViewModel? = null


    companion object {
        fun newInstance(category: String): MemeCategoryCommonFragment {
            val fragment = MemeCategoryCommonFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCategory = arguments?.getString("category") ?: ""
        // 在此处使用传入的参数
    }

    override fun initViewModel() {
        mCategoryCommonFragmentViewModel = getFragmentScopeViewModel(
            MemeCategoryCommonFragmentViewModel::class.java
        )
        Log.e("HeartRateDayFragment", "initViewModel:$mCategoryCommonFragmentViewModel")
    }

    override fun onStart() {
        super.onStart()
        mCategoryCommonFragmentViewModel!!.startFetchingTrendingTokens(mCategory,30)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.list_meme_coins, BR.vm, mCategoryCommonFragmentViewModel!!)
            .addBindingParam(BR.adapter,MemeCommonListdapter(context))
    }

    override fun loadInitData() {
//        var json=LocalJsonResolutionUtils.getJson(context,"memevolsimulator.json")
//        var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
//        var results=DataFilterUtils.filterNonNullName(simulators.tokens)


//        Log.d("---xxx.js-loadmemejson", json)

        //这里在交易量降序的数据集合中最好是请求前150个数据  对期进行涨跌幅排序,市值排序 这样才有意义

//        mCategoryCommonFragmentViewModel?.mHotMeme?.value=results

    }

    override fun onStop() {
        super.onStop()
        mCategoryCommonFragmentViewModel!!.stopFetchingTrendingTokens()
    }

}