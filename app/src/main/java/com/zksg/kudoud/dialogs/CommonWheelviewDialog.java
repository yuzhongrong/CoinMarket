package com.zksg.kudoud.dialogs;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopupext.adapter.ArrayWheelAdapter;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.popup.CommonPickerPopup;
import com.zksg.kudoud.R;

import java.util.Arrays;
import java.util.List;

public class CommonWheelviewDialog extends BottomPopupView {
    private int itemsVisibleCount = 7;
    private int itemTextSize = 16;
    public int dividerColor = 0xFFd5d5d5; //分割线的颜色
    public float lineSpace = 2.8f; // 条目间距倍数 默认2
    public int textColorOut = 0xFFa8a8a8; //分割线以外的文字颜色
    public int textColorCenter = Color.parseColor("#d7f137"); //分割线之间的文字颜色
    public int currentItem=1;
    private WheelView mWheelView;
    private List<String> datas;


    public CommonWheelviewDialog(@NonNull Context context,List<String> datas) {
        super(context);
        this.datas=datas;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mWheelView=findViewById(R.id.mCommonWheel);
        initWheelData(mWheelView,datas);

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_device_language;
    }


    private void initWheelData(WheelView wheelView, List<String> datas) {
        wheelView.setItemsVisibleCount(itemsVisibleCount);
        wheelView.setAlphaGradient(true);
        wheelView.setTextSize(itemTextSize);
        wheelView.setCyclic(false);
        wheelView.setDividerColor( popupInfo.isDarkTheme ? Color.parseColor("#444444") : dividerColor);
        wheelView.setDividerType(WheelView.DividerType.FILL);
        wheelView.setLineSpacingMultiplier(lineSpace);
        wheelView.setTextColorOut(textColorOut);
        wheelView.setTextColorCenter(popupInfo.isDarkTheme ? Color.parseColor("#CCCCCC") : textColorCenter);
        wheelView.isCenterLabel(false);
        wheelView.setCurrentItem(currentItem);
        wheelView.setAdapter(new ArrayWheelAdapter<>(datas));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(commonPickerListener!=null){
                    commonPickerListener.onItemSelected(index,datas.get(index));
                }
            }
        });
        if(popupInfo.isDarkTheme){
            applyDarkTheme();
        }else {
            applyLightTheme();
        }
    }

    private CommonPickerListener commonPickerListener;
    public CommonWheelviewDialog setCommonPickerListener(CommonPickerListener commonPickerListener){
        this.commonPickerListener = commonPickerListener;
        return this;
    }

}
