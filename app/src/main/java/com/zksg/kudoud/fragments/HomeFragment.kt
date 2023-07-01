package com.zksg.kudoud.fragments

import android.os.Bundle
import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.adapters.HomeRecentAdapter
import com.zksg.kudoud.state.HomeFragmentViewModel
import com.zksg.lib_api.beans.HomeItem


class HomeFragment:BaseDialogFragment(){
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


        homeViewModel?.mPublishApks?.observe(this){
            Log.d("----mPublishApks-->",it?.size.toString())
           var dapter= homeViewModel?.todayHealthAdapter?.get() as HomeRecentAdapter
            dapter.setList(it)
        }

        homeViewModel?.mHotApks?.observe(this){
            Log.d("----mHotApks-->",it?.size.toString())
            var dapter= homeViewModel?.coininstallAdapter?.get() as HomeCWAdapter_V
            dapter.setList(it)
        }
        homeViewModel?.loadingVisible?.observe(this){
            Log.d("-loadingVisible-->",it.toString())
            if(it) showDialog() else dismissDialog()
        }





        homeViewModel?.todayHealthAdapter?.set(
            HomeRecentAdapter(R.layout.item_today_health,null)
        )


        val categorys = resources.getStringArray(R.array.category_str)
        homeViewModel?.coininstallAdapter?.set(
            HomeCWAdapter_V(
                R.layout.item_today_app_h,
                null
            ,categorys
            )
        )

//       XPopup.Builder(context)
//            .asLoading("",R.layout.delegate_normal_loading)
//            .show()
//        homeViewModel?.getRecentPublishApp(1,50)
//        homeViewModel?.getCwApps(1,50,1000)
        homeViewModel?.getHomeDatas()

    }





    }


