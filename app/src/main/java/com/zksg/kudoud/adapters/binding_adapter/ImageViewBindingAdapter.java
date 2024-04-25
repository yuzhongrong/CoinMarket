package com.zksg.kudoud.adapters.binding_adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.beans.DexEnum;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"meme_imv"},requireAll = false)
    public static void memeImv(ImageView imv, String url) {

        ImageLoaderManager.getInstance().displayImageForCircle(imv,url);

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
