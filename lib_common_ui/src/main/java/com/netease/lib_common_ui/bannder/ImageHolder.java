package com.netease.lib_common_ui.bannder;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.zksg.lib_api.beans.BannerBean;

public class ImageHolder implements Holder<BannerBean> {

    private AppCompatImageView mImageView;

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();



    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        mImageView.setPadding(45,0,45,0);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerBean data) {
        if(data!=null&&  data.getBannerContent()!=null){
            Log.d("ImageHolder","----->start UpdateUI "+position+" data "+data);
            ImageLoaderManager.getInstance().displayImageForCorner1(mImageView, data.getBannerContent().getImgurl(),20);

        }

    }
}
