package com.zksg.kudoud.adapters

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.gson.Gson
import com.netease.lib_image_loader.app.ImageLoaderManager
import com.netease.lib_network.constants.config
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.AppDetailActivity
import com.zksg.lib_api.beans.AppInfoBean

class HomeRecentAdapter : BaseQuickAdapter<AppInfoBean, BaseViewHolder> {
    constructor(layoutResId: Int) : super(layoutResId) {}
    constructor(layoutResId: Int, data: List<AppInfoBean?>?) : super(layoutResId,
        data as MutableList<AppInfoBean>?
    ) {
        setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
//            val i = Intent(context, AppDetailActivity::class.java)
//            i.putExtra("appinfo", this.data[position])
//            context.startActivity(i)
        }
    }

    //    public CommonAdapter(@Nullable List<HomeItem> data) {
    //
    //        this(R.layout.item_today_health, data);
    //        Log.d("convert", "convert: "+data.size());
    //
    //    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return super.onCreateDefViewHolder(parent, viewType)
    }
    override fun convert(baseViewHolder: BaseViewHolder, homeItem: AppInfoBean) {
        Log.d("convert", "convert: ")
//        var  url = config.ipfs_base_url + homeItem.app_icon
//        ImageLoaderManager.getInstance().displayImageForView(baseViewHolder.getView(R.id.icon), url)
//        baseViewHolder.setText(R.id.title, homeItem.app_name)
    }
}