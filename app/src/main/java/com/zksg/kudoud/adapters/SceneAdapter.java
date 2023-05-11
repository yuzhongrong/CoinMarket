package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CreateEnvActivity;
import com.zksg.kudoud.databinding.ItemEnvBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.lib_api.beans.EnvBean;

import java.util.List;

public class SceneAdapter extends SimpleDataBindingAdapter<EnvBean, ItemEnvBinding> {

    public SceneAdapter(Context context) {
        super(context, R.layout.item_env, DiffUtils.getInstance().getEnvItemCallback());
        setOnItemClickListener((item, position) ->{
            if(item.getType()== EnvBean.Type.ADD){//add scene
                context.startActivity(new Intent(context, CreateEnvActivity.class));
            }
        });

    }

    @Override
    protected void onBindItem(ItemEnvBinding binding, EnvBean item, RecyclerView.ViewHolder holder) {
        binding.setBean(item);
    }
}
