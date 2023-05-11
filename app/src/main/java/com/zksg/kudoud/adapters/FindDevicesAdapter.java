package com.zksg.kudoud.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kunminx.architecture.utils.Utils;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.ControlDeviceActivity;
import com.zksg.kudoud.activitys.SearchDeviceIngActivity;
import com.zksg.lib_api.beans.DeviceBean;
import com.zksg.lib_api.beans.HomeItem;

import java.util.List;

public class FindDevicesAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {



    public FindDevicesAdapter(@Nullable List<DeviceBean> data) {
        super(R.layout.item_search_device, data);
        Log.d("convert", "convert: "+data.size());
        //TODO tip:设置要点击的ids
        addChildClickViewIds(R.id.btn_connect);
        setOnItemChildClickListener((adapter,view,position)->{
            if(view.getId()==R.id.btn_connect){
               getContext().startActivity(new Intent(getContext(), ControlDeviceActivity.class));
            }
        });
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DeviceBean device) {
        Log.d("convert", "convert: ");
        baseViewHolder.setImageResource(R.id.icon,device.getIcon());
        baseViewHolder.setText(R.id.title,device.getName());

    }
}
