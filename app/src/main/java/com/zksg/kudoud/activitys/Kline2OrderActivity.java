package com.zksg.kudoud.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.CoinsDetailViewModel;
import com.zksg.kudoud.state.Kline2OrderActivityViewModel;
import com.zksg.kudoud.utils.HtmlUtils;

public class Kline2OrderActivity extends BaseDialogActivity {
    Kline2OrderActivityViewModel mKline2OrderActivityViewModel;
    private WebView webView;

    @Override
    protected void initViewModel() {
        mKline2OrderActivityViewModel=getActivityScopeViewModel(Kline2OrderActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_webview, BR.vm,mKline2OrderActivityViewModel)
                .addBindingParam(BR.click,new Kline2OrderActivity.ClickProxy());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String contract=getIntent().getStringExtra("contract");


        mKline2OrderActivityViewModel.loadingVisible.observe(this,it->{
            if(it){showDialog();}else{dismissDialog();}
        });

//       String html= HtmlUtils.loadHTMLFromAssets(this,"kline_widget.html");

       String localHtml="<!DOCTYPE html><html><head>    <meta charset=\"UTF-8\">    <title>Connect Metamask</title>    <script src=\"https://cdn.jsdelivr.net/npm/ethers@5.3.0/dist/ethers.min.js\"></script></head><body><style>    body, html {        margin: 0;        padding: 0;        height: 100%;    }    #dexscreener-embed {        position: fixed;        top: 0;        left: 0;        width: 100%;        height: 100%;    }    #dexscreener-embed iframe {        width: 100%;        height: 100%;        border: 0;    }</style><div id=\"dexscreener-embed\">    <iframe src=\"https://dexscreener.com/solana/"+contract+"?embed=1&theme=dark&info=0\">    </iframe></div></body></html>";
        
//        String html="<!DOCTYPE html><html><head>    <meta charset=\"UTF-8\">    <title>Connect Metamask</title>    <script src=\"https://cdn.jsdelivr.net/npm/ethers@5.3.0/dist/ethers.min.js\"></script></head><body><style>    body, html {        margin: 0;        padding: 0;        height: 100%;    }    #dexscreener-embed {        position: fixed;        top: 0;        left: 0;        width: 100%;        height: 100%;    }    #dexscreener-embed iframe {        width: 100%;        height: 100%;        border: 0;    }</style><div id=\"dexscreener-embed\">    <iframe src=\"https://dexscreener.com/solana/"+contract+"?embed=1&theme=dark\"></iframe></div></body></html>";
        mKline2OrderActivityViewModel.htmlStr.set(localHtml);

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

    public class ClickProxy {

        public void close(){
            Kline2OrderActivity.this.finish();
        }

    }


}