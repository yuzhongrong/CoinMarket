package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.adapters.CategoryPagerAdapter
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.EnvBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RankingFragmentViewModel : BaseLoadingViewModel() {
    val mRankingApks = MutableResult<List<AppInfoBean>>()
    var datas = ObservableField<List<EnvBean>>()
    var CategoryAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var indicatorTitle = ObservableField<Array<String>>()
    var todayHealthAdapter = ObservableField<BaseQuickAdapter<*, *>>()

    fun getRankFragment(page:Int,pageSize:Int,sort:String,order:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                loadingVisible.postValue(true)
                DataRepository.getInstance().getAppinfoListRanking(page,pageSize,sort,order){
                    if(it.responseStatus.isSuccess) mRankingApks.postValue(it.result.data.list)
                    loadingVisible.postValue(false)
                }
            }
        }

    }
}