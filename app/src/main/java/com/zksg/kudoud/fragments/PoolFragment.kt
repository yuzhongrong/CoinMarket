package com.zksg.kudoud.fragments

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.InitParamsEntity
import com.zksg.kudoud.state.PoolsFragmentViewModel

class PoolFragment : BaseFragment() {

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
//            .addBindingParam(BR.iEntity, InitParamsEntity(mType, land))
    }


}