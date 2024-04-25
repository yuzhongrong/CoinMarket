package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.databinding.ItemMemeListBinding;
import com.zksg.kudoud.databinding.ItemMemeWalletBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.lib_api.beans.MemeBaseEntry;

public class MemeCommonWalletListdapter extends SimpleDataBindingAdapter<MemeBaseEntry, ItemMemeWalletBinding> {

    private Context mContex;
    public MemeCommonWalletListdapter(Context context) {
        super(context, R.layout.item_meme_wallet, DiffUtils.getInstance().getMemeBaseItemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
            Intent intent=new Intent(context, CoinsDetailActivity.class);
            intent.putExtra("contract",item.getAddress());
            intent.putExtra("symbol",item.getSymbol());
            context.startActivity(intent);

        });
    }
    @Override
    protected void onBindItem(ItemMemeWalletBinding binding, MemeBaseEntry item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
