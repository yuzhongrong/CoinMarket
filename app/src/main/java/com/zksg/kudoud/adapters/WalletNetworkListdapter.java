package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemMemePoolListBinding;
import com.zksg.kudoud.databinding.ItemWalletNetworkBinding;
import com.zksg.kudoud.entitys.WalletNetworkEntity;
import com.zksg.kudoud.utils.DiffUtils;

public class WalletNetworkListdapter extends SimpleDataBindingAdapter<WalletNetworkEntity, ItemWalletNetworkBinding> {

    private Context mContex;
    public WalletNetworkListdapter(Context context) {
        super(context, R.layout.item_wallet_network, DiffUtils.getInstance().getWalletNetworkCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);

        });
    }

    @Override
    protected void onBindItem(ItemWalletNetworkBinding binding, WalletNetworkEntity item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
