package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemWalletBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.manager.SimpleWallet;

public class WalletListdapter extends SimpleDataBindingAdapter<SimpleWallet, ItemWalletBinding> {

    private Context mContex;
    public WalletListdapter(Context context) {
        super(context, R.layout.item_wallet, DiffUtils.getInstance().getWalletCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);

        });
    }

    @Override
    protected void onBindItem(ItemWalletBinding binding, SimpleWallet item, RecyclerView.ViewHolder holder) {
        binding.setMywallet(item);
    }
}
