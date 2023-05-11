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

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class HealRecordViewModel extends ViewModel {
    public ObservableField<List<LineChartBean.GRID0DTO.ResultDTO.CompositeIndexShenzhenDTO>> datas=new ObservableField<>();
    {
        String json= LocalJsonResolutionUtils.getJson(Utils.getApp(),"sleep.json");
        LineChartBean bean= LocalJsonResolutionUtils.JsonToObject(json,LineChartBean.class);
        List<LineChartBean.GRID0DTO.ResultDTO.CompositeIndexShenzhenDTO> beans = bean.getGRID0().getResult().getCompositeIndexShenzhen();
        Log.e("HealRecordViewModel:",beans.size()+";");
        datas.set(beans);
    }

}
