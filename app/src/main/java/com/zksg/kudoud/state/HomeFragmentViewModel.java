package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel {
    public ObservableField<BaseQuickAdapter> todayHealthAdapter = new ObservableField<>();
    public ObservableField<BaseQuickAdapter> todayhotAdapter = new ObservableField<>();

    public ObservableField<BaseQuickAdapter> coininstallAdapter = new ObservableField<>();
    public ObservableField<List<String>> banner_datas = new ObservableField<>();

    {
        banner_datas.set(Arrays.asList("ipfs://QmP6UJ6cNYEh8BYbcdGKecZB1uKK3uKp5JWxnSkDQJZqQf",
                "ipfs://QmP6UJ6cNYEh8BYbcdGKecZB1uKK3uKp5JWxnSkDQJZqQf"

                ));
    }

}
