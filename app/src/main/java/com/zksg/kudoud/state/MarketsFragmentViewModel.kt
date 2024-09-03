package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zksg.kudoud.adapters.CategoryPagerAdapter
import com.zksg.kudoud.adapters.MemeCategoryPagerAdapter
import com.zksg.lib_api.beans.EnvBean

class MarketsFragmentViewModel : ViewModel() {
    var datas = ObservableField<List<EnvBean>>()
    var CategoryAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var indicatorTitle = ObservableField<Array<String>>()

    var adapter = ObservableField<MemeCategoryPagerAdapter?>()

    var memecategoryadapter = ObservableField<MemeCategoryPagerAdapter?>()
}