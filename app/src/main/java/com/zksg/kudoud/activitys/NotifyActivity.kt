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
import com.zksg.kudoud.state.NotifyActivityViewModel
import com.zksg.kudoud.utils.DownloadUtils
import com.zksg.kudoud.utils.StringUtils
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.NotifyBean

class NotifyActivity : BaseDialogActivity() {
    private var mNotifyActivityViewModel: NotifyActivityViewModel? = null
    private  var adapter:NotifyAdapter_V?=null
    private var page=1


    override fun initViewModel() {
        mNotifyActivityViewModel = getActivityScopeViewModel(
            NotifyActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData(binding as ActivityNotifyBinding)
    }

    override fun getDataBindingConfig(): DataBindingConfig {

        return DataBindingConfig(R.layout.activity_notify, BR.vm, mNotifyActivityViewModel!!)
            .addBindingParam(BR.click,ClickProxy())
    }

    private fun initData(notifyBinding: ActivityNotifyBinding) {

        notifyBinding.mSmartRefreshLayout.setOnRefreshListener {
            page=1
            mNotifyActivityViewModel?.getNotifyList(page)
        }

        notifyBinding.mSmartRefreshLayout.setOnLoadMoreListener {
            page += 1
            mNotifyActivityViewModel?.getNotifyList(page)
        }

//        var list= arrayListOf(NotifyBean("我们的metastore正式诞生了我们的metastore正式诞生了我们的metastore正式诞生了","愿景是成为世界上最大的币圈应用市场,每个币圈人手一个愿景是成为世界上最大的币圈应用市场,每个币圈人手一个愿景是成为世界上最大的币圈应用市场,每个币圈人手一个愿景是成为世界上最大的币圈应用市场,每个币圈人手一个愿景是成为世界上最大的币圈应用市场,每个币圈人手一个"),NotifyBean("我们开始发币了","值得祝贺的是我们开始法币了,兄弟们开始私募 搞起 下一步上交易所"))
        adapter= NotifyAdapter_V(R.layout.item_notify_h)

        mNotifyActivityViewModel?.refreshOrload?.observe(this){
            if(notifyBinding.mSmartRefreshLayout.isRefreshing){
                notifyBinding.mSmartRefreshLayout.finishRefresh(2000)
            }
            if(notifyBinding.mSmartRefreshLayout.isLoading){
                notifyBinding.mSmartRefreshLayout.finishLoadMore(2000)
            }

            if(adapter?.data?.size==0){
                adapter?.setEmptyView(R.layout.recycle_emp_layout)
            }



        }
        mNotifyActivityViewModel?.notifResults?.observe(this){
            if(it!=null&& it.size>0){
                if(notifyBinding.mSmartRefreshLayout.isRefreshing){
                    adapter?.setList(it)
                }else{
                    adapter?.addData(it)
                }

            }


        }
        mNotifyActivityViewModel?.coininstallAdapter?.set(adapter)




        mNotifyActivityViewModel?.getNotifyList(page)
    }



    inner class ClickProxy {

        fun finishself(){
            ActivityUtils.finishActivity(this@NotifyActivity,true)
        }






    }




}
