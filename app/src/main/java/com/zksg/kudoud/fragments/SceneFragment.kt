package com.zksg.kudoud.fragments

import android.content.Intent
import android.util.Log
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.CreateEnvActivity
import com.zksg.kudoud.adapters.SceneAdapter
import com.zksg.kudoud.state.SceneFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.lib_api.beans.EnvBean
import java.util.stream.Collectors

class SceneFragment:BaseFragment(){
    private var  sceneViewModel: SceneFragmentViewModel ?=null
    private var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        sceneViewModel=getFragmentScopeViewModel(SceneFragmentViewModel::class.java)
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_scene1,BR.vm,sceneViewModel!!)
           .addBindingParam(BR.adapter, SceneAdapter(context))
           .addBindingParam(BR.click,ClickProxy())

    }

    override fun loadInitData() {
        sceneViewModel?.datas?.set(mutableListOf(EnvBean("哭声安抚",0,false,EnvBean.Type.NONE)
            ,EnvBean("测试1",0,false,EnvBean.Type.NONE)
            ,EnvBean("测试2",0,false,EnvBean.Type.NONE)
            ,EnvBean("创建场景",0,false,EnvBean.Type.ADD)))



        mSharedViewModel?.oneEnvNotify?.observe(this){

            var olddatas= sceneViewModel?.datas?.get()
            //TODO:tip  use stream collect to product a new collect
            var newdatas=olddatas?.stream()?.collect(Collectors.toList())
            newdatas?.add(newdatas?.size!! -1,it)
            sceneViewModel?.datas?.set(newdatas)

        }
    }

    inner class ClickProxy{
        fun CreateEnv(){
           startActivity(Intent(requireContext(),CreateEnvActivity::class.java))
        }
   }

}