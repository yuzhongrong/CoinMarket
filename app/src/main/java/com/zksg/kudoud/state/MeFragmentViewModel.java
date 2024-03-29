package com.zksg.kudoud.state;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.utils.StringUtils;

public class MeFragmentViewModel extends ViewModel {
    public ObservableField<String> account= new ObservableField<>();
    public ObservableField<String> account_value= new ObservableField<>();
    public State<Integer> account_show= new State<>(View.GONE);

    public State<Integer> upload_show= new State<>(View.GONE);

    public State<String> balance=new State<>(StringUtils.num2thousand00("00.00"));
    public State<String> balance_dao=new State<>(StringUtils.num2thousand00("00.00"));

}
