package com.zksg.kudoud.state;


import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kunminx.architecture.ui.state.State;
import com.kunminx.architecture.utils.Utils;
import com.zksg.kudoud.R;
import com.zksg.kudoud.widgets.NinePicturesAdapter;
import com.zksg.lib_api.beans.EnvBean;

import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class AppUploadActivityViewModel extends ViewModel {
    public ObservableField<List<EnvBean>> datas = new ObservableField<>();
    public ObservableField<NinePicturesAdapter> mNinePicturesAdapter = new ObservableField<>();
    public  ObservableField<String> of_name=new ObservableField<>();
    public  ObservableField<String> of_subtitle=new ObservableField<>();
    public  ObservableField<Drawable> of_icon=new ObservableField<>();
    public  ObservableField<Boolean> of_show=new ObservableField<>();
    public  State<String> of_size=new State<>("None");
    public  State<String> of_version=new State<>("None");
    public  State<String> of_category=new State<>("None");




}
