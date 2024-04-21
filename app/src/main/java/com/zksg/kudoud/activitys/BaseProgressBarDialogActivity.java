package com.zksg.kudoud.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.LoadingDialog;
import com.zksg.kudoud.dialogs.LoadingHprogresbarDialog;

public abstract class BaseProgressBarDialogActivity extends BaseActivity {
    private BasePopupView dialog=null;
    private LoadingHprogresbarDialog loadingHprogresbarDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingHprogresbarDialog=new LoadingHprogresbarDialog(this);
        dialog= new XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(loadingHprogresbarDialog);


    }
    public void showDialog(){
        if(dialog!=null&&dialog.isDismiss())dialog.show();
    }
    public void dismissDialog(){
        if(dialog!=null&&dialog.isShow())dialog.dismiss();
    }

    public LoadingHprogresbarDialog getProgressDialog(){
        return loadingHprogresbarDialog;
    }


}
