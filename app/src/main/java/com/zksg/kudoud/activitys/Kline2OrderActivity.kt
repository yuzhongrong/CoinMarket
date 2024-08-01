package com.zksg.kudoud.activitys

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.MemePoolListdapter
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter
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
        val contract = intent.getStringExtra("contract")
        var symbol = intent.getStringExtra("symbol")
        mKline2OrderActivityViewModel!!.symbol.set(symbol)
        //       String html= HtmlUtils.loadHTMLFromAssets(this,"kline_widget.html");


        val fragments = arrayOf<Fragment>(PoolFragment(contract,mKline2OrderActivityViewModel),CheckFragment(contract),IntroduceFragment(contract))
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager, fragments)
        mKline2OrderActivityViewModel!!.tabAdapter.set(adapter)



    }

    inner class ClickProxy {
        fun close() {
            finish()

        }
    }
}