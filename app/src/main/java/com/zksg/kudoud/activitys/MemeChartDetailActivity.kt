package com.zksg.kudoud.activitys

import android.os.Bundle
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.constants.config.*
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.beans.LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO
import com.zksg.kudoud.state.MemeDetailActivityViewModel
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.kudoud.utils.TimeUtils
import com.zksg.lib_api.beans.AppInfoBean

class MemeChartDetailActivity : BaseDialogActivity() {
    private var mMemeDetailActivityViewModel: MemeDetailActivityViewModel? = null
    private  var appinfo:AppInfoBean?=null

    var datas = ObservableField<List<ClientAccumulativeRateDTO>>()
    var spot = State<TimeUtils.Interval>(TimeUtils.Interval.HOUR)



    override fun initViewModel() {
        mMemeDetailActivityViewModel = getActivityScopeViewModel(
            MemeDetailActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initData()
    }

    override fun getDataBindingConfig(): DataBindingConfig {

        return DataBindingConfig(R.layout.activity_meme_detail, BR.vm, mMemeDetailActivityViewModel!!)

    }

    private fun initData() {



    }







}
