package com.zksg.kudoud.activitys

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebViewClient
import com.blankj.utilcode.util.ActivityUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.databinding.ActivityWeightBinding
import com.zksg.kudoud.state.WebViewActivityModel

class WebviewActivity : BaseActivity() {

    private var mWebViewActivityModel: WebViewActivityModel? = null

    private var title:String=""
    private var content:String=""
    private var time=""
    override fun initViewModel() {
        mWebViewActivityModel = getActivityScopeViewModel(
            WebViewActivityModel::class.java
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content= intent.getStringExtra("content")!!
        title= intent.getStringExtra("title")!!
        time=intent.getStringExtra("time")!!
        mWebViewActivityModel?.content?.set(content)
        mWebViewActivityModel?.title?.set(title)
        mWebViewActivityModel?.time?.set(time)
    }


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_webview, BR.vm, mWebViewActivityModel!!)
            .addBindingParam(BR.click,ClickProxy())

    }

    inner class ClickProxy {

        fun finishself(){
            ActivityUtils.finishActivity(this@WebviewActivity,true)
        }




    }


}