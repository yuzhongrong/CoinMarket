package com.zksg.kudoud.fragments

import android.content.Intent
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.CreateEnvActivity
import com.zksg.kudoud.adapters.CategoryPagerAdapter
import com.zksg.kudoud.adapters.SceneAdapter
import com.zksg.kudoud.beans.CommonCategoryDataEnum
import com.zksg.kudoud.beans.CommonDataEnum
import com.zksg.kudoud.state.SceneFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel

class CategoryFragment:BaseFragment(){
    private var  sceneViewModel: SceneFragmentViewModel ?=null
    private var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        sceneViewModel=getFragmentScopeViewModel(SceneFragmentViewModel::class.java)
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_category,BR.vm,sceneViewModel!!)
           .addBindingParam(BR.adapter, SceneAdapter(context))
           .addBindingParam(BR.click,ClickProxy())

    }

    override fun loadInitData() {
//        sceneViewModel?.datas?.set(mutableListOf(EnvBean("哭声安抚",0,false,EnvBean.Type.NONE)
//            ,EnvBean("测试1",0,false,EnvBean.Type.NONE)
//            ,EnvBean("测试2",0,false,EnvBean.Type.NONE)
//            ,EnvBean("创建场景",0,false,EnvBean.Type.ADD)))
//
//
//
//        mSharedViewModel?.oneEnvNotify?.observe(this){
//
//            var olddatas= sceneViewModel?.datas?.get()
//            //TODO:tip  use stream collect to product a new collect
//            var newdatas=olddatas?.stream()?.collect(Collectors.toList())
//            newdatas?.add(newdatas?.size!! -1,it)
//            sceneViewModel?.datas?.set(newdatas)
//
//        }

        sceneViewModel?.indicatorTitle?.set(
            arrayOf(
                getString(R.string.str_wallet),
                getString(R.string.str_exchange),
                getString(R.string.str_dex),
                getString(R.string.str_other)
            )
        )
        sceneViewModel?.adapter?.set(
            CategoryPagerAdapter(
                childFragmentManager,
                arrayOf(
                    CommonCategoryDataEnum.WALLET,
                    CommonCategoryDataEnum.EXCHANGE,
                    CommonCategoryDataEnum.DEX,
                    CommonCategoryDataEnum.OTHER
                )
            )
        )

    }

    inner class ClickProxy{
        fun CreateEnv(){
           startActivity(Intent(requireContext(),CreateEnvActivity::class.java))
        }
   }

}