package com.zksg.kudoud.state;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.domain.message.MutableResult;
import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.kudoud.utils.StringUtils;
import com.zksg.lib_api.beans.MemeBaseEntry;

import java.util.List;

public class MeFragmentViewModel extends BaseLoadingViewModel {
    public ObservableField<String> account= new ObservableField<>();
    public ObservableField<String> account_value= new ObservableField<>();
    public State<Integer> account_show= new State<>(View.GONE);
    public MutableResult<List<MemeBaseEntry>> mWalletTokens = new MutableResult<>();
    public ObservableField<Boolean> show_wallet= new ObservableField<>(false);

}
