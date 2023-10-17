package com.zksg.kudoud.state

import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.IPFSManager
import com.zksg.kudoud.widgets.NinePicturesAdapter
import com.zksg.lib_api.beans.EnvBean
import com.zksg.lib_api.beans.ResponsPublishApk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Objects

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class AppUploadDappActivityViewModel : BaseLoadingViewModel() {

    var open=State<Boolean>(false)

    var datas = ObservableField<List<EnvBean>>()
    @JvmField
    var mNinePicturesAdapter = ObservableField<NinePicturesAdapter>()
    var of_name = ObservableField<String>()
//    var app_package_name = ObservableField<String>()
    var of_subtitle = ObservableField<String>()
    var of_overrView = ObservableField<String>()
    var of_twitter = ObservableField<String>()
    var of_telegram = ObservableField<String>()
    var of_official = ObservableField<String>()
    var of_download_count = ObservableField<String>()
    @JvmField
    var of_icon = ObservableField<Drawable>()
    var of_show = ObservableField<Boolean>()
    @JvmField
    var of_size = State("None")
    @JvmField
    var of_version = State("None")
    @JvmField
    var of_category = State("None")
    var of_category_req = MutableResult<String>()
    var cid = MutableResult<String>()
    var cid_appicon = MutableResult<String>()
    var cid_app_screen_4 = MutableResult<List<String>>(listOf())

    /*----------------------Respons result----------------------------*/
    val mpublishResult = MutableResult<DataResult<ResponsPublishApk>?>()
    fun UploadFile(path: String, type: Type){

        viewModelScope.launch {
            loadingVisible.value=true
           var result= withContext(Dispatchers.IO){
               IPFSManager.uploadFile(path!!)
           }
            when(type){
                Type.APP_ICON -> {
                    if(!TextUtils.isEmpty(result))cid_appicon?.value=result

                }
                Type.APP_FILE -> {
                    loadingVisible.value=false
                    if(!TextUtils.isEmpty(result)) cid?.value=result
                }


            }


        }


    }



    //upload files
    fun uploadImagesToIPFS(imageFiles: List<String>){

        viewModelScope.launch {
            loadingVisible.value=true
            var result= withContext(Dispatchers.IO){
                //use map to for  and return a new list
                imageFiles.map { imageFile -> IPFSManager.uploadFile(imageFile)  }

            }
            loadingVisible.value=false
            cid_app_screen_4.value=result
        }

    }


    fun commitPublish(info: AppInfoBean){

        viewModelScope.launch {
             withContext(Dispatchers.IO){
                 loadingVisible.postValue(true)
                DataRepository.getInstance().commitPublishApk(info){
                    loadingVisible.postValue(false)
                    mpublishResult.postValue(it)
                }

            }
        }



    }




    public enum class Type{
        APP_ICON,
        APP_FILE,
        APP_SCREEN_4
    }

}