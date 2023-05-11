package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.lib_api.playlist.BasicMusicInfo;

import java.util.List;


public class CycMusicFragmentViewModel extends ViewModel {
    public ObservableField<List<BasicMusicInfo>> datas=new ObservableField<>();

}
