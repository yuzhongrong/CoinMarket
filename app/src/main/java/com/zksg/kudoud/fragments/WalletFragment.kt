package com.zksg.kudoud.fragments

import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CommonAdapter_V
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.state.WalletFragmentViewModel
import com.zksg.lib_api.beans.HomeItem

class WalletFragment(id: Int) : BaseFragment() {
    var categoryId=id.toString()
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
        Log.d("---xxx->loadInitData", "loadInitData: ")
        val categorys = resources.getStringArray(R.array.category_str)

       var adapter= HomeCWAdapter_V(
            R.layout.item_today_app_h,
            null
            ,categorys
        )

        mWalletFragmentViewModel?.coininstallAdapter?.set(adapter)


        mWalletFragmentViewModel?.mHotApks?.observe(this){
            Log.d("----mHotApks-->",it?.size.toString())
            var dapter= mWalletFragmentViewModel?.coininstallAdapter?.get() as HomeCWAdapter_V
           if(it.isEmpty()){
               adapter.setEmptyView(R.layout.recycle_emp_layout)
           }else{
               dapter.setList(it)
           }


        }
        mWalletFragmentViewModel?.getCategoryApps(1,50,categoryId)


    }
}