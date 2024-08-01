package com.zksg.kudoud.fragments

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.InitParamsEntity
import com.zksg.kudoud.state.IntroduceFragmentViewModel
import com.zksg.kudoud.state.PoolsFragmentViewModel

class IntroduceFragment(contract:String?) : BaseFragment() {
    var mContract=contract
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
//            .addBindingParam(BR.iEntity, InitParamsEntity(mType, land))
    }


}