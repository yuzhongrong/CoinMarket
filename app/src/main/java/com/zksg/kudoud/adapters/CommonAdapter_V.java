package com.zksg.kudoud.adapters;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.HeartRateDatasActivity;
import com.zksg.kudoud.activitys.WeightDatasActivity;
import com.zksg.lib_api.beans.HomeItem;

import java.util.List;

public class CommonAdapter_V extends BaseQuickAdapter<HomeItem, BaseViewHolder> {

    public CommonAdapter_V(int layoutResId) {
        super(layoutResId);
    }


    public CommonAdapter_V(int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{
            switch (position){
                case 0:
                    getContext().startActivity(new Intent(getContext(), HeartRateDatasActivity.class));
                    break;
                case 1:
                    break;
                case 2:
                    getContext().startActivity(new Intent(getContext(), WeightDatasActivity.class));

                    break;

            }
        });
    }

//    public CommonAdapter(@Nullable List<HomeItem> data) {
//
//        this(R.layout.item_today_health, data);
//        Log.d("convert", "convert: "+data.size());
//
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, HomeItem homeItems) {
        Log.d("convert", "convert: ");
        baseViewHolder.setImageResource(R.id.icon,homeItems.getIconId());
        baseViewHolder.setText(R.id.title,homeItems.getValue());

    }
}
