package com.zksg.kudoud.fragments

import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CommonAdapter_V
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.state.CategoryCommonFragmentViewModel
import com.zksg.lib_api.beans.HomeItem

class CategoryCommonFragment(id: Int) : BaseFragment() {
    var categoryId=id.toString()
    var mCategoryCommonFragmentViewModel: CategoryCommonFragmentViewModel? = null
    override fun initViewModel() {
        mCategoryCommonFragmentViewModel = getFragmentScopeViewModel(
            CategoryCommonFragmentViewModel::class.java
        )
        Log.e("HeartRateDayFragment", "initViewModel:$mCategoryCommonFragmentViewModel")
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_wallet, BR.vm, mCategoryCommonFragmentViewModel!!)
    }

    override fun loadInitData() {
        Log.d("---xxx.js->loadInitData", "loadInitData: ")
        val categorys = resources.getStringArray(R.array.category_str)

       var adapter= HomeCWAdapter_V(
            R.layout.item_today_app_h,
            null
            ,categorys
        )

        mCategoryCommonFragmentViewModel?.coininstallAdapter?.set(adapter)


        mCategoryCommonFragmentViewModel?.mHotApks?.observe(this){
            Log.d("----mHotApks-->",it?.size.toString())
            var dapter= mCategoryCommonFragmentViewModel?.coininstallAdapter?.get() as HomeCWAdapter_V
           if(it.isEmpty()){
               adapter.setEmptyView(R.layout.recycle_emp_layout)
           }else{
               dapter.setList(it)
           }


        }
        mCategoryCommonFragmentViewModel?.getCategoryApps(1,50,categoryId)


    }
}