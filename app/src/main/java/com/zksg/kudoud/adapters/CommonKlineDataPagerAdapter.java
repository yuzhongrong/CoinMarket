package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.Kline24ChangeChannelEnum;
import com.zksg.kudoud.beans.MusicChannelEnum;
import com.zksg.kudoud.fragments.Chart1HFragment;
import com.zksg.kudoud.fragments.Chart24HFragment;
import com.zksg.kudoud.fragments.Chart5MFragment;
import com.zksg.kudoud.fragments.Chart6HFragment;
import com.zksg.kudoud.fragments.ChartKLineFragment;
import com.zksg.kudoud.fragments.CycMusicFragment;
//import com.zksg.kudoud.fragments.LocalMusicFragment;
//import com.zksg.kudoud.fragments.MyMusicFragment;

public class CommonKlineDataPagerAdapter extends FragmentStatePagerAdapter {

    private Kline24ChangeChannelEnum[] channels;

    @SuppressLint("WrongConstant")
    public CommonKlineDataPagerAdapter(@NonNull FragmentManager fm, Kline24ChangeChannelEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type=channels[position].getValue();
        switch (type){
            case Kline24ChangeChannelEnum.K_5M_ID:
                return  Chart5MFragment.newInstance(Kline24ChangeChannelEnum.K_5M_ID, false);
            case Kline24ChangeChannelEnum.K_1H_ID:
                return Chart1HFragment.newInstance(Kline24ChangeChannelEnum.K_1H_ID, false);
            case Kline24ChangeChannelEnum.K_6H_ID:
                return Chart6HFragment.newInstance(Kline24ChangeChannelEnum.K_6H_ID, false);
            case Kline24ChangeChannelEnum.K_24H_ID:
                return Chart24HFragment.newInstance(Kline24ChangeChannelEnum.K_24H_ID, false);
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
