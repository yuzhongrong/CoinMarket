package com.zksg.kudoud.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.CheckToken;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.zksg.kudoud.R;
import com.zksg.kudoud.databinding.ItemMemeCheckListBinding;
import com.zksg.kudoud.databinding.ItemMemePoolListBinding;
import com.zksg.kudoud.utils.DiffUtils;


public class MemeCheckListdapter extends SimpleDataBindingAdapter<CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO, ItemMemeCheckListBinding> {

    private Context mContext;
    public MemeCheckListdapter(Context context) {
        super(context, R.layout.item_meme_check_list, DiffUtils.getInstance().getMemeChecktemCallback());
        this.mContext=context;

        setOnItemClickViewListener((item, position, view) -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            String url="https://solscan.io/account/"+item.getAddress();
            // Set the data for the intent as the URL
            intent.setData(Uri.parse(url));
            // Start the activity
            mContext.startActivity(intent);

         },R.id.holder_look);





    }

    @Override
    protected void onBindItem(ItemMemeCheckListBinding binding, CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO item, RecyclerView.ViewHolder holder) {
        binding.setMeme(item);
        binding.setIndex(getCurrentList().indexOf(item));
    }




}
