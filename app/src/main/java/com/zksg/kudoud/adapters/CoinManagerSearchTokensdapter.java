package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinManagerActivity;
import com.zksg.kudoud.databinding.ItemCoinSearchManagerBindingImpl;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.CoinManagerActivityViewModel;
import com.zksg.kudoud.utils.DiffUtils;

import java.util.List;

public class CoinManagerSearchTokensdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemCoinSearchManagerBindingImpl> {

    private Context mContex;
    private CoinManagerActivityViewModel mCoinManagerActivityViewModel;
    public CoinManagerSearchTokensdapter(Context context) {
        super(context, R.layout.item_coin_search_manager, DiffUtils.getInstance().getSearchEntityCallback());
        this.mContex=context;
        this.mCoinManagerActivityViewModel=((CoinManagerActivity)mContex).getViewModel();

        setOnItemClickViewListener((OnItemClickViewListener<UiWalletToken>) (item, position, view) -> {
            //必须点击imageview
//            if(view.getId()!=R.id.action)return;
            //排除sol
            if(item.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT))return;
            //这里就区分 方向：加或减
            if(item.isShow()){//减操作
                item.setShow(false);
                //更新localtokens列表,然后把这个item从本地删除
                List<UiWalletToken> oldLocalDatas=mCoinManagerActivityViewModel.localdatas.getValue();
                oldLocalDatas.remove(item);
                mCoinManagerActivityViewModel.localdatas.postValue(oldLocalDatas);
                //更新hottokens列表
                List<UiWalletToken> oldHotDatas= mCoinManagerActivityViewModel.hotdatas.getValue();
                oldHotDatas.add(item);
                mCoinManagerActivityViewModel.hotdatas.postValue(oldHotDatas);


            }else{//加操作

                item.setShow(true);
                //先localdatas更新集合
                List<UiWalletToken> oldlacaldatas=mCoinManagerActivityViewModel.localdatas.getValue();
                oldlacaldatas.add(item);
                mCoinManagerActivityViewModel.localdatas.postValue(oldlacaldatas);

                //再更新hotdatas
                List<UiWalletToken> oldhotdatas=mCoinManagerActivityViewModel.hotdatas.getValue();
                oldhotdatas.remove(item);
                mCoinManagerActivityViewModel.hotdatas.postValue(oldhotdatas);


            }

            //更新当前搜索列表
//            List<UiWalletToken> searchDatas=mCoinManagerActivityViewModel.amountdatas.getValue();
//            searchDatas.get(position).setShow(item.isShow());
//            mCoinManagerActivityViewModel.amountdatas.postValue(searchDatas);
              notifyItemChanged(position);




//            item.setShow(false);
//            //更新localtokens列表,然后把这个item从本地删除
//            List<UiWalletToken> oldLocalDatas=mCoinManagerActivityViewModel.localdatas.getValue();
//            oldLocalDatas.remove(item);
//            mCoinManagerActivityViewModel.localdatas.postValue(oldLocalDatas);
//            //更新hottokens列表
//            List<UiWalletToken> oldHotDatas= mCoinManagerActivityViewModel.hotdatas.getValue();
//            oldHotDatas.add(item);
//
//            mCoinManagerActivityViewModel.hotdatas.postValue(oldHotDatas);
        },R.id.action);
    }

    @Override
    protected void onBindItem(ItemCoinSearchManagerBindingImpl binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
