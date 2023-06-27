package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.constants.config.ipfs_base_url;

import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.AppDetailActivity;
import com.zksg.lib_api.beans.AppInfoBean;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> {


    private String[] categorys;
    public SearchAdapter(int layoutResId) {
        super(layoutResId);
    }


    public SearchAdapter(int layoutResId, @Nullable List<AppInfoBean> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{

        });
    }
    public SearchAdapter(int layoutResId, @Nullable List<AppInfoBean> data, String[] category) {
        super(layoutResId,data);
        this.categorys=category;
        setOnItemClickListener((adapter,view,position)->{
            Intent i=new Intent(getContext(), AppDetailActivity.class);
            i.putExtra("appinfo",this.getData().get(position));
            getContext().startActivity(i);
        });


    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateDefViewHolder(parent, viewType);
    }








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
