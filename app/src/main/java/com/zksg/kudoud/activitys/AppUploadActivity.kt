package com.zksg.kudoud.activitys

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lqr.imagepicker.ImagePicker
import com.lqr.imagepicker.bean.ImageItem
import com.lxj.xpopup.XPopup
import com.suke.widget.SwitchButton
import com.suke.widget.SwitchButton.OnCheckedChangeListener
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.kudoud.dialogs.CategorySelectDialog
import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.kudoud.utils.MyFileUtils
import com.zksg.kudoud.widgets.NinePicturesAdapter

class AppUploadActivity : BaseDialogActivity(){
    private var mAppUploadActivityViewModel: AppUploadActivityViewModel? = null
    private var mGetContentApk : ActivityResultLauncher<Intent>?=null
    private var mAppInfoBean= AppInfoBean()
    private val picturnNum=4
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
            .addBindingParam(BR.nameTextWatcher, nameTextWatcher)
            .addBindingParam(BR.subtitleTextWatcher, subTitleTextWatcher)
            .addBindingParam(BR.overrviewTextWatcher, overrviewTextWatcher)
            .addBindingParam(BR.twitterTextWatcher, twitterTextWatcher)
            .addBindingParam(BR.telegramTextWatcher, telegramTextWatcher)
            .addBindingParam(BR.officialTextWatcher, officialTextWatcher)
            .addBindingParam(BR.downloadCountTextWatcher, downloadCountTextWatcher)
            .addBindingParam(BR.checkChange, checkChange)



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

            }
        }
        //handler result
          mNinePicturesAdapter= NinePicturesAdapter(this,picturnNum) {
              val intent = ImagePicker.picker().showCamera(false).buildPickIntent(this)
              mGetContent.launch(intent)
          }

        mNinePicturesAdapter.setOnRemoveListener {
            var screens=mAppUploadActivityViewModel?.cid_app_screen_4?.value//上传过后screens才有值
            if(screens?.size!! >0){
                screens.minus(screens.get(it))
            }


        }
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


        mAppUploadActivityViewModel?.loadingVisible?.observe(this){

            if(it){ showDialog() }else{dismissDialog()}
        }


        mAppUploadActivityViewModel?.cid?.observe(this){
            Log.d("--cid upload file---->",it)
            mAppInfoBean.app_file=it
        }

        mAppUploadActivityViewModel?.cid_appicon?.observe(this){
            Log.d("--cid_appicon---->",it)
            mAppInfoBean.app_icon=it

        }
        mAppUploadActivityViewModel?.cid_app_screen_4?.observe(this){
            it.forEach {Log.d("--cid_app_screen_4---->",it) }

            Log.d("--cid_app_screen_41---->", Gson().toJson(it))
            mAppInfoBean.app_screen_4=Gson().toJson(it)
        }

        mAppUploadActivityViewModel?.of_category_req?.observe(this){
            Log.d("--of_category_req---->",it)
            mAppInfoBean.app_category=it;

        }

        //subscribe the commit apk api
        mAppUploadActivityViewModel?.mpublishResult?.observe(this){
            if(it?.responseStatus!!.isSuccess){
                ToastUtils.showShort(getString(R.string.str_publish_success))
                this.finish()
            }else{
                ToastUtils.showShort(getString(R.string.str_publish_fail))
            }

        }

    }

    private fun UpdateUi(path:String?){
        var apk=AppUtils.getApkInfo(path)
        mAppUploadActivityViewModel?.of_icon?.set(apk?.icon)
        mAppUploadActivityViewModel?.of_version?.set(apk!!.versionName)
        var size=FileUtils.getSize(path)
        mAppUploadActivityViewModel?.of_size?.set(size)
        //wrapper appinfobean
        mAppInfoBean.app_size=size
        mAppInfoBean.app_version=apk!!.versionName
        mAppInfoBean.app_package_name=apk!!.packageName


        var iconPath= MyFileUtils.saveDrawableAsImage(this,apk?.icon,apk?.name)

        //set app_icon  cid
        mAppUploadActivityViewModel?.UploadFile(iconPath!!,AppUploadActivityViewModel.Type.APP_ICON)

        //set app_file cid
        mAppUploadActivityViewModel?.UploadFile(path!!,AppUploadActivityViewModel.Type.APP_FILE)

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
            //请求网络提交上传的app信息:
            mAppUploadActivityViewModel?.commitPublish(mAppInfoBean)
        }

        fun UploadImgs(){
            mAppUploadActivityViewModel?.let {
                var datas=it.mNinePicturesAdapter.get()?.data!!
                if(datas.size==4)it.uploadImagesToIPFS(datas)
            }
        }

        fun ShowCategoryDialog(){
            //解析一个布局
            XPopup.Builder(this@AppUploadActivity)
                .asCustom(mAppUploadActivityViewModel?.let {
                    CategorySelectDialog(this@AppUploadActivity, it)
                })
                .show()



        }


    }

    private val nameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var appname=s.toString().trim()
                mAppUploadActivityViewModel?.of_name?.set(appname)
                mAppInfoBean.app_name=appname
            }
        }
    }

    private val subTitleTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            if(!TextUtils.isEmpty(s)){
                var subtitle=s.toString().trim()
                mAppUploadActivityViewModel?.of_subtitle?.set(subtitle)
                mAppInfoBean.app_subtitle=subtitle
            }

        }
    }

    private val overrviewTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            if(!TextUtils.isEmpty(s)){
                var overrview=s.toString().trim()
                mAppUploadActivityViewModel?.of_overrView?.set(overrview)
                mAppInfoBean.app_overrview=overrview
            }

        }
    }

    private val twitterTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            if(!TextUtils.isEmpty(s)){
                var twitter=s.toString().trim()
                mAppUploadActivityViewModel?.of_twitter?.set(twitter)
                mAppInfoBean.app_twitter=twitter
            }

        }
    }

    private val telegramTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {

            if(!TextUtils.isEmpty(s)){
                var telegram=s.toString().trim()
                mAppUploadActivityViewModel?.of_telegram?.set(telegram)
                mAppInfoBean.app_tg=telegram
            }

        }
    }

    private val officialTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            if(!TextUtils.isEmpty(s)){
                var official=s.toString().trim()
                mAppUploadActivityViewModel?.of_official?.set(official)
                mAppInfoBean.app_offical=official
            }

        }
    }


    private val downloadCountTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            if(!TextUtils.isEmpty(s)){
                var count=s.toString().trim()
                mAppUploadActivityViewModel?.of_download_count?.set(count)
                mAppInfoBean.app_download_count=count.toInt()
            }

        }
    }

    private val checkChange:SwitchButton.OnCheckedChangeListener=
        OnCheckedChangeListener { view, isChecked ->
            mAppUploadActivityViewModel?.of_show?.set(isChecked)
            mAppInfoBean.app_show_immediately=if(isChecked)"1" else "0"
        }




}