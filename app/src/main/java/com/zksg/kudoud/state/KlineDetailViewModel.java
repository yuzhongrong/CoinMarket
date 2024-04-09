package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.customviews.NoTouchScrollViewpager;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class KlineDetailViewModel extends ViewModel {
    public ObservableField<SimpleFragmentPagerAdapter> tabAdapter=new ObservableField<>();
    public ObservableField<NoTouchScrollViewpager> viewpager=new ObservableField<>();

}
