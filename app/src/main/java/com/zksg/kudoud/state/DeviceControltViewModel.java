package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;

public class DeviceControltViewModel extends ViewModel {
    public ObservableField<BaseQuickAdapter> adapter = new ObservableField<>();

}
