package com.zksg.kudoud.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.zksg.kudoud.R

class MyPagerAdapter : PagerAdapter() {
    override fun getCount(): Int {
        return 6 // 返回你的页面数量
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val textView = TextView(container.context)
        textView.text = "Page $position"
        textView.setTextColor(textView.context.getColor(R.color.white))
        textView.textSize=18f
        container.addView(textView)
        return textView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}