package com.zksg.kudoud.activitys

import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.R
import android.os.Bundle
//import com.kunminx.architecture.ui.page.StateHolder
//import com.kunminx.architecture.ui.state.State
//import com.kunminx.architecture.utils.BarUtils
import com.zksg.kudoud.BR
import com.zksg.kudoud.adapters.FindDevicesAdapter
import com.zksg.kudoud.state.SearchDeviceViewModel
import com.zksg.lib_api.beans.DeviceBean

class SearchDeviceActivity : BaseActivity() {
    private var mSearchDeviceViewModel: SearchDeviceViewModel? = null
    override fun initViewModel() {
        mSearchDeviceViewModel = getActivityScopeViewModel(
            SearchDeviceViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_search_device,BR.vm, mSearchDeviceViewModel!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }


    private fun initData(){
        var devices=mutableListOf(
            DeviceBean("10:ca:bb:xp:cp","test",R.mipmap.ic_item_device),
            DeviceBean("10:ca:bb:xp:cp","test",R.mipmap.ic_item_device),
            DeviceBean("10:ca:bb:xp:cp","test",R.mipmap.ic_item_device),
            DeviceBean("10:ca:bb:xp:cp","test",R.mipmap.ic_item_device))
        mSearchDeviceViewModel?.adapter?.set(FindDevicesAdapter(devices))
    }


//    class States : StateHolder() {
//        //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
//        //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
//        val isDrawerOpened = State(false)
//        val openDrawer = State(false)
//        val allowDrawerOpen = State(true)
//    }





}