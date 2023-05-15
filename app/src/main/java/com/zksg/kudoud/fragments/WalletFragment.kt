package com.zksg.kudoud.fragments

import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CommonAdapter_V
import com.zksg.kudoud.state.WalletFragmentViewModel
import com.zksg.lib_api.beans.HomeItem

class WalletFragment : BaseFragment() {
    var mWalletFragmentViewModel: WalletFragmentViewModel? = null
    override fun initViewModel() {
        mWalletFragmentViewModel = getFragmentScopeViewModel(
            WalletFragmentViewModel::class.java
        )
        Log.e("HeartRateDayFragment", "initViewModel:$mWalletFragmentViewModel")
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_wallet, BR.vm, mWalletFragmentViewModel!!)
    }

    override fun loadInitData() {
        val homeitems = mutableListOf(
            HomeItem(R.mipmap.item_heart_rate, "86", getString(R.string.str_item_rate)),
            HomeItem(R.mipmap.item_breathe, "22", getString(R.string.str_item_breathe)),
            HomeItem(R.mipmap.item_weight, "21", getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight, "23", getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight, "25", getString(R.string.str_item_weight)),
            HomeItem(
                R.mipmap.item_weight,
                "29",
                getString(R.string.str_item_weight)
            ),
        )
        Log.d("---xxx->loadInitData", "loadInitData: ")
        mWalletFragmentViewModel!!.coininstallAdapter.set(
            CommonAdapter_V(
                R.layout.item_today_app_h,
                homeitems
            )
        )
    }
}