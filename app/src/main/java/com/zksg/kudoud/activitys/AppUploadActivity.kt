package com.zksg.kudoud.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lqr.imagepicker.ImagePicker
import com.lqr.imagepicker.bean.ImageItem
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.AppDetailHeaderAdapter
import com.zksg.kudoud.adapters.CommonAdapter

import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.kudoud.widgets.NinePicturesAdapter
import com.zksg.lib_api.beans.AppDetailItem
import com.zksg.lib_api.beans.HomeItem

class AppUploadActivity : BaseActivity() {
    private var mAppUploadActivityViewModel: AppUploadActivityViewModel? = null
    override fun initViewModel() {
        mAppUploadActivityViewModel = getActivityScopeViewModel(
            AppUploadActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_upload, BR.vm, mAppUploadActivityViewModel!!)
              .addBindingParam(BR.click,  ClickProxy())
    }

    private fun initData() {
        val IMAGE_PICKER = 120
        var mNinePicturesAdapter:NinePicturesAdapter?=null

        //register
        val mGetContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intent = result.data
            if (result.resultCode == Activity.RESULT_OK && intent != null) {
                // 使用 Bundle 获取请求码
                val requestCode = intent.extras?.getInt("REQUEST_CODE", 0)
                // 在回调方法中处理获取的结果及请求码
                // ...
//                if(requestCode==IMAGE_PICKER){
                    val images = intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as ArrayList<ImageItem>
                    var paths= mutableListOf<String>()
                    images.forEach { paths.add(it.path) }
                    mNinePicturesAdapter?.addAll(paths)
//                }
            }
        }
        //handler result
          mNinePicturesAdapter= NinePicturesAdapter(this,3,NinePicturesAdapter.OnClickAddListener {
              val intent = ImagePicker.picker().showCamera(false).buildPickIntent(this)
//              val bundle = Bundle()
//              bundle.putInt("REQUEST_CODE", IMAGE_PICKER)
//              intent.putExtras(bundle)
//              intent.putExtra("REQUEST_CODE", IMAGE_PICKER)
              mGetContent.launch(intent)
        })
        mNinePicturesAdapter?.setOnRemoveListener {}
        mAppUploadActivityViewModel?.mNinePicturesAdapter?.set(mNinePicturesAdapter)

    }



    inner class ClickProxy {
        fun Skip2LocalApksPage(){
            startActivity(Intent(this@AppUploadActivity,ShowLocalApksActivity::class.java))
        }
    }


}