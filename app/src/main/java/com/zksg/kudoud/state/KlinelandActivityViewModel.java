package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zksg.kudoud.customviews.WonderfulViewPager;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class KlinelandActivityViewModel extends ViewModel {
//    public ObservableField<List<FeedTip>> datas=new ObservableField<>();

    public ObservableField<PagerAdapter> adapter=new ObservableField<>();
    public ObservableField<WonderfulViewPager> viewpager=new ObservableField<>();


}
