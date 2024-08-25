package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.lxj.xpopup.XPopup;
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

public class MemeCommonListdapter extends SimpleDataBindingAdapter<MemeBaseEntry, ItemMemeListBinding> {

    private Context mContex;
    public MemeCommonListdapter(Context context) {
        super(context, R.layout.item_meme_list, DiffUtils.getInstance().getMemeBaseItemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {


            start2DexscreenKline(context,item.getAddress(),item.getSymbol(),"");

        });
    }


    @Override
    protected void onBindItem(ItemMemeListBinding binding, MemeBaseEntry item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
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
