package com.zksg.kudoud.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.databinding.ItemMemePoolListBinding;
import com.zksg.kudoud.databinding.ItemSocialsBinding;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.kudoud.utils.StringUtils;
import com.zksg.kudoud.utils.TelegramUtils;

import java.util.List;

public class SocialAdapter extends SimpleDataBindingAdapter<DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO, ItemSocialsBinding> {

    private Context mContex;
    public SocialAdapter(Context context) {
        super(context, R.layout.item_socials, DiffUtils.getInstance().getSocialsItemCallback());
        this.mContex=context;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinsDetailActivity.class);
//            intent.putExtra("contract",item.getAddress());
//            context.startActivity(intent);
            if(item.getType().equals("twitter")){
                startTwitter(item);
            }else if(item.getType().equals("telegram")){
                TelegramUtils.startTelegram(context,item);
            }else if(item.getType().equals("discord")){

            }


        });
    }

    @Override
    protected void onBindItem(ItemSocialsBinding binding, DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }
    public void startTwitter(DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO social){
        String username="";
//        DexScreenTokenInfo1.PairsDTO.InfoDTO info= mCoinsDetailViewModel.mPairsDTO.getValue().getInfo();
        if(social==null){
            ToastUtils.showShort(R.string.str_Invalid_twitter);
            return;
        }
        if(social.getType().equals("twitter")){
            username= StringUtils.extractUsernameFromUrl(social.getUrl());
        }
        if(username.equals("")){
            ToastUtils.showShort(R.string.str_Invalid_twitter);
            return;
        }
        IntentUtils.openTwitter(mContex,username);
    }


//    public void startTelegram(DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO social){
//        String username="";
//        if(social==null){
//            ToastUtils.showShort(R.string.str_Invalid_telegram);
//            return;
//        }
//
//        if(social.getType().equals("telegram")){
//            username= StringUtils.extractUsernameFromUrlTG(social.getUrl());
//        }
//        if(username.equals("")){
//            ToastUtils.showShort(R.string.str_Invalid_telegram);
//            return;
//        }
//        IntentUtils.openTelegram(mContex,username);
//    }

}
