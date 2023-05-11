package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.DialogMusicPagerAdapter;
import com.zksg.kudoud.beans.MusicChannelEnum;
import com.zksg.kudoud.state.MusicPlaylistActivityViewModel;

public class ControlMusicPlayActivity extends BaseActivity {
    private MusicPlaylistActivityViewModel musicPlaylistActivityViewModel;
    @Override
    protected void initViewModel() {
        musicPlaylistActivityViewModel=getActivityScopeViewModel(MusicPlaylistActivityViewModel.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.test_music_setting, BR.vm,musicPlaylistActivityViewModel);
    }



    private void initData(){
        musicPlaylistActivityViewModel.indicatorTitle.set(new String[]{"环境音","我的音乐","本地音乐"});
        musicPlaylistActivityViewModel.adapter.set(new DialogMusicPagerAdapter(getSupportFragmentManager(),new MusicChannelEnum[]{  MusicChannelEnum.CYCMUSIC, MusicChannelEnum.MYMUSIC, MusicChannelEnum.LOCALMUSIC}));
    }
}
