package com.zksg.kudoud.activitys;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.CycMusicAdapter;
import com.zksg.kudoud.state.MusicSettingViewModel;
import com.zksg.kudoud.state.ShakeSettingViewModel;
import com.zksg.lib_api.playlist.BasicMusicInfo;

import java.util.ArrayList;
import java.util.List;

public class MusicSettingctivity extends BaseActivity {
    MusicSettingViewModel mMusicSettingViewModel;

    @Override
    protected void initViewModel() {
        mMusicSettingViewModel=getActivityScopeViewModel(MusicSettingViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_setting_music, BR.vm,mMusicSettingViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.adapter,new CycMusicAdapter(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(()->{
            initData();
        },280);
    }


    private void initData(){
        List<BasicMusicInfo> list=new ArrayList<>();
        list.add(new BasicMusicInfo(true,"真的爱你","3:00"));
        list.add(new BasicMusicInfo(true,"光辉岁月","2:00"));
        list.add(new BasicMusicInfo(true,"喜欢你","4:50"));
        list.add(new BasicMusicInfo(true,"小苹果","2:51"));
        list.add(new BasicMusicInfo(true,"突然的自我","3:50"));
        list.add(new BasicMusicInfo(true,"尖峰时刻","6:50"));
        list.add(new BasicMusicInfo(true,"红尘","3:38"));
        list.add(new BasicMusicInfo(true,"茶道夫","4:58"));
        list.add(new BasicMusicInfo(true,"总是错过","3:59"));
        list.add(new BasicMusicInfo(true,"爱不起","4:49"));
        list.add(new BasicMusicInfo(true,"突发","3:12"));
        list.add(new BasicMusicInfo(true,"感染源","2:20"));
        list.add(new BasicMusicInfo(true,"小洋人","3:52"));

        mMusicSettingViewModel.datas.set(list);

    }

    public class ClickProxy {


    }
}
