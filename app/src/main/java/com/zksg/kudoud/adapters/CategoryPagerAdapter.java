package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.CommonCategoryDataEnum;
import com.zksg.kudoud.fragments.CategoryCommonFragment;

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
                return new CategoryCommonFragment(CommonCategoryDataEnum.WALLET_ID);
            case CommonCategoryDataEnum.EXCHANGE_ID:
                return new CategoryCommonFragment(CommonCategoryDataEnum.EXCHANGE_ID);
            case CommonCategoryDataEnum.DEX_ID:
                return new CategoryCommonFragment(CommonCategoryDataEnum.DEX_ID);
            case CommonCategoryDataEnum.OTHER_ID:
                return new CategoryCommonFragment(CommonCategoryDataEnum.DEX_ID);
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
