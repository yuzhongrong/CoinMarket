package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.zksg.kudoud.dialogs.LoadingDialog;

public abstract class BaseDialogActivity extends BaseActivity {
    private BasePopupView dialog=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog= new XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(true)
                .asCustom(new LoadingDialog(this));

    }
    public void showDialog(){
        if(dialog!=null&&dialog.isDismiss())dialog.show();
    }
    public void dismissDialog(){
        if(dialog!=null&&dialog.isShow())dialog.dismiss();
    }


}
