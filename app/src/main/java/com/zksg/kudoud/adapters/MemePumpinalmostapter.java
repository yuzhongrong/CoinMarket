package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;
import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_USDC_CONTRACT;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.CommonCategory;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.Kline2OrderActivity;
import com.zksg.kudoud.databinding.ItemMemeListBinding;
import com.zksg.kudoud.databinding.ItemMemePumpinListBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.kudoud.utils.TelegramUtils;

public class MemePumpinalmostapter extends SimpleDataBindingAdapter<CommonCategory.DataDTO, ItemMemePumpinListBinding> {

    private Context mContex;
    public MemePumpinalmostapter(Context context) {
        super(context, R.layout.item_meme_pumpin_list, DiffUtils.getInstance().getMemeBaseItemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
            TelegramUtils.openTelegramBot2(mContext,item);
        });
    }


//    private void openItem(CommonCategory.DataDTO item){
//
//        String contract="";
//        String symbol="";
//        if(!item.getToken0Address().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){
//            contract=item.getToken0Address();
//            symbol=item.getToken0Symbol();
//        }else{
//            contract=item.getToken1Address();
//            symbol=item.getToken1Symbol();
//        }
//        Intent i= new Intent(mContex, Kline2OrderActivity.class)
//                .putExtra("contract",contract)
//                .putExtra("symbol",symbol)
//                .putExtra("pair",item.getPair());
//        IntentUtils.openIntent(mContex,i);
//
//    }


    @Override
    protected void onBindItem(ItemMemePumpinListBinding binding, CommonCategory.DataDTO item, RecyclerView.ViewHolder holder) {
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
