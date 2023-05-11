package com.zksg.kudoud.state;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.state.State;
import com.kunminx.architecture.utils.Utils;
import com.zksg.kudoud.beans.LineChartBean;
import com.zksg.kudoud.utils.LocalJsonResolutionUtils;
import com.zksg.kudoud.utils.TimeUtils;

import java.util.List;


public class HeartRateDayFragmentViewModel extends ViewModel {
    public ObservableField<List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO>> datas=new ObservableField<>();
    public State<TimeUtils.Interval> spot = new State<>(TimeUtils.Interval.HOUR);
    {
        String json= LocalJsonResolutionUtils.getJson(Utils.getApp(),"chart_heartrate_day.json");
        LineChartBean bean= LocalJsonResolutionUtils.JsonToObject(json,LineChartBean.class);
        List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO> beans = bean.getGRID0().getResult().getClientAccumulativeRate();
        Log.e("HeartRateDayFragmentViewModel:",beans.size()+";");
        datas.set(beans);
    }

}
