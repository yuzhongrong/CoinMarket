package com.zksg.kudoud.adapters

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjq.shape.view.ShapeButton
import com.netease.lib_image_loader.app.ImageLoaderManager
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.AppDetailActivity
import com.zksg.kudoud.utils.ColorUtils
import com.zksg.kudoud.utils.DigitUtils
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

        if(meme.symbol==null)return
        ImageLoaderManager.getInstance().displayImageForCircle(baseViewHolder.getView(R.id.icon), meme.logoURI)
        baseViewHolder.setText(R.id.name, meme.symbol)
        if(meme.v24hUSD!=null){
            baseViewHolder.getView<TextView>(R.id.vol).text="Vol $"+DigitUtils.formatAmount(meme.v24hUSD)
        }else{
            baseViewHolder.getView<TextView>(R.id.vol).text=context.getText(R.string.str_vol_default)
        }

        if(meme.v24hChangePercent!=null){
           var change= baseViewHolder.getView<ShapeButton>(R.id.change)
            var percent=meme.v24hChangePercent as Double
            if(meme.symbol.equals("boden")){
                var ok=DigitUtils.isNegative(percent)
                Log.d("-----boden--->",ok.toString())
            }
            if(DigitUtils.isNegative(percent)){


            }
            change.text=DigitUtils.formatAmount(percent)+context.getString(R.string.str_precent)
        }else{
            baseViewHolder.getView<TextView>(R.id.change).text="--.--%"
        }

        if(meme.price!=null){
            baseViewHolder.getView<TextView>(R.id.price).text="$"+DigitUtils.formatAmount(meme.price)
        }else{
            baseViewHolder.getView<TextView>(R.id.price).text=context.getString(R.string.str_f_default)
        }


//        baseViewHolder.setText(R.id.change,meme.v24hChangePercent)
    }
}