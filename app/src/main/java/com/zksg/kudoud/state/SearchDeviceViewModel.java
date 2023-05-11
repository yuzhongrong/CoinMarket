package com.zksg.kudoud.state;


import androidx.databinding.ObservableField;

import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.lib_api.beans.DeviceBean;

import java.util.List;

public class SearchDeviceViewModel extends BaseLoadingViewModel {
    //TODO tip 只能赋值一次 ObservableField 单向绑定 ui->view
   public final ObservableField<List<DeviceBean>> devices=new ObservableField<>();
}
