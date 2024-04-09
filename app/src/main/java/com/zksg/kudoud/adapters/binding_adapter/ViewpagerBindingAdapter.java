package com.zksg.kudoud.adapters.binding_adapter;

import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.zksg.kudoud.customviews.WonderfulViewPager;

public class ViewpagerBindingAdapter {

    @BindingAdapter(value = {"initKlineViewpagerIntercept"},requireAll = false)
    public static void initKlineViewpagerIntercept(WonderfulViewPager mWonderfulViewPager,boolean intercept) {

        if(mWonderfulViewPager==null)return;
        mWonderfulViewPager.setNotIntercept(intercept);

    }





}
