package com.zksg.kudoud.activitys

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.state.SearchActivityViewModel

class SeatchActivity : BaseActivity() {
    var mSearchActivityViewModel: SearchActivityViewModel? = null
    override fun initViewModel() {
        mSearchActivityViewModel = getActivityScopeViewModel(
            SearchActivityViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_search, BR.vm, mSearchActivityViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    inner class ClickProxy
}