package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.kunminx.architecture.utils.Utils
import com.zksg.kudoud.beans.LineChartBean
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.kudoud.utils.TimeUtils
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.CommonResponse
import com.zksg.lib_api.beans.EnvBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class MemeDetailActivityViewModel : BaseLoadingViewModel() {

    var meme_webchart = ObservableField<String>()


    @JvmField
    var datas = ObservableField<List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO>>()
    @JvmField
    var spot = State(TimeUtils.Interval.HOUR)

    init {
        val json = LocalJsonResolutionUtils.getJson(Utils.getApp(), "chart_heartrate_day.json")
        val bean = LocalJsonResolutionUtils.JsonToObject(json, LineChartBean::class.java)
        val beans = bean.griD0.result.clientAccumulativeRate
        Log.e("HeartRateDayFragmentViewModel:", beans.size.toString() + ";")
        datas.set(beans)
    }



}