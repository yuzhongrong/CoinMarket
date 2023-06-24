package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.kunminx.architecture.utils.Utils
import com.zksg.kudoud.beans.LineChartBean
import com.zksg.kudoud.beans.LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.kudoud.utils.TimeUtils
import com.zksg.lib_api.beans.AppInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletFragmentViewModel : BaseLoadingViewModel() {
    var datas = ObservableField<List<ClientAccumulativeRateDTO>>()
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var spot = State(TimeUtils.Interval.HOUR)
    val mHotApks = MutableResult<List<AppInfoBean>>()

    //get apps by category
    fun getCategoryApps(page:Int,pageSize:Int,category:String){
        viewModelScope.launch {
            loadingVisible.value=true
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getAppinfoList(page,pageSize,category){
                    if(it.result.data!=null) mHotApks.postValue(it.result.data.list)
                }
            }
            loadingVisible.value=false

        }
    }



}