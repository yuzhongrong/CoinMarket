package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CreateEnvActivity;
import com.zksg.kudoud.databinding.ItemBabyBinding;
import com.zksg.kudoud.dialogs.DelBabyDialog;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.lib_api.baby.BabyInfo;
import com.zksg.lib_api.beans.EnvBean;

public class BabyAdapter extends SimpleDataBindingAdapter<BabyInfo, ItemBabyBinding> {

    private Context mContex;
    public BabyAdapter(Context context) {
        super(context, R.layout.item_baby, DiffUtils.getInstance().getBabyItemCallback());
        this.mContex=context;
        setOnItemLongClickListener((item, position) -> {
            new XPopup.Builder(context).asCustom(new DelBabyDialog(context)).show();
        });
    }

    @Override
    protected void onBindItem(ItemBabyBinding binding, BabyInfo item, RecyclerView.ViewHolder holder) {
        binding.setBean(item);
//        final XPopup.Builder builder = new XPopup.Builder(mContex).watchView(holder.itemView);
//        holder.itemView.setOnLongClickListener(v -> {
//            builder.asAttachList(new String[]{"置顶", "编辑", "删除"}, null, new OnSelectListener() {
//                @Override
//                public void onSelect(int position, String text) {
//                    Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
//                }
//            }).show();
//            return true;
//        });

    }
}
