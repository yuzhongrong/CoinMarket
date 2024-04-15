package com.zksg.kudoud.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.zksg.kudoud.R;

public class TestMetamaskActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView=findViewById(R.id.webView);
        // 启用 JavaScript 支持
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 添加 JavaScript 接口对象
        webView.addJavascriptInterface(new JavaScriptInterface(), "AndroidInterface");

        // 加载 HTML 页面
        webView.loadUrl("file:///android_asset/kline_widget.html");
    }

    public class JavaScriptInterface {
        @JavascriptInterface
        public void onConnectSuccess() {
            // 连接成功回调处理
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 连接成功的操作
                }
            });
        }

        @JavascriptInterface
        public void onConnectError(final String errorMessage) {
            // 连接失败回调处理
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 连接失败的操作，可以使用 errorMessage 提示用户失败原因
                }
            });
        }
    }

}
