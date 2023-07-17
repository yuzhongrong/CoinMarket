package com.zksg.kudoud.activitys

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.zksg.kudoud.state.AppDetailActivityViewModel
import com.zksg.kudoud.utils.DownloadUtils
import com.zksg.kudoud.utils.StringUtils
import com.zksg.lib_api.beans.AppInfoBean

class AppDetailActivity : BaseDialogActivity() {
    private var mAppDetailActivityViewModel: AppDetailActivityViewModel? = null
    private  var appinfo:AppInfoBean?=null
    private var mClickProxy:ClickProxy?=null

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
               mClickProxy=ClickProxy()
        return DataBindingConfig(R.layout.activity_detail, BR.vm, mAppDetailActivityViewModel!!)
            .addBindingParam(BR.click,mClickProxy!!)
    }

    private fun initData() {
        appinfo=intent.getSerializableExtra("appinfo") as AppInfoBean


        //star init ui
        val array = Gson().fromJson(appinfo?.app_screen_4, Array<String>::class.java)
        var screenList= array.toList()

        mAppDetailActivityViewModel?.appDetailAdapter?.set(
            AppDetailHeaderAdapter(
                R.layout.item_apps_detail,
                screenList
            )
        )

        mAppDetailActivityViewModel?.loadingVisible?.observe(this){

            if(it){ showDialog() }else{dismissDialog()}
        }

        mAppDetailActivityViewModel?.updateappinfoResult?.observe(this){

           if(it?.responseStatus!!.isSuccess){
               mClickProxy?.DownloadApk()
           }else{
               ToastUtils.showShort(getString(R.string.str_download_request_fail))
           }

        }

        var categorys=resources.getStringArray(R.array.category_str)
        var index=appinfo?.app_category?.toInt()
        var category=categorys[index!!]
        mAppDetailActivityViewModel?.app_name?.set(appinfo?.app_name)
        mAppDetailActivityViewModel?.app_size?.set(appinfo?.app_size)
        mAppDetailActivityViewModel?.app_version?.set(appinfo?.app_version)
        mAppDetailActivityViewModel?.app_category?.set(category)
        mAppDetailActivityViewModel?.app_overrview?.set(appinfo?.app_overrview)
        mAppDetailActivityViewModel?.app_offcail?.set(appinfo?.app_offical)
        var number=StringUtils.num2thousand(appinfo?.app_download_count!!.toString())
        mAppDetailActivityViewModel?.app_download_count?.set(number)
    }



    inner class ClickProxy {


        fun RequestDownload(){

            appinfo?.let {
                it.app_download_count+=1
                mAppDetailActivityViewModel?.updateAppinfo(it)
            }
        }

        fun DownloadApk(){

//            appinfo?.app_file?.let {
//                val fileName = appinfo?.app_name+".apk"
//                IPFSManager.downloadFileWithDownloadManager(
//                    this@AppDetailActivity,
//                    it,
//                    fileName
//                )
//            }

            //通知栏下载apk
            val url = ipfs_base_url+"${appinfo?.app_file}"
            val fileName = appinfo?.app_name+".apk"
            DownloadUtils(this@AppDetailActivity, url, fileName)




        }

        fun GoWebsites() {
            val url =appinfo?.app_offical

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        fun finishself(){
            ActivityUtils.finishActivity(this@AppDetailActivity,true)
        }

    }




}
