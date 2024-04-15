package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter;
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.customviews.NoTouchScrollViewpager;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class CoinsDetailViewModel extends ViewModel {
    public ObservableField<CommonKlineDataPagerAdapter> tabAdapter=new ObservableField<>();
    public ObservableField<NoTouchScrollViewpager> viewpager=new ObservableField<>();
    public ObservableField<String[]> indicatorTitle=new ObservableField<>();
    {
        indicatorTitle.set(new String[]{"5M","1H","6H","24H"});
    }


}
