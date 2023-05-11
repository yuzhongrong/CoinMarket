package com.zksg.kudoud.state;


import androidx.databinding.ObservableField;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.lib_api.playlist.BasicMusicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class MusicSettingViewModel extends BaseLoadingViewModel {

    public ObservableField<List<BasicMusicInfo>> datas=new ObservableField<>();


}
