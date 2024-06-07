package com.zksg.kudoud.state

import android.text.TextUtils
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.entitys.UiWalletToken
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.WalletUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections
import java.util.stream.Collectors

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class TransationHistorysActivityViewModel : BaseLoadingViewModel() {
    @JvmField
    var walletAddress = ObservableField<String>()

    @JvmField
    var localdatas = MutableResult<List<UiWalletToken>>()
    @JvmField
    var historys = MutableResult(listOf<TransationHistoryEntity>())
    @JvmField
    var isfinishRefresh=MutableResult(false)

    //没有更多数据
    @JvmField
   var nomoredata=ObservableField(false)
//    @JvmField
//   var loadType=MutableResult(0) //0:从网络加载数据,1:从本地加载数据
    @JvmField
    var loadType=MutableResult.Builder<Int>().setAllowNullValue(false).create()
    //提供展示搜索内容用的=localdatas+hotdatas
    @JvmField
    var amountdatas = MutableResult<List<UiWalletToken>>()
    //提供给搜索用的初始化还原数据源
    @JvmField
    var amountdatascache = MutableResult<List<UiWalletToken>>()
    //clear all to show and click to set ""
    @JvmField
    var clearAll=State(false)
    @JvmField
    var empty=State("")


    fun getAllHistorys(wallet: String,before:String,isShowDialog:Boolean){
        viewModelScope.launch {
            if(isShowDialog)loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getAllHistorys(wallet,before){
                    if(it.result!=null&&it.result.data.size>0) {
                        var plusResult = historys.value?.plus(it.result.data)
                        //如果初始化网络最新一笔交易txid=本地最新一笔的交易txid 代表没有新的交易
                        if (isTransationDiffHead(it.result.data)) {//没有新的交易
                            //切换到本地加载模式
                            loadType.postValue(1)
                            var localCacheDatas = getLocalTransactionsBeforeSignature(
                                wallet,
                                plusResult!!.first().signature,
                                15
                            )
                            historys.postValue(localCacheDatas)

                        } else {//加载网络数据
                            loadType.postValue(0)
                            historys.postValue(plusResult)

                        }
                        if (isShowDialog) loadingVisible.postValue(false)


                    }else if(it.result!=null&&it.result.data.size==0&&!TextUtils.isEmpty(before)){//没有更多数据

                        nomoredata.set(true)
                    }
                    isfinishRefresh.postValue(true)
                }


            }

        }

    }


    /**
     * 只要确保 newplusResult的首尾在本地范围 存在 就说明可以加载本地
     */
    fun isLoadCache(newplusResult:List<TransationHistoryEntity>):Boolean{
        //始终获取集合第一个
//        var firstBlockTime=plusResult.firstOrNull()?.blockTime?:0
        //始终获取本地缓存第一个
        var localfirst=WalletUtils.getLocalCacheTransationHistory(walletAddress.get()!!)
//        val localFirstBlockTime = localfirst.firstOrNull()?.blockTime?:0

        var isfindcache_start=localfirst.find { it.signature==newplusResult.firstOrNull()?.signature?:null }
        var isfindcache_end=localfirst.find { it.signature==newplusResult.lastOrNull()?.signature?:null }
        return  (isfindcache_start!=null)&&(isfindcache_end!=null)

    }

    fun isTransationDiffHead(newplusResult:List<TransationHistoryEntity>):Boolean{
        //始终获取集合第一个
        var firstBlockTime=newplusResult.firstOrNull()?.blockTime?:0
        //始终获取本地缓存第一个
        var localfirst=WalletUtils.getLocalCacheTransationHistory(walletAddress.get()!!)
        val localFirstBlockTime = localfirst.firstOrNull()?.blockTime?:0
       return firstBlockTime==localFirstBlockTime
//        if(localfirst!=null&&localfirst.size==0){
//            return false
//        }else{
//            var netTxHead=newplusResult.firstOrNull()?.signature
//            var isfindcache_start=localfirst.get(0).signature
//            return netTxHead.equals(isfindcache_start)
//
//        }



    }


    fun getLocalTransactionsBeforeSignature(
        wallet: String,
        startSignature: String,
        count: Int = 15
    ): List<TransationHistoryEntity> {

        val transactionHistoryList= WalletUtils.getLocalCacheTransationHistory(wallet)
        val startIndex = transactionHistoryList.indexOfFirst { it.signature == startSignature }
        return if (startIndex != -1) {
            val endIndex = (startIndex + count).coerceAtMost(transactionHistoryList.size)
//            if(startIndex+1==endIndex){
//                return emptyList()
//            }
            transactionHistoryList.subList(startIndex, endIndex)
        } else {
            emptyList() // 找不到签名返回空列表
        }
    }




}