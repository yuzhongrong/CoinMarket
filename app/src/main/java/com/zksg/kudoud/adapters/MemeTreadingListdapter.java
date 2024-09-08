package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;
import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_USDC_CONTRACT;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.Kline2OrderActivity;
import com.zksg.kudoud.databinding.ItemMemeTrendingBinding;
import com.netease.lib_network.entitys.CommonCategory;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;

public class MemeTreadingListdapter extends SimpleDataBindingAdapter<CommonCategory.DataDTO, ItemMemeTrendingBinding> {

    private Context mContex;
    public MemeTreadingListdapter(Context context) {
        super(context, R.layout.item_meme_trending, DiffUtils.getInstance().getMemeTrendingtemCallback());
        this.mContex=context;
        this.setHasStableIds(true);
        setOnItemClickViewListener((item, position, viewId) -> {

            String contract=item.getTargetToken();
            String symbol="";
            String logo="";
            if(item.getToken0Address().equals(item.getTargetToken())){

                symbol=item.getToken0Symbol();
                logo=item.getToken0LogoUrl();
            }else{

                symbol=item.getToken1Symbol();
                logo=item.getToken1LogoUrl();
            }
            Intent i= new Intent(context, Kline2OrderActivity.class)
                    .putExtra("contract",contract)
                    .putExtra("symbol",symbol)
                    .putExtra("pair",item.getPair())
                    .putExtra("logo",logo);
            IntentUtils.openIntent(context,i);

        },R.id.action);

    }

    @Override
    protected void onBindItem(ItemMemeTrendingBinding binding, CommonCategory.DataDTO item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }

    @Override
    public long getItemId(int position) {
       return getCurrentList().get(position).getPair().hashCode();
    }


}
