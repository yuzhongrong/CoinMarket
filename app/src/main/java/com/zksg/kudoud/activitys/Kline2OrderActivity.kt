package com.zksg.kudoud.activitys

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter
import com.zksg.kudoud.fragments.ChartKLineFragment
import com.zksg.kudoud.fragments.CheckFragment
import com.zksg.kudoud.fragments.IntroduceFragment
import com.zksg.kudoud.fragments.PoolFragment
import com.zksg.kudoud.state.Kline2OrderActivityViewModel

class Kline2OrderActivity : BaseDialogActivity() {
    var mKline2OrderActivityViewModel: Kline2OrderActivityViewModel? = null
    override fun initViewModel() {
        mKline2OrderActivityViewModel = getActivityScopeViewModel(
            Kline2OrderActivityViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_webview, BR.vm, mKline2OrderActivityViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val pair_address = intent.getStringExtra("contract")
        var pair_address="FmLmM2C3svyMmRwzTmnEjGnA5cD4YBLuPoQPixqZJG15"
        var symbol = intent.getStringExtra("symbol")
        mKline2OrderActivityViewModel!!.symbol.set(symbol)
        //       String html= HtmlUtils.loadHTMLFromAssets(this,"kline_widget.html");
        val localHtml = """<!DOCTYPE html>
                <html>
                <head>
                    <style>
                        html, body {
                            margin: 0;
                            padding: 0;
                            width: 100%;
                            height: 100%;
                            background-color: transparent;
                        }
                
                        .iframe-container {
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 100%;
                            height: 100%; /* 设置为父容器高度 */
                            overflow: hidden; /* 禁用滚动 */
                        }
                
                        .iframe-container iframe {
                            width: 100%;
                            height: 100%;
                            border: 0;
                        }
                    </style>
                </head>
                <body>
                    <div class="iframe-container">
                        <iframe id="dextools-widget"
                                title="DEXTools Trading Chart"
                                src="https://www.dextools.io/widget-chart/cn/solana/pe-light/$pair_address?theme=dark&chartType=1&headerColor=131722&chartResolution=1D&drawingToolbars=false">
                        </iframe>
                    </div>
                </body>
                </html>
                """
        mKline2OrderActivityViewModel!!.htmlStr.set(localHtml)

        val fragments = arrayOf<Fragment>(PoolFragment(),CheckFragment(),IntroduceFragment())
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager, fragments)
        mKline2OrderActivityViewModel!!.tabAdapter.set(adapter)

    }

    inner class ClickProxy {
        fun close() {
            finish()

        }
    }
}