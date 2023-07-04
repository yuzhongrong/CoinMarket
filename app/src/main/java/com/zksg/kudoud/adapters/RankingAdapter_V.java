package com.zksg.kudoud.adapters;

import static com.netease.lib_network.constants.config.ipfs_base_url;

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

public class RankingAdapter_V extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> {


    private String[] categorys;
    public RankingAdapter_V(int layoutResId) {
        super(layoutResId);
    }
    public RankingAdapter_V(int layoutResId,String[] categorys) {
        super(layoutResId);
        this.categorys=categorys;
        setOnItemClickListener((adapter,view,position)->{
            Intent i=new Intent(getContext(), AppDetailActivity.class);
            i.putExtra("appinfo",this.getData().get(position));
            getContext().startActivity(i);
        });
    }

    public RankingAdapter_V(int layoutResId, @Nullable List<AppInfoBean> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{

        });
    }
    public RankingAdapter_V(int layoutResId, @Nullable List<AppInfoBean> data, String[] category) {
        super(layoutResId,data);
        this.categorys=category;
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
    protected void convert(@NonNull BaseViewHolder baseViewHolder, AppInfoBean apk) {
        int index=Integer.parseInt(apk.getApp_category());
        String category=categorys[index];
        Log.d("convert", "convert:"+index +"+"+category);
        String url=ipfs_base_url+apk.getApp_icon();
        ImageLoaderManager.getInstance().displayImageForView(baseViewHolder.getView(R.id.icon),url);
        baseViewHolder.setText(R.id.title,apk.getApp_name());
        baseViewHolder.setText(R.id.type,category);
        baseViewHolder.setText(R.id.desctiption,apk.getApp_subtitle());

    }
}
