package com.zksg.kudoud.activitys
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener

import com.zksg.kudoud.BR
import com.zksg.kudoud.R

import com.zksg.kudoud.adapters.TransHistorysrdapter

import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.state.TransationHistorysActivityViewModel
import com.zksg.kudoud.utils.WalletUtils


class CoinTransationHistorysActivity : BaseDialogActivity() {
    var viewModel: TransationHistorysActivityViewModel? = null
    var mSharedViewModel: SharedViewModel? = null
    var page=1
    var currentHistoryTxID=""
    var isloadMore=false
    var adapter:TransHistorysrdapter?=null

    override fun initViewModel() {
        viewModel = getActivityScopeViewModel(
            TransationHistorysActivityViewModel::class.java
        )
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

        viewModel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }

        //每次请求网络成功获取新的交易历史
        viewModel!!.historys.observe(this){



            if(it!=null&& it.isNotEmpty()){
                //最多只让加载50个数据
                if(it.size>=50){
                    isloadMore=false
//                    ToastUtils.showShort(getString(R.string.str_look_onchain_historys))

                }else{

                    //设置加载下一页数据
                    it.get(it.size-1).let {
                        currentHistoryTxID=it.signature
                        isloadMore=true
                    }

                }

            }else{
                isloadMore=false
            }

        }


        //请求全部交易历史
        //获取当前钱包的地址
        var wallet=WalletUtils.getCurrentSimpleWallet(mSharedViewModel!!).address
        viewModel!!.walletAddress.postValue(wallet)
        viewModel!!.getAllHistorys("75qj1YKiXGzWaY9YApCWjU9eAcUXV5YgJPGX9LLKKxiE","",true)//传入""默认拿最近30条记录
    }


    var loadmore=object: OnLoadMoreListener {
        override fun onLoadMore(refreshLayout: RefreshLayout) {
            //当集合最后一个txid=currentHistoryTxID
            if(isloadMore){
                viewModel?.getAllHistorys("75qj1YKiXGzWaY9YApCWjU9eAcUXV5YgJPGX9LLKKxiE",currentHistoryTxID,false)
            }
        }


    }


    inner class ClickProxy {
        fun close() {
            finish()
        }





    }
}