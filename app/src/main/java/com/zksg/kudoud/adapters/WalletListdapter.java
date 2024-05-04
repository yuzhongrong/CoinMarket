package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.WalleManagertActivity;
import com.zksg.kudoud.databinding.ItemWalletBinding;
import com.zksg.kudoud.entitys.SelectWalletEntity;
import com.zksg.kudoud.state.SharedViewModel;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.manager.SimpleWallet;

public class WalletListdapter extends SimpleDataBindingAdapter<SimpleWallet, ItemWalletBinding> {

    private Context mContex;
    private SharedViewModel sharedViewModel;
    private WalleManagertActivity mActivity;
    public WalletListdapter(Context context) {
        super(context, R.layout.item_wallet, DiffUtils.getInstance().getWalletCallback());
        this.mContex=context;
        mActivity=((WalleManagertActivity)context);
        sharedViewModel=mActivity.getSharedViewModel();
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);

            //1.更新本地缓存
            MMKV.mmkvWithID("currentWallet").encode("netwrokgroup",item.getNetwork());
            MMKV.mmkvWithID("currentWallet").encode("keyAlias",item.getKeyAlias());

            //2.post全局的当前钱包
            sharedViewModel.requestSelectWallet(new SelectWalletEntity(item.getNetwork(),item.getKeyAlias()));
            mActivity.finish();
        });


    }

    @Override
    protected void onBindItem(ItemWalletBinding binding, SimpleWallet item, RecyclerView.ViewHolder holder) {
        binding.setMywallet(item);
        binding.setMshareVm(sharedViewModel);
    }
}
