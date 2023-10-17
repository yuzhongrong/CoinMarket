package com.zksg.kudoud.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.gson.Gson
import com.netease.lib_image_loader.app.ImageLoaderManager
import com.netease.lib_network.constants.config
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.AppDetailActivity
import com.zksg.lib_api.beans.AppInfoBean

class HomeCWAdapter_V : BaseQuickAdapter<AppInfoBean, BaseViewHolder> {
    private lateinit var categorys: Array<String>

    constructor(layoutResId: Int) : super(layoutResId) {}
    constructor(layoutResId: Int, data: List<AppInfoBean?>?) : super(layoutResId,
        data as MutableList<AppInfoBean>?
    ) {
        setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int -> }
    }

    constructor(layoutResId: Int, data: List<AppInfoBean?>?, categorys: Array<String>) : super(
        layoutResId,
        data as MutableList<AppInfoBean>?
    ) {
        this.categorys = categorys
        setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            var info=this.data[position]

                val i = Intent(context, AppDetailActivity::class.java)
                i.putExtra("appinfo", this.data[position])
                context.startActivity(i)


        }
    }

    //    public CommonAdapter(@Nullable List<HomeItem> data) {
    //
    //        this(R.layout.item_today_health, data);
    //        Log.d("convert", "convert: "+data.size());
    //
    //    }
    override fun convert(baseViewHolder: BaseViewHolder, apk: AppInfoBean) {
        var url = ""
        val index = apk.app_category.toInt()
        val category = categorys[index]
        Log.d("convert", "convert:$index+$category")
        if (index == 2) {
            val array = Gson().fromJson(apk?.app_screen_4, Array<String>::class.java)
            var screenList= array.toList()
            url=config.ipfs_base_url +screenList.get(0)
            baseViewHolder.setText(R.id.action0,"OPEN")
        }else{
            url=config.ipfs_base_url + apk.app_icon
        }


        ImageLoaderManager.getInstance().displayImageForCorner(baseViewHolder.getView(R.id.icon), url,15)
        baseViewHolder.setText(R.id.title, apk.app_name)
        baseViewHolder.setText(R.id.type, category)
        baseViewHolder.setText(R.id.desctiption, apk.app_subtitle)
    }
}