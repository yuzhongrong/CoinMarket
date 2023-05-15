package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.CommonCategoryDataEnum;
import com.zksg.kudoud.beans.CommonDataEnum;
import com.zksg.kudoud.fragments.HeartRateDayFragment;
import com.zksg.kudoud.fragments.HeartRateWeekFragment;
import com.zksg.kudoud.fragments.WalletFragment;

import org.jetbrains.annotations.NotNull;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private CommonCategoryDataEnum[] channels;

    @SuppressLint("WrongConstant")
    public CategoryPagerAdapter(@NonNull FragmentManager fm, CommonCategoryDataEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type=channels[position].getValue();
        switch (type){
            case CommonCategoryDataEnum.WALLET_ID:
                return new WalletFragment();
            case CommonCategoryDataEnum.EXCHANGE_ID:
                return new HeartRateWeekFragment();
            case CommonCategoryDataEnum.DEX_ID:
                return new HeartRateDayFragment();
            case CommonCategoryDataEnum.OTHER_ID:
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
