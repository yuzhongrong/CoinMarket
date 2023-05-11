package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;
import com.kunminx.architecture.ui.state.State;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.lib_api.baby.BabyInfo;

import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class DeviceSettingViewModel extends BaseLoadingViewModel {
    //设备显示中英文
    public  State<String> deviceLanguage = new State<>("中文");
    public ObservableField<List<BabyInfo>> datas=new ObservableField<>();



}
