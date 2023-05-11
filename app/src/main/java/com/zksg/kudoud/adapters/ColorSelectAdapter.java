package com.zksg.kudoud.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shape.view.ShapeImageView;
import com.hjq.shape.view.ShapeView;
import com.zksg.kudoud.R;
import com.zksg.lib_api.beans.ColorItem;
import com.zksg.lib_api.beans.HomeItem;

import java.util.List;
import java.util.stream.Collectors;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

public class ColorSelectAdapter extends BaseQuickAdapter<ColorItem, BaseViewHolder> {
    private final ObservableField<Integer> colorObservable=new ObservableField<>();
    public ColorSelectAdapter() {
        super(R.layout.item_circle);
    }


    public ColorSelectAdapter(int layoutResId, @Nullable List<ColorItem> data) {
        super(R.layout.item_circle,data);

    }

    public ColorSelectAdapter( @Nullable List<ColorItem> data) {

        this(R.layout.item_circle, data);
        Log.d("convert", "convert: "+data.size());

        //TODO tip:订阅色板
//        colorPickerView.subscribe((color, fromUser, shouldPropagate) -> {
//            // use the color
//            colorObservable.set(color);
//        });

        addChildClickViewIds(R.id.inner);
        addChildLongClickViewIds(R.id.inner);
        setOnItemChildClickListener((adapter,view,position)->{

        });
        setOnItemChildLongClickListener((adapter, view, position) -> {
            //清理所有select状态
            List<ColorItem> maps= getData().stream().map(s-> {
                s.setSelected(false);
                return s;
            }).collect(Collectors.toList());
            maps.get(position).setSelected(true);
            setNewData(maps);
            return false;
        });

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ColorItem item) {
        Log.d("convert", "convert: ");


        if(item.getType()== ColorItem.Type.ADD){


        }else{
            ShapeImageView shapeView= baseViewHolder.getView(R.id.inner);
            if(item.getColor() instanceof String){

                shapeView.getShapeDrawableBuilder().setSolidColor(Color.parseColor((String) item.getColor())).intoBackground();

            }else{
                shapeView.getShapeDrawableBuilder().setSolidColor((Integer) item.getColor()).intoBackground();
            }

            //处理长按选中状态
            if(item.isSelected()){
                shapeView.getShapeDrawableBuilder().setStrokeColor(Color.parseColor("#A0A0AB"))
                        .setStrokeWidth(10).intoBackground();
            }else{
                shapeView.getShapeDrawableBuilder().setStrokeColor(Color.parseColor("#FFFFFF"))
                        .setStrokeWidth(0).intoBackground();
            }

        }


    }


}
