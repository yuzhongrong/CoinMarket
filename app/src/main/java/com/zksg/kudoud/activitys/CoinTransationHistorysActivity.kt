package com.zksg.kudoud.activitys
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.tencent.mmkv.MMKV

import com.zksg.kudoud.BR
import com.zksg.kudoud.R

import com.zksg.kudoud.adapters.TransHistorysrdapter

import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.state.TransationHistorysActivityViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.WalletUtils


class CoinTransationHistorysActivity : BaseDialogActivity() {
    var viewModel: TransationHistorysActivityViewModel? = null
    var mSharedViewModel: SharedViewModel? = null
    var page=1
    var currentHistoryTxID=""

    var adapter:TransHistorysrdapter?=null
    var wallet=""

    override fun initViewModel() {
        viewModel = getActivityScopeViewModel(TransationHistorysActivityViewModel::class.java)
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        adapter=TransHistorysrdapter(this)
        return DataBindingConfig(R.layout.activity_transation_historys, BR.vm, viewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.historysAdaper,adapter!!)
            .addBindingParam(BR.loadmore,loadmore)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }


    private fun initData() {
        wallet=intent.getStringExtra("wallet")!!
        viewModel!!.walletAddress.set(wallet)
        viewModel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        //每次请求网络成功获取新的交易历史
        viewModel!!.historys.observe(this){



            if(it!=null&& it.isNotEmpty()){
                //最多只让加载50个数据
                if(it.size>=50){
                    viewModel!!.nomoredata.set(true)

                }else{

                    //如果第一个txid正好等于本地第一个txid 就说明目前本地还是最新的交易,加载本地交易记录


                    //设置加载下一页数据
                    it.get(it.size-1).let {
                        currentHistoryTxID=it.signature
                    }
                    //请求过的交易保存到本地缓存
                    if(viewModel!!.loadType.value==0){ //网络模式才去保存数据
                        WalletUtils.saveLocalCacheTransationHistory(wallet,it)
                    }else{
                        //缓存模式这里处理缓存加载完切换成网络模式
                        var transactionHistoryList= WalletUtils.getLocalCacheTransationHistory(wallet)
                         if(transactionHistoryList.lastOrNull()?.signature.equals(currentHistoryTxID)){
                             viewModel!!.loadType.postValue(0)
                         }
                    }
                }

            }

        }


        //请求全部交易历史
        //获取当前钱包的地址
//        Log.d("TTTTTT", viewModel!!.walletAddress.get()!!)
        viewModel!!.getAllHistorys( viewModel!!.walletAddress.get()!!,"",true)//传入""默认拿最近30条记录
    }


    var loadmore=object: OnLoadMoreListener {

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            //从网络加载
            if(viewModel!!.loadType.value==0){
                viewModel?.getAllHistorys(wallet,currentHistoryTxID,false)
            }
            else if(viewModel!!.loadType.value==1){ //从本地加载
                //从本地拿缓存数据
                var localcachetransations=viewModel!!.getLocalTransactionsBeforeSignature(wallet,currentHistoryTxID,15)
                var plusResult= viewModel?.historys?.value?.plus(localcachetransations)
                viewModel?.historys?.postValue(plusResult)

                viewModel?.isfinishRefresh?.postValue(true)

            }
        }

    }





    inner class ClickProxy {
        fun close() {
            finish()
        }





    }
}