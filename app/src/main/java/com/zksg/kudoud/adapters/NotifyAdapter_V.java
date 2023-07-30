package com.zksg.kudoud.adapters;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.WebviewActivity;
import com.zksg.lib_api.beans.NotifyBean;

import java.util.List;

public class NotifyAdapter_V extends BaseQuickAdapter<NotifyBean, BaseViewHolder> {



    public NotifyAdapter_V(int layoutResId) {
        super(layoutResId);
        setOnItemClickListener((adapter,view,position)->{
            Intent intent=new Intent(getContext(), WebviewActivity.class);
            intent.putExtra("content",getData().get(position).getContent());
            intent.putExtra("title",getData().get(position).getTitle());
            intent.putExtra("time",getData().get(position).getTime());
            getContext().startActivity(intent);
        });
    }


    public NotifyAdapter_V(int layoutResId, @Nullable List<NotifyBean> data) {
        super(layoutResId,data);

    }


//    public CommonAdapter(@Nullable List<HomeItem> data) {
//
//        this(R.layout.item_today_health, data);
//        Log.d("convert", "convert: "+data.size());
//
//    }





    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, NotifyBean notify) {

        baseViewHolder.setText(R.id.title,notify.getTitle());
        baseViewHolder.setText(R.id.subtitle,notify.getSubtitle());
        baseViewHolder.setText(R.id.time,notify.getTime());

    }
}
