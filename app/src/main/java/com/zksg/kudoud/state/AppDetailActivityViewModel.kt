package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.zksg.kudoud.R
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.CommonResponse
import com.zksg.lib_api.beans.EnvBean
import com.zksg.lib_api.beans.ResponsPublishApk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class AppDetailActivityViewModel : BaseLoadingViewModel() {
    var datas = ObservableField<List<EnvBean>>()
    @JvmField
    var appDetailAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var app_name = ObservableField<String>()
    @JvmField
    var app_size = ObservableField<String>()
    @JvmField
    var app_category = ObservableField<String>()
    var showdownload_or_open=ObservableField<String>()
    @JvmField
    var app_version = ObservableField<String>()
    @JvmField
    var app_overrview = ObservableField<String>()
    var app_twitter = ObservableField<String>()
    var app_telegram = ObservableField<String>()
    var app_offcail = ObservableField<String>()
    var app_file = ObservableField<String>()
    var app_download_count = State("0")
    val updateappinfoResult = MutableResult<DataResult<CommonResponse<Any>>?>()


    fun updateAppinfo(info: AppInfoBean){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                loadingVisible.postValue(true)
                DataRepository.getInstance().updateAppinfo(info){
                    loadingVisible.postValue(false)
                    updateappinfoResult.postValue(it)

                }

            }
        }



    }




}