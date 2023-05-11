package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zksg.kudoud.beans.MusicChannelEnum;
import com.zksg.kudoud.fragments.CycMusicFragment;
import com.zksg.kudoud.fragments.HomeFragment;
import com.zksg.kudoud.fragments.MeFragment;
//import com.zksg.kudoud.fragments.LocalMusicFragment;
//import com.zksg.kudoud.fragments.MyMusicFragment;

public class DialogMusicPagerAdapter extends FragmentStatePagerAdapter {

    private MusicChannelEnum[] channels;

    @SuppressLint("WrongConstant")
    public DialogMusicPagerAdapter(@NonNull FragmentManager fm, MusicChannelEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type=channels[position].getValue();
        switch (type){
            case MusicChannelEnum.CYCMUSIC_ID:
                return new CycMusicFragment();
            case MusicChannelEnum.MYMUSIC_ID:
                return new CycMusicFragment();
            case MusicChannelEnum.LOCALMUSIC_ID:
                return new CycMusicFragment();
            default:
                break;
        }
        throw new IllegalArgumentException("cannot find fragment");
    }

    @Override
    public int getCount() {
        return channels.length;
    }
}
