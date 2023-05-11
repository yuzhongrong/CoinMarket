package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.CommonDataEnum;
import com.zksg.kudoud.fragments.HeartRateDayFragment;
import com.zksg.kudoud.fragments.HeartRateWeekFragment;

public class HeartRatePagerAdapter extends FragmentStatePagerAdapter {

    private CommonDataEnum[] channels;

    @SuppressLint("WrongConstant")
    public HeartRatePagerAdapter(@NonNull FragmentManager fm, CommonDataEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type=channels[position].getValue();
        switch (type){
            case CommonDataEnum.HEARTRATEDAY_ID:
                return new HeartRateDayFragment();
            case CommonDataEnum.HEARTRATEWEEK_ID:
                return new HeartRateWeekFragment();
            case CommonDataEnum.HEARTRATEMONTH_ID:
                return new HeartRateDayFragment();
            case CommonDataEnum.HEARTRATEYEAR_ID:
                return new HeartRateDayFragment();
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
