package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.constants.config.ipfs_base_url;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.AppDetailActivity;
import com.zksg.lib_api.beans.AppInfoBean;

import java.util.List;

public class HomeRecentAdapter extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> {

    public HomeRecentAdapter(int layoutResId) {
        super(layoutResId);
    }


    public HomeRecentAdapter(int layoutResId, @Nullable List<AppInfoBean> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{
            Intent i=new Intent(getContext(), AppDetailActivity.class);
            i.putExtra("appinfo",this.getData().get(position));
            getContext().startActivity(i);
        });
    }

//    public CommonAdapter(@Nullable List<HomeItem> data) {
//
//        this(R.layout.item_today_health, data);
//        Log.d("convert", "convert: "+data.size());
//
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, AppInfoBean homeItem) {
        Log.d("convert", "convert: ");
        String url=ipfs_base_url+homeItem.getApp_icon();
        ImageLoaderManager.getInstance().displayImageForView(baseViewHolder.getView(R.id.icon),url);
        baseViewHolder.setText(R.id.title,homeItem.getApp_name());

    }
}
