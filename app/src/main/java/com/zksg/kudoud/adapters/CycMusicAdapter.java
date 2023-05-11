package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemCycmusicBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.lib_api.playlist.BasicMusicInfo;

//TODO:tip  实现databinding功能的adapter 使用方式 当数据过多的时候请使用DiffUtils.getInstance()
public class CycMusicAdapter extends SimpleDataBindingAdapter<BasicMusicInfo, ItemCycmusicBinding> {
    public CycMusicAdapter(Context context) {
        super(context, R.layout.item_cycmusic, DiffUtils.getInstance().getBasicMusicInfoItemCallback());
    }

    @Override
    protected void onBindItem(ItemCycmusicBinding binding, BasicMusicInfo item, RecyclerView.ViewHolder holder) {
        binding.setInfo(item);
    }
}
