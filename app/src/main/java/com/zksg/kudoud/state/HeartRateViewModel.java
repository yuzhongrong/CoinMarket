package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zksg.kudoud.adapters.DialogMusicPagerAdapter;
import com.zksg.kudoud.adapters.HeartRatePagerAdapter;

public class HeartRateViewModel extends ViewModel {
    public ObservableField<HeartRatePagerAdapter> adapter=new ObservableField();
    public ObservableField<String[]> indicatorTitle=new ObservableField<>();

}
