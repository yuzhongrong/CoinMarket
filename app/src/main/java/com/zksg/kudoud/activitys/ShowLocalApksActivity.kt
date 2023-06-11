package com.zksg.kudoud.activitys

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_image_loader.ipfs.IPFSManager
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.LocalApksAdapter_V
import com.zksg.kudoud.state.ShowLocalApksActivityViewModel
import com.zksg.kudoud.utils.FileUtils
import kotlinx.coroutines.*

class ShowLocalApksActivity : BaseActivity() {
    private var mShowLocalApksActivityViewModel: ShowLocalApksActivityViewModel? = null
    override fun initViewModel() {
        mShowLocalApksActivityViewModel = getActivityScopeViewModel(
            ShowLocalApksActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_local_apks, BR.vm, mShowLocalApksActivityViewModel!!)
    }

    private fun initData() {

//        var ipfs= IPFSManager.getInstance().ipfsInstance
//        Log.d("----ipfs->",ipfs.toString())


        mShowLocalApksActivityViewModel?.mloginResult?.observe(this){
            Log.e("---mloginResult->", it.size.toString())

           mShowLocalApksActivityViewModel?.mApksAdapter?.set(
            LocalApksAdapter_V(this,
                R.layout.item_apk,
                it
            )
        )

        }

        mShowLocalApksActivityViewModel?.loadAllLocalApks(this)




    }





}