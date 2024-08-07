package com.zksg.kudoud.fragments

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.InitParamsEntity
import com.zksg.kudoud.state.CheckFragmentViewModel
import com.zksg.kudoud.state.PoolsFragmentViewModel

class CheckFragment(contract:String?) : BaseFragment() {
    var mContract=contract
    var mCheckFragmentViewModel: CheckFragmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        mCheckFragmentViewModel = getFragmentScopeViewModel(
            CheckFragmentViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_checks, BR.vm, mCheckFragmentViewModel!!)
//            .addBindingParam(BR.iEntity, InitParamsEntity(mType, land))
    }

    override fun loadInitData() {
        mCheckFragmentViewModel!!.url.set("https://ave.ai/check/DtR4D9FtVoTX2569gaL837ZgrB6wNjj6tkmnX9Rdk9B2-solana")
    }


}