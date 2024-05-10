package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemExSwapWalletBinding;
import com.zksg.kudoud.databinding.ItemMemeWalletBinding;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.CoinWalletDetailActivityViewModel;
import com.zksg.kudoud.state.MeFragmentViewModel;
import com.zksg.kudoud.utils.DiffUtils;

public class ExSwapListdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemExSwapWalletBinding> {

    private CoinWalletDetailActivityViewModel meFragmentViewModel;
    private Context mContex;
    public ExSwapListdapter(Context context, CoinWalletDetailActivityViewModel vm) {
        super(context, R.layout.item_ex_swap_wallet, DiffUtils.getInstance().geTokenInfoEntityCallback());
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
    protected void onBindItem(ItemExSwapWalletBinding binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setUiwallettoken(item);
        binding.setVm(this.meFragmentViewModel);
    }
}
