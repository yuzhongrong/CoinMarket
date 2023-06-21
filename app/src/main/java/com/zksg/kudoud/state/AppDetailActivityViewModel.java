package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zksg.lib_api.beans.EnvBean;

import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class AppDetailActivityViewModel extends ViewModel {
    public ObservableField<List<EnvBean>> datas = new ObservableField<>();
    public ObservableField<BaseQuickAdapter> appDetailAdapter = new ObservableField<>();
    public ObservableField<String> app_name=new ObservableField<>();
    public ObservableField<String> app_size=new ObservableField<>();
    public ObservableField<String> app_category=new ObservableField<>();
    public ObservableField<String> app_version=new ObservableField<>();
    public ObservableField<String> app_overrview=new ObservableField<>();
    public ObservableField<String> app_twitter=new ObservableField<>();
    public ObservableField<String> app_telegram=new ObservableField<>();
    public ObservableField<String> app_offcail=new ObservableField<>();
    public ObservableField<String> app_file=new ObservableField<>();

}
