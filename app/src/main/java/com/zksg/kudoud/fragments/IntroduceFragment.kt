package com.zksg.kudoud.fragments

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SocialAdapter
import com.zksg.kudoud.state.IntroduceFragmentViewModel
import com.zksg.kudoud.state.Kline2OrderActivityViewModel
import com.zksg.kudoud.utils.CopyUtils

class IntroduceFragment(contract:String?,mKline2OrderActivityViewModel: Kline2OrderActivityViewModel?) : BaseFragment() {
    var mContract=contract
    var mKline2OrderViewModel=mKline2OrderActivityViewModel
    var mIntroduceFragmentViewModel: IntroduceFragmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        mIntroduceFragmentViewModel = getFragmentScopeViewModel(
            IntroduceFragmentViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {

        return DataBindingConfig(R.layout.fragment_introduce, BR.vm, mIntroduceFragmentViewModel!!)
               .addBindingParam(BR.adapter, SocialAdapter(requireContext()))
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun loadInitData() {
        super.loadInitData()
        mKline2OrderViewModel!!.pairs.observe(this){
            mIntroduceFragmentViewModel!!.pairs.set(it)
            mIntroduceFragmentViewModel!!.socials.set(it[0].info.socials)
            mIntroduceFragmentViewModel!!.website.set(it[0].info.websites[0].url)


        }

        mIntroduceFragmentViewModel!!.contract.set(mContract)

    }


    inner class ClickProxy {

        fun copyAddress(){
            CopyUtils.copyToClipboard(requireContext(),mIntroduceFragmentViewModel!!.contract.get()!!)
            ToastUtils.showShort(getString(R.string.str_copy_success))
        }

    }


}