package com.zksg.kudoud.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.zksg.kudoud.dialogs.LoadingDialog;

public abstract class BaseJobFragment extends BaseFragment {
    private BasePopupView dialog=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog= new XPopup.Builder(getContext())
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new LoadingDialog(getContext()));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void showDialog(){
        if(dialog!=null&&dialog.isDismiss())dialog.show();
    }
    public void dismissDialog(){
        if(dialog!=null&&dialog.isShow())dialog.dismiss();
    }

}
