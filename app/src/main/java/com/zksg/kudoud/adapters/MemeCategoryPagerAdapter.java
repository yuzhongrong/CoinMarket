package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.CommonCategoryDataEnum;
import com.zksg.kudoud.fragments.CategoryCommonFragment;
import com.zksg.kudoud.fragments.MemeCategoryCommonFragment;

public class MemeCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private CommonCategoryDataEnum[] channels;

    @SuppressLint("WrongConstant")
    public MemeCategoryPagerAdapter(@NonNull FragmentManager fm, CommonCategoryDataEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type=channels[position].getValue();
        switch (type){
            case CommonCategoryDataEnum.CHAT_ZX:
                return MemeCategoryCommonFragment.Companion.newInstance(CommonCategoryDataEnum.CHAT_ZX);
            case CommonCategoryDataEnum.NEW_PAIR_ID:
                return MemeCategoryCommonFragment.Companion.newInstance(CommonCategoryDataEnum.NEW_PAIR_ID);
            case CommonCategoryDataEnum.CHAT_UP_24:
                return MemeCategoryCommonFragment.Companion.newInstance(CommonCategoryDataEnum.CHAT_EX_24);
            case CommonCategoryDataEnum.COLLECT_ID:
                return MemeCategoryCommonFragment.Companion.newInstance(CommonCategoryDataEnum.COLLECT_ID);
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
