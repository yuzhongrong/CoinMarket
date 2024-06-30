package com.zksg.kudoud.activitys

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.state.TransationHistoryDetailActivityViewmodel
import com.zksg.kudoud.utils.CopyUtils


class TransationHistoryDetailActivity : BaseDialogActivity() {

    var mTransationHistoryEntity: TransationHistoryEntity?=null
    var wallet: String?=null

    var mTransationHistoryDetailActivityViewmodel: TransationHistoryDetailActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        mTransationHistoryDetailActivityViewmodel = getActivityScopeViewModel(
            TransationHistoryDetailActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_transation_history_detail,
            BR.vm,
            mTransationHistoryDetailActivityViewmodel!!
        ).addBindingParam(BR.click, ClickProxy())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){
        mTransationHistoryEntity = intent.getSerializableExtra("detail") as TransationHistoryEntity
        wallet=intent.getStringExtra("wallet")
        mTransationHistoryDetailActivityViewmodel!!.history.set(mTransationHistoryEntity)
        mTransationHistoryDetailActivityViewmodel!!.wallet.set(wallet)


    }






   inner  class ClickProxy {
        fun close() {
            finish()
        }



        fun copySenderAddress(){
            CopyUtils.copyToClipboard(this@TransationHistoryDetailActivity,mTransationHistoryDetailActivityViewmodel!!.history.get()!!.sender!!)
            ToastUtils.showShort(getString(R.string.str_copy_success))

        }
       fun copyReceiverAddress(){
           CopyUtils.copyToClipboard(this@TransationHistoryDetailActivity,mTransationHistoryDetailActivityViewmodel!!.history.get()!!.receiver)
           ToastUtils.showShort(getString(R.string.str_copy_success))

       }
       fun copyTxid(){
           CopyUtils.copyToClipboard(this@TransationHistoryDetailActivity,mTransationHistoryDetailActivityViewmodel!!.history.get()!!.signature)
           ToastUtils.showShort(getString(R.string.str_copy_success))

       }






    }











}