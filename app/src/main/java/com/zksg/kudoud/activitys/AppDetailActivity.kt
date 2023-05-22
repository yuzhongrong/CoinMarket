package com.zksg.kudoud.activitys

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.AppDetailHeaderAdapter
import com.zksg.kudoud.adapters.CommonAdapter
import com.zksg.kudoud.state.AppDetailActivityViewModel
import com.zksg.lib_api.beans.AppDetailItem
import com.zksg.lib_api.beans.HomeItem

class AppDetailActivity : BaseActivity() {
    private var mAppDetailActivityViewModel: AppDetailActivityViewModel? = null
    override fun initViewModel() {
        mAppDetailActivityViewModel = getActivityScopeViewModel(
            AppDetailActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_detail, BR.vm, mAppDetailActivityViewModel!!)
    }

    private fun initData() {

        var detailitems= mutableListOf(
            AppDetailItem("https://ipfs.io/ipfs/QmYHats9k8EqJDQXrimedv7SXGDpyf9HJdLFb1DU3SieTf","",""),
            AppDetailItem("https://ipfs.io/ipfs/Qmabbumer7VxgJvA8cdXgV34wWvsMv1BmHaXUSw2CsfuQf","",""),
            AppDetailItem("https://ipfs.io/ipfs/QmRL21LFcvARFgMDLsqEUp1fUWEsXN7xEpgEsW4D98cckZ","",""),
            AppDetailItem("https://ipfs.io/ipfs/QmQdyV1RNRiwzxCgZA1MKnVSq4XXiPD7o28qWnBe3eeiQd","",""),

        )

        mAppDetailActivityViewModel?.appDetailAdapter?.set(
            AppDetailHeaderAdapter(
                R.layout.item_apps_detail,
                detailitems
            )
        )

    }
}