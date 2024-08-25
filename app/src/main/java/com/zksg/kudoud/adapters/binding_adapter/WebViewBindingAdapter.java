package com.zksg.kudoud.adapters.binding_adapter;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.hjq.shape.view.ShapeButton;
import com.kunminx.architecture.domain.message.MutableResult;
import com.zksg.kudoud.R;
import com.zksg.kudoud.callback.WebViewClientCallback;
import com.zksg.kudoud.entitys.WebViewParmarsEntity;
import com.zksg.kudoud.state.Kline2OrderActivityViewModel;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class WebViewBindingAdapter {


    @BindingAdapter(value = {"meme_web_dexscreen","meme_web_loadfinish"},requireAll = false)
    public static void meme_web_progress_dexscreen(WebView web, String html,WebViewClientCallback callback) {
        if(web==null|| TextUtils.isEmpty(html) ||callback==null)return;
        // 启用 JavaScript 支持
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 设置WebView的WebChromeClient
        web.setWebChromeClient(new MyWebChromeClient());
        // 设置WebViewClient以确保在WebView中加载链接而不是默认的浏览器
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 页面开始加载时执行的操作
                Log.d("WebView", "Page started loading: " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // 注入 JavaScript 来监听 window.onload 事件
                view.evaluateJavascript(
                        "(function() { window.onload = function() { return 'All resources finished loading'; }})();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                Log.d("WebView", "All resources finished loading");

                                // 增加3秒延迟
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 3秒后执行的代码
//                                        Log.d("WebView", "Page finished loading and 3 seconds have passed: " + url);
                                        if(callback!=null) callback.onLoadFinish(true);
                                    }
                                }, 3000); // 3000ms = 3秒


                            }
                        }
                );


                // 页面加载完成时执行的操作
                Log.d("WebView", "Page finished loading: " + url);




            }
        });
        web.loadDataWithBaseURL(null,html, "text/html", "utf-8", null);

    }


    @BindingAdapter(value = {"meme_web_progress_loadurl","meme_web_loadfinish"},requireAll = false)
    public static void meme_web_progress_loadurl(WebView web, String url,WebViewClientCallback callback) {
        if(web==null|| TextUtils.isEmpty(url) ||callback==null)return;
        // 启用 JavaScript 支持
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 设置WebView的WebChromeClient
        web.setWebChromeClient(new MyWebChromeClient());
        // 设置WebViewClient以确保在WebView中加载链接而不是默认的浏览器
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 页面开始加载时执行的操作
                Log.d("WebView", "Page started loading: " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // 注入 JavaScript 来监听 window.onload 事件
                view.evaluateJavascript(
                        "(function() { window.onload = function() { return 'All resources finished loading'; }})();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                Log.d("WebView", "All resources finished loading");

                                // 增加3秒延迟
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 3秒后执行的代码
//                                        Log.d("WebView", "Page finished loading and 3 seconds have passed: " + url);
                                        if(callback!=null) callback.onLoadFinish(true);
                                    }
                                }, 3000); // 3000ms = 3秒


                            }
                        }
                );


                // 页面加载完成时执行的操作
                Log.d("WebView", "Page finished loading: " + url);




            }
        });
        web.loadUrl(url);

    }





    // 自定义WebChromeClient
    private static class MyWebChromeClient extends WebChromeClient {


        public MyWebChromeClient() {

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 更新进度条
//            progressBar.setProgress(newProgress);

//            if (newProgress == 100) {
                // 网页加载完成，隐藏进度条
//                progressBar.setVisibility(ProgressBar.GONE);
//            } else {
                // 网页加载中，显示进度条
//                progressBar.setVisibility(ProgressBar.VISIBLE);
//            }
        }
    }


}
