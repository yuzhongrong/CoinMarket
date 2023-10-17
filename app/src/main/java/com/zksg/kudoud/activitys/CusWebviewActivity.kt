package com.zksg.kudoud.activitys

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebViewClient
import com.blankj.utilcode.util.ActivityUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.databinding.ActivityWebviewCusBinding
import com.zksg.kudoud.databinding.ActivityWeightBinding
import com.zksg.kudoud.databinding.FragmentHomeBinding
import com.zksg.kudoud.state.CusWebViewActivityModel
import com.zksg.kudoud.state.WebViewActivityModel

class CusWebviewActivity : BaseActivity() {

    private var mWebViewActivityModel: CusWebViewActivityModel? = null

    private var title:String=""
    private var url:String=""
    private var time=""
    override fun initViewModel() {
        mWebViewActivityModel = getActivityScopeViewModel(
            CusWebViewActivityModel::class.java
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url= intent.getStringExtra("url")!!
        title= intent.getStringExtra("title")!!
        initData(binding as ActivityWebviewCusBinding)
    }


    fun initData(bind:ActivityWebviewCusBinding){
        mWebViewActivityModel?.url?.set(url)
        mWebViewActivityModel?.title?.set(title)
        mWebViewActivityModel?.newProgress?.set(bind.progressBar)
    }


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_webview_cus, BR.vm, mWebViewActivityModel!!)
            .addBindingParam(BR.click,ClickProxy())

    }





    inner class ClickProxy {

        fun finishself(){
            ActivityUtils.finishActivity(this@CusWebviewActivity,true)
        }




    }


}