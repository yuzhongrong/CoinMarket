package com.zksg.kudoud.dialogs

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxj.xpopup.core.BottomPopupView
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.ColorSelectAdapter
import com.zksg.lib_api.beans.ColorItem
import top.defaults.colorpicker.ColorPickerView

class ControlColorsModelDialog(context: Context) : BottomPopupView(context) {
    private  val progressObs=ObservableField<Int>()
    private var mRecyclerView: RecyclerView? = null
    private var mPicker: ColorPickerView?=null
    private var imageView:ImageView?=null
    private var process_value:TextView?=null
    private var seekbar: IndicatorSeekBar?=null
    override fun onCreate() {
        super.onCreate()
        mRecyclerView = findViewById(R.id.rv_colors_list)
        mPicker=findViewById(R.id.colorPicker)
        imageView=findViewById(R.id.action_add)
        process_value=findViewById(R.id.progress_value)
        seekbar=findViewById(R.id.seekbar)
        initData()
    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_colors_model_setting
    }

    private fun initData() {
        //TODO tip : 默认给5个颜色
        var colors= mutableListOf(
            ColorItem("#F71816",false,ColorItem.Type.NONE),
            ColorItem("#F6A50C",false,ColorItem.Type.NONE),
            ColorItem("#FBF30B",false,ColorItem.Type.NONE),
            ColorItem("#34D630",false,ColorItem.Type.NONE),
            ColorItem("#2449F7",false,ColorItem.Type.NONE),
        )

        var layoutManager =object :LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false){

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        mRecyclerView?.setLayoutManager(layoutManager)
        mRecyclerView?.adapter=ColorSelectAdapter(colors)

        imageView?.setOnClickListener {
           var adapter= mRecyclerView?.adapter as ColorSelectAdapter
            if(adapter.data.size<=6){
                adapter.addData(ColorItem(mPicker?.color,false,ColorItem.Type.NONE))

            }
        }


        seekbar?.onSeekChangeListener=object:OnSeekChangeListener {

            override fun onSeeking(seekParams: SeekParams?) {
                Log.d("--->onSeeking", "onSeeking: "+seekParams?.progress)
                process_value?.text=seekParams?.progress.toString()+"%"
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {

                Log.d("--->onStartTrackingTouch", "onSeeking: "+seekBar?.progress)
            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

                Log.d("--->onStopTrackingTouch", "onSeeking: "+seekBar?.progress)
            }


        }




    }
}