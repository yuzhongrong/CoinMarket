package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemSendcoinNumberBinding;
import com.zksg.kudoud.utils.DiffUtils;

public class SendCoinNumberdapter extends SimpleDataBindingAdapter<String, ItemSendcoinNumberBinding> {


    private Context mContex;
    public SendCoinNumberdapter(Context context) {
        super(context, R.layout.item_sendcoin_number, DiffUtils.getInstance().getSendCoinNumberCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            intent.putExtra("symbol",item.getSymbol());
//            context.startActivity(intent);

        });
    }
    @Override
    protected void onBindItem(ItemSendcoinNumberBinding binding, String item, RecyclerView.ViewHolder holder) {
        binding.setNumber(item);
    }
}
