package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinManagerActivity;
import com.zksg.kudoud.databinding.ItemCoinAddManagerBinding;
import com.zksg.kudoud.entitys.JubToken;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.CoinManagerActivityViewModel;
import com.zksg.kudoud.utils.DiffUtils;

import java.util.List;

public class CoinManagerListdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemCoinAddManagerBinding> {

    private Context mContex;
    private CoinManagerActivityViewModel mCoinManagerActivityViewModel;
    public CoinManagerListdapter(Context context) {
        super(context, R.layout.item_coin_add_manager, DiffUtils.getInstance().geTokenInfoEntityCallback());
        this.mContex=context;
        this.mCoinManagerActivityViewModel=((CoinManagerActivity)mContex).getViewModel();
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            intent.putExtra("symbol",item.getSymbol());
//            context.startActivity(intent);
            //先localdatas更新集合
           List<UiWalletToken> oldlacaldatas=mCoinManagerActivityViewModel.localdatas.getValue();
           oldlacaldatas.add(item);
            mCoinManagerActivityViewModel.localdatas.postValue(oldlacaldatas);

            //再更新hotdatas
            List<UiWalletToken> oldhotdatas=mCoinManagerActivityViewModel.hotdatas.getValue();
            oldhotdatas.remove(item);
            mCoinManagerActivityViewModel.hotdatas.postValue(oldhotdatas);

            //



        });
    }


    @Override
    protected void onBindItem(ItemCoinAddManagerBinding binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
