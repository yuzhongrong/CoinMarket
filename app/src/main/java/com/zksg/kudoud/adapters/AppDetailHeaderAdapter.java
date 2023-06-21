package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.constants.config.host;
import static com.zksg.kudoud.constants.config.port;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;

import java.util.List;

public class AppDetailHeaderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AppDetailHeaderAdapter(int layoutResId) {
        super(layoutResId);
    }


    public AppDetailHeaderAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{

        });
    }

//    public CommonAdapter(@Nullable List<HomeItem> data) {
//
//        this(R.layout.item_today_health, data);
//        Log.d("convert", "convert: "+data.size());
//
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String screen) {
        Log.d("convert", "convert: ");
        String url="http://"+host+":"+port+"/ipfs/"+screen;
        ImageLoaderManager.getInstance().displayImageForCorner(baseViewHolder.getView(R.id.icon),url,15);
    }
}
