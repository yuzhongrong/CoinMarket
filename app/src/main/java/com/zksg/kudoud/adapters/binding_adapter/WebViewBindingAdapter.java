package com.zksg.kudoud.adapters.binding_adapter;

import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.hjq.shape.view.ShapeButton;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.Kline2OrderActivityViewModel;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class WebViewBindingAdapter {


    @BindingAdapter(value = {"meme_web_dexscreen"},requireAll = false)
    public static void meme_web_progress_dexscreen(WebView web,String htmlContent) {
        if(web==null||htmlContent==null||htmlContent.equals(""))return;
        // 启用 JavaScript 支持
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置WebView的WebChromeClient
        web.setWebChromeClient(new MyWebChromeClient());
        // 设置WebViewClient以确保在WebView中加载链接而不是默认的浏览器
        web.setWebViewClient(new WebViewClient());
        web.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);
    }



    // 自定义WebChromeClient
    private static class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 更新进度条
//            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                // 网页加载完成，隐藏进度条
//                progressBar.setVisibility(ProgressBar.GONE);
            } else {
                // 网页加载中，显示进度条
//                progressBar.setVisibility(ProgressBar.VISIBLE);
            }
        }
    }


}
