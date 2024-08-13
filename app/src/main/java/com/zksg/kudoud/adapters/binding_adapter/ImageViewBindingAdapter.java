package com.zksg.kudoud.adapters.binding_adapter;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.beans.DexEnum;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.utils.AnimationUtil;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"meme_imv"},requireAll = false)
    public static void memeImv(ImageView imv, String url) {

        ImageLoaderManager.getInstance().displayImageForCircle(imv,url);

    }

    @BindingAdapter(value = {"memeImvType"},requireAll = false)
    public static void memeImvType(ImageView imv, String type) {
        if(type.equals("twitter")){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_twitter,0);
        }else if(type.equals("telegram")){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_telegram1,0);
        }else if(type.equals("discord")){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_discord,0);
        }


    }


    @BindingAdapter(value = {"transfor_icon","issystem"},requireAll = false)
    public static void transfor_icon(ImageView imv, String url,boolean issystem) {
        if(imv==null)return;
        if(issystem){//sol
            imv.setImageResource(R.mipmap.ic_solana_common);
        }else{
            if(!TextUtils.isEmpty(url)){
                ImageLoaderManager.getInstance().displayImageForCircle(imv,url);
            }

        }

    }



    @BindingAdapter(value = {"meme_imv_wallet"},requireAll = false)
    public static void memeImv(ImageView imv, UiWalletToken uitoken) {

        if(imv==null||uitoken==null)return;
        if(uitoken.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,uitoken.getResId(),45);
        }else{
            ImageLoaderManager.getInstance().displayImageForCircle(imv,uitoken.getImageUrl());
        }


    }


    @BindingAdapter(value = {"meme_imv_token_add"},requireAll = false)
    public static void meme_imv_token_add(ImageView imv, UiWalletToken token) {

        if(imv==null)return;
        if(token.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){//默认sol不让操作
            imv.setVisibility(View.GONE);
        }else{
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_plus,0);
        }


    }


    @BindingAdapter(value = {"meme_imv_token_remove"},requireAll = false)
    public static void meme_imv_token_remove(ImageView imv, String mint) {

        if(imv==null|| TextUtils.isEmpty(mint))return;
        if(mint.equalsIgnoreCase(TOKEN_SOL_CONTRACT)){//默认sol不让操作
            imv.setImageResource( R.mipmap.ic_not_minus);

//            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_not_minus,0);
        }else{
//            ImageLoaderManager.getInstance().displayLocalImage(imv,R.mipmap.ic_minus);
            imv.setImageResource(R.mipmap.ic_minus);
        }

    }

    @BindingAdapter(value = {"meme_imv_token_amount_show"},requireAll = false)
    public static void meme_imv_token_amount_show(ImageView imv, UiWalletToken token) {

        if(imv==null||token==null)return;
        if(token.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){//默认sol不让操作只显示ic_not_minus
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_not_minus,0);
        }else{
            if(token.isShow()){
                imv.setImageResource(R.mipmap.ic_minus);
            }else if(!token.isShow()){
                imv.setImageResource(R.mipmap.ic_plus);
            }

        }



    }

    @BindingAdapter(value = {"meme_imv_if_sol"},requireAll = false)
    public static void meme_imv_if_sol(ImageView imv, UiWalletToken token) {
        if(imv==null||token==null)return;
        if(token.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){
            imv.setImageResource(R.mipmap.ic_solana_common);
        }
        else{
            ImageLoaderManager.getInstance().displayImageForCircle(imv,token.getImageUrl());
        }


    }



    @BindingAdapter(value = {"meme_common_local_imv"},requireAll = false)
    public static void meme_common_local_imv(ImageView imv, int resId) {
        ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,resId,45);

    }
    @BindingAdapter(value = {"meme_dex_imv"},requireAll = false)
    public static void meme_dex_imv(ImageView imv, String dexid) {
        if(dexid.equals(DexEnum.ORCA.getKey())){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_orca,45);
        }else if(dexid.equals(DexEnum.METEORA.getKey())){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_meteora,45);
        }else if(dexid.equals(DexEnum.RAYDIUM.getKey())){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_raydium,45);
        }else{
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_unlink,45);

        }

    }


    @BindingAdapter(value = {"imv_rotate"},requireAll = false)
    public static void imv_rotate(ImageView imv, boolean rotate) {
        if(imv==null)return;
        if(rotate)  AnimationUtil.rotateImageView(imv,  180f, 500);

    }


    @BindingAdapter(value = {"meme_check_token_items"},requireAll = false)
    public static void meme_check_token_items(ImageView imv, int type) {
        if(type==0){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_safe,0);
        }else{
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_warning,0);

        }


    }

    @BindingAdapter(value = {"meme_is_contract"},requireAll = false)
    public static void meme_is_contract(ImageView imv, int type) {
        if(type==1){
            imv.setVisibility(View.VISIBLE);
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_contract,0);
        }else{
            imv.setVisibility(View.GONE);
        }


    }


}
