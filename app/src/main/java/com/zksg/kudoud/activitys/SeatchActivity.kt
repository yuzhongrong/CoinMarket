package com.zksg.kudoud.activitys

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.adapters.SearchAdapter
import com.zksg.kudoud.databinding.ActivitySearchBinding
import com.zksg.kudoud.state.SearchActivityViewModel

class SeatchActivity : BaseDialogActivity() {

    var  clearIcon: Drawable?=null
    var  clearIconWidth=0
    var mSearchActivityViewModel: SearchActivityViewModel? = null
    var editText:EditText?=null
    override fun initViewModel() {
        mSearchActivityViewModel = getActivityScopeViewModel(
            SearchActivityViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_search, BR.vm, mSearchActivityViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.editWatcher, editWatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }


    @SuppressLint("ClickableViewAccessibility")
    fun initData(){
        val categorys = resources.getStringArray(R.array.category_str)
         clearIcon = resources.getDrawable(R.drawable.ic_clear)
         clearIconWidth = clearIcon?.intrinsicWidth!!
         editText = (binding as ActivitySearchBinding).etSearch

        editText!!.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (event.action === MotionEvent.ACTION_UP && event.getRawX() >= editText!!.right-clearIconWidth) {
                    // 点击了删除图标
                    editText!!.setText("")
//                    mSearchActivityViewModel?.searchAdapter?.get()?.data?.clear()
//                    mSearchActivityViewModel?.searchAdapter?.get()?.notifyDataSetChanged()
                    return true
                }
                return false
            }
        })

        var adapter= SearchAdapter(
            R.layout.item_today_app_h,
            null
            ,categorys
        )
        mSearchActivityViewModel?.searchAdapter?.set(adapter)
        mSearchActivityViewModel?.mSearchApks?.observe(this){
            Log.d("----mSearchApks-->",it.size.toString())
            adapter.setList(it)
//            if(it.isEmpty()){
//                adapter.setEmptyView(R.layout.recycle_emp_layout)
//            }else{
//                adapter.setList(it)
//            }
        }


        mSearchActivityViewModel?.loadingVisible?.observe(this){
            if(it) showDialog() else dismissDialog()
        }

    }

    inner class ClickProxy{

        fun startSearch(){
            // 1. 隐藏键盘
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(editText?.windowToken, 0)

            editText?.clearFocus()
            //用户体验
            Handler(Looper.getMainLooper()).postDelayed({
                var key=editText?.text.toString()
                if(!TextUtils.isEmpty(key))
                mSearchActivityViewModel?.getSearchAppsForButton(1,250,key)
            },500)
        }

    }

    private  val editWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isNotEmpty()) {
                editText?.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clearIcon, null)
                mSearchActivityViewModel?.getSearchApps(1,250,s.toString())

            } else {
                editText?.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            }

        }
        override fun afterTextChanged(s: Editable) {
            //transfor string

        }
    }


}