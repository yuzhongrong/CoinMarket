package com.zksg.kudoud.activitys

import android.Manifest.permission.*
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lqr.imagepicker.ImagePicker
import com.lqr.imagepicker.bean.ImageItem
import com.lxj.xpopup.XPopup
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.dialogs.ChangeEmailDialog
import com.zksg.kudoud.dialogs.LoadingDialog
import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.kudoud.widgets.NinePicturesAdapter

class AppUploadActivity : BaseActivity(){
    private var mAppUploadActivityViewModel: AppUploadActivityViewModel? = null
    private var mGetContentApk : ActivityResultLauncher<Intent>?=null
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
          mNinePicturesAdapter= NinePicturesAdapter(this,4) {
              val intent = ImagePicker.picker().showCamera(false).buildPickIntent(this)
              mGetContent.launch(intent)
          }

        mNinePicturesAdapter?.setOnRemoveListener {}
        mAppUploadActivityViewModel?.mNinePicturesAdapter?.set(mNinePicturesAdapter)

         mGetContentApk = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intent = result.data
            if (result.resultCode == Activity.RESULT_OK && intent != null) {
                // 使用 Bundle 获取请求码
//                    val requestCode = intent.extras?.getInt("REQUEST_CODE", 0)
                val filePath = intent.extras?.getString("PATH")
                // 在回调方法中处理获取的结果及请求码
                Log.d("---Start2ShowLocalApkActivity---->",filePath.toString())
                // ...
                UpdateUi(filePath)
            }
        }

        mAppUploadActivityViewModel?.of_icon?.set(getDrawable(R.mipmap.ic_addphoto))

        mAppUploadActivityViewModel?.cid?.observe(this){
            Log.d("--upload file---->",it)
        }


    }

    private fun UpdateUi(path:String?){
        var apk=AppUtils.getApkInfo(path)
        mAppUploadActivityViewModel?.of_icon?.set(apk?.icon)
        mAppUploadActivityViewModel?.of_version?.set(apk!!.versionName)
        mAppUploadActivityViewModel?.of_size?.set(FileUtils.getSize(path))
        mAppUploadActivityViewModel?.UploadFile(path!!)
    }



    inner class ClickProxy {
        fun Skip2LocalApksPage() {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R || Environment.isExternalStorageManager()) {
                Start2ShowLocalApkActivity()
            } else {
                val intent: Intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
            }
        }

        fun Start2ShowLocalApkActivity() {
            var intent = Intent(this@AppUploadActivity, ShowLocalApksActivity::class.java)
            mGetContentApk?.launch(intent)

        }

        fun PublishApk() {

            XPopup.Builder(this@AppUploadActivity).asCustom(LoadingDialog(this@AppUploadActivity)).show()
        }
    }





}