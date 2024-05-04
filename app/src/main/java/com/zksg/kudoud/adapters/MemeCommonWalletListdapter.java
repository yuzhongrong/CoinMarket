package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.NewWalletToken;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemMemeWalletBinding;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.MeFragmentViewModel;
import com.zksg.kudoud.utils.DiffUtils;

public class MemeCommonWalletListdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemMemeWalletBinding> {

    private MeFragmentViewModel meFragmentViewModel;
    private Context mContex;
    public MemeCommonWalletListdapter(Context context,MeFragmentViewModel vm) {
        super(context, R.layout.item_meme_wallet, DiffUtils.getInstance().geTokenInfoEntityCallback());
        this.mContex=context;
        this.meFragmentViewModel=vm;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            intent.putExtra("symbol",item.getSymbol());
//            context.startActivity(intent);

        });
    }
    @Override
    protected void onBindItem(ItemMemeWalletBinding binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setUiwallettoken(item);
        binding.setVm(this.meFragmentViewModel);
    }
}
