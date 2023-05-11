package com.zksg.kudoud.activitys

import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.R
import android.os.Bundle
//import com.kunminx.architecture.ui.page.StateHolder
import com.kunminx.architecture.ui.state.State
//import com.kunminx.architecture.utils.BarUtils
import com.zksg.kudoud.BR
import com.zksg.kudoud.adapters.DevideOptionAdapter
import com.zksg.kudoud.state.DeviceControltViewModel
import com.zksg.kudoud.state.SearchDeviceIngViewModel
import com.zksg.lib_api.beans.DeviceOptiontem

class ControlDeviceActivity : BaseActivity() {
    private var mDeviceControltViewModel: DeviceControltViewModel? = null
    override fun initViewModel() {
        mDeviceControltViewModel = getActivityScopeViewModel(
            DeviceControltViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_device_control,BR.vm, mDeviceControltViewModel!!)
            .addBindingParam(BR.click,proxyClick())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData();
    }

    private fun initData(){

        val items= mutableListOf(
            DeviceOptiontem(R.mipmap.ic_open_on,"",getString(R.string.str_open),DeviceOptiontem.Type.NONE,false),
            DeviceOptiontem(R.mipmap.ic_shake_on,"",getString(R.string.str_shake),DeviceOptiontem.Type.NONE,false),
            DeviceOptiontem(R.mipmap.ic_music_on,"",getString(R.string.str_music),DeviceOptiontem.Type.ENABLE,false),
            DeviceOptiontem(R.mipmap.ic_light_on,"",getString(R.string.str_light),DeviceOptiontem.Type.ENABLE,false),
            DeviceOptiontem(R.mipmap.ic_starlight_on,"",getString(R.string.str_starlight),DeviceOptiontem.Type.ENABLE,false),
            DeviceOptiontem(R.mipmap.ic_ambientlight_on,"",getString(R.string.str_ambientlight),DeviceOptiontem.Type.ENABLE,false),
            DeviceOptiontem(R.mipmap.ic_control_dashboard_on,"",getString(R.string.str_contral_dashboard),DeviceOptiontem.Type.ENABLE,false))
        mDeviceControltViewModel?.adapter?.set(DevideOptionAdapter(items))
    }


//    class States : StateHolder() {
//
//
//        val isDrawerOpened = State(false)
//        val openDrawer = State(false)
//        val allowDrawerOpen = State(true)
//    }


   inner class proxyClick{

        fun test(){
        }


    }



}