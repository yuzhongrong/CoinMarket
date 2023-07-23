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
import com.zksg.kudoud.activitys.NotifyActivity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.CommonResponse
import com.zksg.lib_api.beans.EnvBean
import com.zksg.lib_api.beans.NotifyBean
import com.zksg.lib_api.beans.ResponsPublishApk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class NotifyActivityViewModel : BaseLoadingViewModel() {

    @JvmField
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var notifResults= MutableResult<List<NotifyBean>>()
    var refreshOrload=MutableResult<Boolean>()

    fun getNotifyList(page:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
//                loadingVisible.postValue(true)
                DataRepository.getInstance().getNotifyList(page,50){
//                    loadingVisible.postValue(false)
                    if(it.responseStatus.isSuccess){
                        notifResults.postValue(it.result.data.list)
                    }
                    refreshOrload.postValue(false)

                }

            }
        }



    }




}