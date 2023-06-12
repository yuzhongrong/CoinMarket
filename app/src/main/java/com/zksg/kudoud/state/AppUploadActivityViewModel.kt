package com.zksg.kudoud.state

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.zksg.kudoud.beans.FileApkItem
import com.zksg.kudoud.utils.IPFSManager
import com.zksg.kudoud.widgets.NinePicturesAdapter
import com.zksg.lib_api.beans.EnvBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.cert.CertPath

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class AppUploadActivityViewModel : ViewModel() {
    var datas = ObservableField<List<EnvBean>>()
    @JvmField
    var mNinePicturesAdapter = ObservableField<NinePicturesAdapter>()
    var of_name = ObservableField<String>()
    var of_subtitle = ObservableField<String>()
    @JvmField
    var of_icon = ObservableField<Drawable>()
    var of_show = ObservableField<Boolean>()
    @JvmField
    var of_size = State("None")
    @JvmField
    var of_version = State("None")
    @JvmField
    var of_category = State("None")

    var cid = MutableResult<String>()

    fun UploadFile(path: String){

        viewModelScope.launch {
           var result= withContext(Dispatchers.IO){
               IPFSManager.uploadFile(path!!)
           }
            cid.value=result;

        }


    }
}