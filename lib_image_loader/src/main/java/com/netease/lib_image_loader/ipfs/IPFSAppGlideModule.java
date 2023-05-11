package com.netease.lib_image_loader.ipfs;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import io.ipfs.api.IPFS;


@GlideModule
public class IPFSAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    }


    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        Log.e("IPFSAppGlideModule","----->registerComponents");
       new Handler().post(new Runnable() {
           @Override
           public void run() {

//               IPFS ipfs=IPFSManager.getInstance().getIPFSInstance();
               glide.getRegistry().replace(String.class, InputStream.class, new IPFSModelLoader.Factory());
           }
       });

    }
}
