package com.zksg.kudoud.activitys

import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.R
import android.os.Bundle
//import com.kunminx.architecture.ui.page.StateHolder
//import com.kunminx.architecture.ui.state.State
//import com.kunminx.architecture.utils.BarUtils
import com.zksg.kudoud.BR
import com.zksg.kudoud.state.SearchDeviceIngViewModel

class SearchDeviceIngActivity : BaseActivity() {
    private var mSearchDeviceIngViewModel: SearchDeviceIngViewModel? = null
    override fun initViewModel() {
        mSearchDeviceIngViewModel = getActivityScopeViewModel(
            SearchDeviceIngViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_search_device_ing,BR.vm, mSearchDeviceIngViewModel!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData();
    }

    private fun initData(){

    }


//    class States : StateHolder() {
//        //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
//        //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
//        val isDrawerOpened = State(false)
//        val openDrawer = State(false)
//        val allowDrawerOpen = State(true)
//    }





}