package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.zksg.kudoud.adapters.DialogMusicPagerAdapter;

public class MusicPlaylistActivityViewModel extends ViewModel {
    public ObservableField<DialogMusicPagerAdapter> adapter=new ObservableField();
    public ObservableField<String[]> indicatorTitle=new ObservableField<>();

}
