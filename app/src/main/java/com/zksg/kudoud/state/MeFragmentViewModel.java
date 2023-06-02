package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class MeFragmentViewModel extends ViewModel {
    public ObservableField<String> account= new ObservableField<>();
    {
        account.set("Account");
    }

}
