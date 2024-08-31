package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.lxj.xpopup.XPopup;
import com.netease.lib_network.entitys.CommonCategory;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.activitys.Kline2OrderActivity;
import com.zksg.kudoud.activitys.MemeChartDetailActivity;
import com.zksg.kudoud.databinding.ItemFeedTipBinding;
import com.zksg.kudoud.databinding.ItemMemeListBinding;
import com.zksg.kudoud.dialogs.DelBabyDialog;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.lib_api.baby.FeedTip;
import com.zksg.lib_api.beans.MemeBaseEntry;

public class MemeCommonListdapter extends SimpleDataBindingAdapter<CommonCategory.DataDTO, ItemMemeListBinding> {

    private Context mContex;
    public MemeCommonListdapter(Context context) {
        super(context, R.layout.item_meme_list, DiffUtils.getInstance().getMemeBaseItemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
            openItem(item);
        });
    }


    private void openItem(CommonCategory.DataDTO item){

        String contract="";
        String symbol="";
        if(!item.getToken0Address().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){
            contract=item.getToken0Address();
            symbol=item.getToken0Symbol();
        }else{
            contract=item.getToken1Address();
            symbol=item.getToken1Symbol();
        }
        Intent i= new Intent(mContex, Kline2OrderActivity.class)
                .putExtra("contract",contract)
                .putExtra("symbol",symbol)
                .putExtra("pair",item.getPair());
        IntentUtils.openIntent(mContex,i);

    }


    @Override
    protected void onBindItem(ItemMemeListBinding binding, CommonCategory.DataDTO item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }

    public void start2DexscreenKline(Context context,String contract,String symbol,String pair){
        Intent i= new Intent(context, Kline2OrderActivity.class)
                .putExtra("contract",contract)
                .putExtra("symbol",symbol)
                .putExtra("pair",pair)
                ;
        IntentUtils.openIntent(context,i);
    }

}
