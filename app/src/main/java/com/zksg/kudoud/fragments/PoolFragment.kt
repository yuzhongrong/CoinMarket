package com.zksg.kudoud.fragments

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.MemePoolListdapter
import com.zksg.kudoud.state.PoolsFragmentViewModel

class PoolFragment(contract: String?) : BaseFragment() {
    var mContract=contract
    var mPoolsFragmentViewModel: PoolsFragmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        mPoolsFragmentViewModel = getFragmentScopeViewModel(
            PoolsFragmentViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_pools, BR.vm, mPoolsFragmentViewModel!!)
            .addBindingParam(BR.adapter, MemePoolListdapter(requireContext()))
//            .addBindingParam(BR.iEntity, InitParamsEntity(mType, land))
    }


    override fun loadInitData() {
        mContract?.let { mPoolsFragmentViewModel!!.getTokenInfo(it) }
    }


}