package com.zksg.kudoud.fragments

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.MemePoolListdapter
import com.zksg.kudoud.state.Kline2OrderActivityViewModel
import com.zksg.kudoud.state.PoolsFragmentViewModel

class PoolFragment(contract: String?,mKline2OrderActivityViewModel: Kline2OrderActivityViewModel?) : BaseFragment() {
    var mContract=contract
    var mKline2OrderViewModel=mKline2OrderActivityViewModel
    var mPoolsFragmentViewModel: PoolsFragmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {
        mPoolsFragmentViewModel = getFragmentScopeViewModel(
            PoolsFragmentViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_pools, BR.vm, mPoolsFragmentViewModel!!)
            .addBindingParam(BR.adapter, MemePoolListdapter(requireContext()))
    }


    override fun loadInitData() {
        initObsever()
        mContract?.let { mPoolsFragmentViewModel!!.getTokenInfo(it) }
    }

    fun initObsever(){

        mPoolsFragmentViewModel!!.tokenInfo.observe(this){
            var pair_address=it.pairs.get(0).pairAddress
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
                                src="https://www.dextools.io/widget-chart/cn/solana/pe-light/$pair_address?theme=dark&chartType=1&headerColor=202630&tvPlatformColor=202630&tvPaneColor=202630&chartResolution=1D&drawingToolbars=false">
                        </iframe>
                    </div>
                </body>
                </html>
                """
            mKline2OrderViewModel!!.htmlStr.set(localHtml)
            mKline2OrderViewModel!!.pairs.postValue(it.pairs)
        }

    }


}