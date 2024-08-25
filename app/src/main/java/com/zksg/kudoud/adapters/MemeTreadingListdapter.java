package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemMemeTrendingBinding;
import com.netease.lib_network.entitys.CommonCategory;
import com.zksg.kudoud.utils.DiffUtils;

public class MemeTreadingListdapter extends SimpleDataBindingAdapter<CommonCategory.DataDTO, ItemMemeTrendingBinding> {

    private Context mContex;
    public MemeTreadingListdapter(Context context) {
        super(context, R.layout.item_meme_trending, DiffUtils.getInstance().getMemeTrendingtemCallback());
        this.mContex=context;
        this.setHasStableIds(true);
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);

        });
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
