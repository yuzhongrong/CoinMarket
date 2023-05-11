package com.netease.lib_image_loader.ipfs;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IPFSModelLoader {

    private IPFSModelLoader() {
        // TODO: Private constructor
    }

    @NonNull
    public static Uri parseIPFSUrl(@NonNull String ipfsUrl) {
        // TODO: 解析IPFS链接
        Log.d("IPFSModelLoader","parseIPFSUrl is "+ Uri.parse(ipfsUrl).toString());
        return Uri.parse(ipfsUrl);
    }

    private static class IPFSDataFetcher implements DataFetcher<InputStream> {

        private final String cid;

        IPFSDataFetcher(String cid) {
            Log.d("IPFSDataFetcher","cid is "+cid);
            this.cid = cid;

        }

        @Override
        public void loadData(@NonNull Priority priority, @NonNull final DataCallback<? super InputStream> callback) {
            Log.d("IPFSDataFetcher","---->start loadData");
            //使用网关解析
            try {
                String ipfsUrl = "https://ipfs.io/ipfs/";
                String urlStr = ipfsUrl + cid;
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                        .readTimeout(30, TimeUnit.SECONDS) // 设置读取超时时间
                        .writeTimeout(30, TimeUnit.SECONDS) // 设置写入超时时间
                        .retryOnConnectionFailure(false) // 禁止自动重试
                        .build();
                Request request = new Request.Builder()
                        .url(urlStr)
                        .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    callback.onDataReady(inputStream);
                } else {
                    callback.onLoadFailed(new IOException("Response code " + response.code()));
                }
            } catch (Exception e) {
                callback.onLoadFailed(e);
            }


        }

        @Override
        public void cleanup() {
            // 不需要实现

        }

        @Override
        public void cancel() {
            // 不需要实现
        }

        @NonNull
        @Override
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }

        @NonNull
        @Override
        public DataSource getDataSource() {
            return DataSource.REMOTE;
        }
    }

    public static class Factory implements ModelLoaderFactory<String, InputStream> {

//        private IPFS ipfs;

//        public Factory(IPFS ipfs){
//            this.ipfs=ipfs;
//        }

        @NonNull
        @Override
        public ModelLoader<String, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new ModelLoader<String, InputStream>() {

                @NonNull
                @Override
                public LoadData<InputStream> buildLoadData(@NonNull String ipfsUrl, int width, int height, @NonNull Options options) {
                    return new LoadData<>(new ObjectKey(ipfsUrl), new IPFSDataFetcher(ipfsUrl));
                }

                @Override
                public boolean handles(@NonNull String ipfsUrl) {
                    Log.d("Factory.handles ","cid is "+ipfsUrl);
//                    return ipfsUrl.toLowerCase(Locale.ROOT).startsWith("ipfs://");
                    return true;
                }
            };
        }

        @Override
        public void teardown() {
            // 不需要实现
        }
    }

}
