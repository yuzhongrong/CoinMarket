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
import com.zksg.kudoud.beans.SearchHistory;
import com.zksg.lib_api.beans.AppInfoBean;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private int layout_default= R.layout.item_today_app_h;
    private int layout_history= R.layout.search_history_layout;

    private String[] categorys;
    public SearchAdapter(int layoutResId) {
        super(layoutResId);
    }


    public SearchAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{

        });
    }



    public SearchAdapter(int layoutResId, @Nullable List data, String[] category) {
        super(layoutResId,data);
        this.categorys=category;
        setOnItemClickListener((adapter,view,position)->{
            Intent i=new Intent(getContext(), AppDetailActivity.class);
            Object o=this.getData().get(position);
            AppInfoBean bean=(AppInfoBean)o;
            i.putExtra("appinfo",bean);
            getContext().startActivity(i);
        });


    }


    @Override
    protected int getDefItemViewType(int position) {

        if(getData()!=null&&getData().size()>0){

            if(getData().get(position) instanceof SearchHistory)
                return layout_history;
            else
                return layout_default;

        }

        return super.getDefItemViewType(position);
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateDefViewHolder(parent, viewType);
    }








    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Object apk) {
        if(apk instanceof AppInfoBean){ //默认搜索情况
            AppInfoBean apkbean=(AppInfoBean) apk;
            int index=Integer.parseInt(apkbean.getApp_category());
            String category=categorys[index];
            Log.d("convert", "convert:"+index +"+"+category);
            String url=ipfs_base_url+apkbean.getApp_icon();
            ImageLoaderManager.getInstance().displayImageForView(baseViewHolder.getView(R.id.icon),url);
            baseViewHolder.setText(R.id.title,apkbean.getApp_name());
            baseViewHolder.setText(R.id.type,category);
            baseViewHolder.setText(R.id.desctiption,apkbean.getApp_subtitle());
        }else if(apk instanceof SearchHistory){//历史记录
            SearchHistory historybean=(SearchHistory) apk;


        }

    }
}
