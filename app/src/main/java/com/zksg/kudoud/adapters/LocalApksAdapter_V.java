package com.zksg.kudoud.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

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


    public LocalApksAdapter_V(Activity activity,int layoutResId, @Nullable List<FileApkItem> data) {
        super(layoutResId,data);
       addChildClickViewIds(R.id.btn);
       setOnItemChildClickListener((adapter,view,position)->{
           if(view.getId()==R.id.btn){
               Intent i=activity.getIntent().putExtra("PATH", data.get(position).file.getPath());
               activity.setResult(Activity.RESULT_OK,i);
               activity.finish();
           }

       });
    }


    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, FileApkItem apk) {
        Log.d("convert", "convert: ");
        baseViewHolder.setImageDrawable(R.id.icon,apk.appInfo.getIcon());
        baseViewHolder.setText(R.id.title,apk.getAppInfo().getName());
        baseViewHolder.setText(R.id.apksize,apk.getSize()+"");

    }
}
