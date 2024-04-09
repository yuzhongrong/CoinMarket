package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.state.State
import com.kunminx.architecture.utils.Utils
import com.zksg.kudoud.beans.LineChartBean
import com.zksg.kudoud.beans.LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.kudoud.utils.TimeUtils

class HeartRateDayFragmentViewModel : ViewModel() {
    @JvmField
    var datas = ObservableField<List<ClientAccumulativeRateDTO>>()
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