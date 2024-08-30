package com.zksg.kudoud.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zksg.kudoud.beans.CategoryEnum;
import com.zksg.kudoud.beans.CommonCategoryDataEnum;
import com.zksg.kudoud.fragments.CategoryCommonFragment;
import com.zksg.kudoud.fragments.MemeCategoryCommonFragment;

public class MemeCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private CategoryEnum[] channels;

    @SuppressLint("WrongConstant")
    public MemeCategoryPagerAdapter(@NonNull FragmentManager fm, CategoryEnum[] channels) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.channels=channels;
    }




    @NonNull
    @Override
    public Fragment getItem(int position) {
        String category=channels[position].getValue();
       return MemeCategoryCommonFragment.Companion.newInstance(category);

    }

    @Override
    public int getCount() {
        return channels.length;
    }
}
