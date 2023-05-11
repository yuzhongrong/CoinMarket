package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.zksg.kudoud.beans.LineChartBean;

import java.util.List;


public class CommonDatasViewModel extends ViewModel {
    public ObservableField<List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO>> datas=new ObservableField<>();
}
