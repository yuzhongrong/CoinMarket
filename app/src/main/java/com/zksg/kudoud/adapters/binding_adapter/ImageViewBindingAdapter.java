package com.zksg.kudoud.adapters.binding_adapter;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.beans.DexEnum;
import com.zksg.kudoud.entitys.JubToken;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"meme_imv"},requireAll = false)
    public static void memeImv(ImageView imv, String url) {

        ImageLoaderManager.getInstance().displayImageForCircle(imv,url);

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
    public static void meme_imv_token_remove(ImageView imv, UiWalletToken token) {

        if(imv==null||token==null)return;
        if(token.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){//默认sol不让操作
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_not_minus,0);
        }else{
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_minus,0);
        }

    }

    @BindingAdapter(value = {"meme_imv_if_sol"},requireAll = false)
    public static void meme_imv_if_sol(ImageView imv, UiWalletToken token) {
        if(imv==null||token==null)return;
        if(token.getMint().equalsIgnoreCase(TOKEN_SOL_CONTRACT)){
            ImageLoaderManager.getInstance().displayLocalImageForCorner(imv,R.mipmap.ic_solana_common,45);
        }else{
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



}
