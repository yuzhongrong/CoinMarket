package com.zksg.kudoud.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.shape.view.ShapeButton;
import com.kunminx.architecture.ui.page.BaseActivity;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.ControlMusicPlayActivity;
import com.zksg.kudoud.dialogs.ControLightleverDialog;
import com.zksg.kudoud.dialogs.ControlColorsDialog;
import com.zksg.kudoud.dialogs.ControlColorsModelDialog;
import com.zksg.kudoud.dialogs.ControlDashboardDialog;
import com.zksg.kudoud.dialogs.ControlShakeDialog;
import com.zksg.kudoud.dialogs.ControlStarLightDialog;
import com.zksg.lib_api.beans.DeviceOptiontem;
import com.zksg.lib_api.beans.HomeItem;

import java.util.List;

public class DevideOptionAdapter extends BaseQuickAdapter<DeviceOptiontem, BaseViewHolder> {

    public DevideOptionAdapter() {
        super(R.layout.item_device_option);
    }


    public DevideOptionAdapter(int layoutResId, @Nullable List<DeviceOptiontem> data) {
        super(R.layout.item_device_option,data);
    }

    public DevideOptionAdapter(@Nullable List<DeviceOptiontem> data) {
        this(R.layout.item_device_option, data);
        Log.d("convert", "convert: "+data.size());
        addChildClickViewIds(R.id.open,R.id.root);
        setOnItemChildClickListener((adapter,view,position)->{

            switch (position){
                case 0:
                    break;
                case 1://设置摇摆
                     showShakeDialog();
                    break;
                case 2://
                    showMusicPlayDialog();
                    break;
                case 3:
                    switch (view.getId()){
                        case R.id.open:
                            break;
                        case R.id.root:
                            showStartSmallLightDialog();
                            break;
                    }

                    break;

                case 4:
                    switch (view.getId()){
                        case R.id.open:

                            break;
                        case R.id.root:
                            showStarLightDialog();
                            break;
                    }

                    break;

                case 5:
                    switch (view.getId()){
                        case R.id.open:

                            break;
                        case R.id.root:
                            showAmbientlightDialog();
                            break;
                    }

                    break;
                case 6:
                    showDashboardDialog();
                    break;


            }






        });

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DeviceOptiontem optiontem) {
        Log.d("convert", "convert: ");
        baseViewHolder.setImageResource(R.id.icon,optiontem.getIconId());
        baseViewHolder.setText(R.id.title,optiontem.getTitle());
        ShapeButton button= baseViewHolder.getView(R.id.open);
        if(optiontem.getType()==DeviceOptiontem.Type.NONE){

            button.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#ffffff")).intoBackground();

        }else{
            if(optiontem.isOpen()){
                //TODO tip:  r.color.xx设置下去无效
                button.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#cff32b")).intoBackground();
            }else{

                button.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#e8f69c")).intoBackground();
            }

        }


    }

    private void showShakeDialog(){
        new XPopup.Builder(getContext()).asCustom(new ControlShakeDialog(getContext()))
                .show();
    }

    private void showStarLightDialog(){
        new XPopup.Builder(getContext()).asCustom(new ControlColorsDialog(getContext()))
                .show();
    }

    private void showStartSmallLightDialog(){
        new XPopup.Builder(getContext()).asCustom(new ControLightleverDialog(getContext()))
                .show();
    }

    private void showAmbientlightDialog(){
        new XPopup.Builder(getContext()).asCustom(new ControlColorsModelDialog(getContext()))
                .show();
    }

    private void showDashboardDialog(){
        new XPopup.Builder(getContext()).asCustom(new ControlDashboardDialog(getContext()))
                .show();
    }


    private void  showMusicPlayDialog(){

//        new XPopup.Builder(getContext()).asCustom(new ControlMusicDialog((BaseActivity) getContext()))
//                .show();
        getContext().startActivity(new Intent(getContext(), ControlMusicPlayActivity.class));


    }

}
