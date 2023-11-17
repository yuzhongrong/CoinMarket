package com.zksg.kudoud.activitys

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.google.gson.Gson
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.constants.config.*
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.AppDetailHeaderAdapter
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.adapters.NotifyAdapter_V
import com.zksg.kudoud.databinding.ActivityMainBinding
import com.zksg.kudoud.databinding.ActivityNotifyBinding
import com.zksg.kudoud.state.AppDetailActivityViewModel
import com.zksg.kudoud.state.PreSaleActivityViewModel
import com.zksg.kudoud.utils.DownloadUtils
import com.zksg.kudoud.utils.StringUtils
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.NotifyBean

class PreSaleMstActivity : BaseDialogActivity() {
    private var mPreSaleActivityViewModel: PreSaleActivityViewModel? = null


    override fun initViewModel() {
        mPreSaleActivityViewModel = getActivityScopeViewModel(
            PreSaleActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getDataBindingConfig(): DataBindingConfig {

        return DataBindingConfig(R.layout.activity_presale, BR.vm, mPreSaleActivityViewModel!!)
            .addBindingParam(BR.click,ClickProxy())
    }

    private fun initData(notifyBinding: ActivityNotifyBinding) {
    }



    inner class ClickProxy {

        fun finishself(){
            ActivityUtils.finishActivity(this@PreSaleMstActivity,true)
        }






    }




}
