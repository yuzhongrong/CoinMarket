package com.zksg.kudoud.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zksg.kudoud.R;

import java.util.List;

public class SearchHistoryItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchHistoryItemAdapter(int layoutResId) {
        super(layoutResId);
    }


    public SearchHistoryItemAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId,data);
        setOnItemClickListener((adapter,view,position)->{
//            Intent i=new Intent(getContext(), AppDetailActivity.class);
//            i.putExtra("appinfo",this.getData().get(position));
//            getContext().startActivity(i);
        });
    }

//    public CommonAdapter(@Nullable List<HomeItem> data) {
//
//        this(R.layout.item_today_health, data);
//        Log.d("convert", "convert: "+data.size());
//
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String homeItem) {
        Log.d("convert", "convert: ");
        baseViewHolder.setText(R.id.title,homeItem);

    }
}
