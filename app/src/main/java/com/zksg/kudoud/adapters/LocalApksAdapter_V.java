package com.zksg.kudoud.adapters;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.AppDetailActivity;
import com.zksg.kudoud.activitys.WeightDatasActivity;
import com.zksg.kudoud.beans.FileApkItem;
import com.zksg.lib_api.beans.HomeItem;

import java.util.List;

public class LocalApksAdapter_V extends BaseQuickAdapter<FileApkItem, BaseViewHolder> {

    public LocalApksAdapter_V(int layoutResId) {
        super(layoutResId);
    }


    public LocalApksAdapter_V(int layoutResId, @Nullable List<FileApkItem> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{
            switch (position){
                case 0:
                    getContext().startActivity(new Intent(getContext(), AppDetailActivity.class));
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
    protected void convert(@NonNull BaseViewHolder baseViewHolder, FileApkItem apk) {
        Log.d("convert", "convert: ");
        baseViewHolder.setImageDrawable(R.id.icon,apk.appInfo.getIcon());
        baseViewHolder.setText(R.id.title,apk.getAppInfo().getName());
        baseViewHolder.setText(R.id.apksize,apk.getSize()+"");

    }
}
