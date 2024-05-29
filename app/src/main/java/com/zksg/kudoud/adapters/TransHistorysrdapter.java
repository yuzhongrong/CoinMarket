package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.TransationHistoryEntity;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinTransationHistorysActivity;
import com.zksg.kudoud.activitys.TransationHistoryDetailActivity;
import com.zksg.kudoud.databinding.ItemTransationBinding;
import com.zksg.kudoud.state.TransationHistorysActivityViewModel;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;

public class TransHistorysrdapter extends SimpleDataBindingAdapter<TransationHistoryEntity, ItemTransationBinding> {

    private Context mContex;
    private TransationHistorysActivityViewModel mTransationHistorysActivityViewModel;
    public TransHistorysrdapter(Context context) {
        super(context, R.layout.item_transation, DiffUtils.getInstance().geTTransationHistoryEntity());
        this.mContex=context;
        this.mTransationHistorysActivityViewModel=((CoinTransationHistorysActivity)mContex).getViewModel();
        setOnItemClickListener((item, position) -> {
            IntentUtils.openIntent(context, new Intent(context,TransationHistoryDetailActivity.class).putExtra("detail",item).putExtra("wallet",mTransationHistorysActivityViewModel.walletAddress.get()));
        });
    }


    @Override
    protected void onBindItem(ItemTransationBinding binding, TransationHistoryEntity item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
        binding.setAdapter(this);
        binding.setVm(mTransationHistorysActivityViewModel);

    }
}
