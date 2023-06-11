package com.zksg.kudoud.fragments

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_image_loader.ipfs.IPFSManager
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CommonAdapter
import com.zksg.kudoud.adapters.CommonAdapter_V
import com.zksg.kudoud.state.HomeFragmentViewModel
import com.zksg.lib_api.beans.HomeItem


class HomeFragment:BaseFragment(){
    private var  homeViewModel: HomeFragmentViewModel ?=null

    override fun initViewModel() {
        homeViewModel=getFragmentScopeViewModel(HomeFragmentViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_home,BR.vm,homeViewModel!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         initData()

    }

    fun  initData(){
        var homeitems= mutableListOf(
            HomeItem(R.mipmap.item_heart_rate,"86",getString(R.string.str_item_rate)),
            HomeItem(R.mipmap.item_breathe,"22",getString(R.string.str_item_breathe)),
            HomeItem(R.mipmap.item_weight,"21",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"23",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"25",getString(R.string.str_item_weight)),
            HomeItem(R.mipmap.item_weight,"29",getString(R.string.str_item_weight)),
            )
        homeViewModel?.todayHealthAdapter?.set(
            CommonAdapter(
                R.layout.item_today_health,
                homeitems
            )
        )

        homeViewModel?.todayhotAdapter?.set(
            CommonAdapter(
                R.layout.item_today_health,
                homeitems
            )
        )

        homeViewModel?.coininstallAdapter?.set(
            CommonAdapter_V(
                R.layout.item_today_app_h,
                homeitems
            )
        )

//       XPopup.Builder(context)
//            .asLoading("",R.layout.delegate_normal_loading)
//            .show()


    }




    }


