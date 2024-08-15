package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemMemePoolListBinding;
import com.zksg.kudoud.utils.DiffUtils;

public class MemePoolListdapter extends SimpleDataBindingAdapter<DexScreenTokenInfo1.PairsDTO, ItemMemePoolListBinding> {

    private Context mContex;
    public MemePoolListdapter(Context context) {
        super(context, R.layout.item_meme_pool_list, DiffUtils.getInstance().getMemePooltemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);

        });
    }

    @Override
    protected void onBindItem(ItemMemePoolListBinding binding, DexScreenTokenInfo1.PairsDTO item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
    }
}
