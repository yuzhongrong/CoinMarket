package com.zksg.kudoud.adapters

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.netease.lib_image_loader.app.ImageLoaderManager
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.AppDetailActivity
import com.zksg.lib_api.beans.MemeBaseEntry

class HomeMemeCWAdapter_V : BaseQuickAdapter<MemeBaseEntry, BaseViewHolder> {
    private lateinit var memes:MutableList<MemeBaseEntry>

    constructor(layoutResId: Int) : super(layoutResId) {}
    constructor(layoutResId: Int, data: List<MemeBaseEntry?>?) : super(layoutResId,
        data as MutableList<MemeBaseEntry>?
    ) {
        setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int -> }
    }

    constructor(layoutResId: Int, data: List<MemeBaseEntry?>?, memes: MutableList<MemeBaseEntry>) : super(
        layoutResId,
        data as MutableList<MemeBaseEntry>?
    ) {
        this.memes = memes
        setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            var info=this.data[position]

//                val i = Intent(context, AppDetailActivity::class.java)
//                i.putExtra("appinfo", this.data[position])
//                context.startActivity(i)


        }
    }

    //    public CommonAdapter(@Nullable List<HomeItem> data) {
    //
    //        this(R.layout.item_today_health, data);
    //        Log.d("convert", "convert: "+data.size());
    //
    //    }
    override fun convert(baseViewHolder: BaseViewHolder, meme: MemeBaseEntry) {
//        var url = ""
//        val index = apk.app_category.toInt()
//        val category = categorys[index]
//        Log.d("convert", "convert:$index+$category")
//        if (index == 2) {
//            val array = Gson().fromJson(apk?.app_screen_4, Array<String>::class.java)
//            var screenList= array.toList()
//            url=config.ipfs_base_url +screenList.get(0)
//            baseViewHolder.setText(R.id.action0,"OPEN")
//        }else{
//            url=config.ipfs_base_url + apk.app_icon
//        }
//
//
        ImageLoaderManager.getInstance().displayImageForCircle(baseViewHolder.getView(R.id.icon), meme.logoURI)

        baseViewHolder.setText(R.id.name, meme.symbol)
//        baseViewHolder.setText(R.id.vol, meme.v24hUSD)
        baseViewHolder.setText(R.id.price, "$179.23")
//        baseViewHolder.setText(R.id.change, meme.v24hChangePercent)
    }
}