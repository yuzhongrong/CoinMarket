package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemBabyBinding;
import com.zksg.kudoud.databinding.ItemFeedTipBinding;
import com.zksg.kudoud.dialogs.DelBabyDialog;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.lib_api.baby.BabyInfo;
import com.zksg.lib_api.baby.FeedTip;

public class FeedTipAdapter extends SimpleDataBindingAdapter<FeedTip, ItemFeedTipBinding> {

    private Context mContex;
    public FeedTipAdapter(Context context) {
        super(context, R.layout.item_feed_tip, DiffUtils.getInstance().getFeedTipItemCallback());
        this.mContex=context;
        setOnItemLongClickListener((item, position) -> {
            new XPopup.Builder(context).asCustom(new DelBabyDialog(context)).show();
        });
    }


    @Override
    protected void onBindItem(ItemFeedTipBinding binding, FeedTip item, RecyclerView.ViewHolder holder) {
        binding.setBean(item);
    }
}
