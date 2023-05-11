package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.zksg.kudoud.adapters.HeartRatePagerAdapter;
import com.zksg.kudoud.adapters.WeightPagerAdapter;

public class WeightViewModel extends ViewModel {
    public ObservableField<WeightPagerAdapter> adapter=new ObservableField();
    public ObservableField<String[]> indicatorTitle=new ObservableField<>();

}
