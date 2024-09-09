package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinManagerActivity;
import com.zksg.kudoud.databinding.ItemCoinLocalManagerBinding;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.CoinManagerActivityViewModel;
import com.zksg.kudoud.utils.DiffUtils;

import java.util.List;

public class CoinManagerLocalTokensdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemCoinLocalManagerBinding> {

    private Context mContex;
    private CoinManagerActivityViewModel mCoinManagerActivityViewModel;
    public CoinManagerLocalTokensdapter(Context context) {
        super(context, R.layout.item_coin_local_manager, DiffUtils.getInstance().geTokenInfoEntityCallback());
        this.mContex=context;
        this.mCoinManagerActivityViewModel=((CoinManagerActivity)mContex).getViewModel();

        setOnItemClickViewListener((OnItemClickViewListener<UiWalletToken>) (item, position, view) -> {
            //必须点击imageview
            if(view.getId()!=R.id.action)return;
            //排除sol
            if(item.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT))return;
            item.setShow(false);
            //更新localtokens列表,然后把这个item从本地删除
            List<UiWalletToken> oldLocalDatas=mCoinManagerActivityViewModel.localdatas.getValue();
            oldLocalDatas.remove(item);
            mCoinManagerActivityViewModel.localdatas.postValue(oldLocalDatas);

            //更新hottokens列表-这里没有处理自定义加入到列表的代币 remove后放到热门列表中去了的情况
            List<UiWalletToken> oldHotDatas= mCoinManagerActivityViewModel.hotdatas.getValue();
            oldHotDatas.add(item);
            mCoinManagerActivityViewModel.hotdatas.postValue(oldHotDatas);

        },R.id.action);
    }

    @Override
    protected void onBindItem(ItemCoinLocalManagerBinding binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
