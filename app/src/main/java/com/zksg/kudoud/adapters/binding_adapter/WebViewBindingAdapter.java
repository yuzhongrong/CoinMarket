package com.zksg.kudoud.adapters.binding_adapter;

import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.zksg.kudoud.R;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class WebViewBindingAdapter {

    @BindingAdapter(value = {"meme_web_chart"},requireAll = false)
    public static void loadmemewebview(WebView web, String url) {
//        WebSettings webSettings = web.getSettings();
//        webSettings.setJavaScriptEnabled(true); // 启用 JavaScript 支持
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setDisplayZoomControls(false)
//        web.setWebViewClient(new WebViewClient());
////        web.setWebChromeClient(new WebChromeClient());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//        web.loadUrl("https://birdeye.so/tv-widget/DezXAZ8z7PnrnRJjz3wXBoRgixCa6xjnB7YaB1pPB263?chain=solana&chartType=area&chartInterval=3&chartLeftToolbar=show");
    }


}
