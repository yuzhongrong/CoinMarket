package com.zksg.kudoud.adapters.binding_adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.kudoud.R;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"meme_imv"},requireAll = false)
    public static void memeImv(ImageView imv, String url) {

        ImageLoaderManager.getInstance().displayImageForCircle(imv,url);

    }



}
