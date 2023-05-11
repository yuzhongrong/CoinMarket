package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//TODO:tip dialog无法使用这种方式传递
public class DeviceMusicListViewModel extends ViewModel {
 //TODO:tip 使用ObservableField 单向绑定 从数据->页面
    public ObservableField<String[]> titles=new ObservableField<>();
    public ObservableField<FragmentStateAdapter> adapter=new ObservableField<>();
    {
        titles.set(new String[]{"环境音","我的音乐","本地音乐"});
    }

}
