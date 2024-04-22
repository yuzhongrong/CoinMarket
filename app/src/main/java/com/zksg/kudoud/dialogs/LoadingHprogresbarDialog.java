package com.zksg.kudoud.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.zksg.kudoud.R;
import com.zksg.kudoud.widgets.CircularProgressBar;

/** TODO:tip 这里创建场景 如何通知全局？ 必须通过mvi模式或者使用全局viewmodel
 *
 */
public class LoadingHprogresbarDialog extends CenterPopupView {
    CircularProgressBar mProgressBar;
    @Override
    protected void onCreate() {
        super.onCreate();
        mProgressBar=findViewById(R.id.progress_bar_cus);
    }

    public LoadingHprogresbarDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_loading_h_progressbar_layout;
    }


    public void setProgress(Integer integer){
        if(mProgressBar!=null){
            mProgressBar.setProgress(integer);
            mProgressBar.setText(integer+"%");
        }
    }





}
