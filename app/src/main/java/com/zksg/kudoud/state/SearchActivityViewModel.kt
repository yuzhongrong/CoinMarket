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

class SearchActivityViewModel : BaseLoadingViewModel() {
    var datas = ObservableField<List<ClientAccumulativeRateDTO>>()
    var searchAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var spot = State(TimeUtils.Interval.HOUR)
    val mSearchApks = MutableResult<List<AppInfoBean>>()

    //get apps by category
    fun getSearchApps(page:Int,pageSize:Int,appanme:String){
        viewModelScope.launch {

            withContext(Dispatchers.IO){
                DataRepository.getInstance().getAppinfoListSearch(page,pageSize,appanme){
                    if(it.result.data!=null) mSearchApks.postValue(it.result.data.list)
                }
            }



        }
    }
    fun getSearchAppsForButton(page:Int,pageSize:Int,appanme:String){
        viewModelScope.launch {
             withContext(Dispatchers.IO){
                 loadingVisible.postValue(true)
                DataRepository.getInstance().getAppinfoListSearchDelay(page,pageSize,appanme){
                    if(it.result.data!=null) mSearchApks.postValue(it.result.data.list)
                    loadingVisible.postValue(false)
                }
            }
        }
    }



}