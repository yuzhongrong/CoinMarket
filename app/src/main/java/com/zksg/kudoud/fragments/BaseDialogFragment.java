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

public abstract class BaseDialogFragment extends BaseFragment {
    private BasePopupView dialog=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root= super.onCreateView(inflater, container, savedInstanceState);
        dialog= new XPopup.Builder(getContext())
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new LoadingDialog(getContext()));
        return root;
    }

    public void showDialog(){
        if(dialog!=null&&dialog.isDismiss())dialog.show();
    }
    public void dismissDialog(){
        if(dialog!=null&&dialog.isShow())dialog.dismiss();
    }

}
