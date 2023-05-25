package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zksg.kudoud.adapters.CategoryPagerAdapter;
import com.zksg.lib_api.beans.EnvBean;

import java.util.List;

public class RankingFragmentViewModel extends ViewModel {
    public ObservableField<List<EnvBean>> datas = new ObservableField<>();
    public ObservableField<BaseQuickAdapter> CategoryAdapter = new ObservableField<>();
    public ObservableField<BaseQuickAdapter> coininstallAdapter = new ObservableField<>();
    public ObservableField<String[]> indicatorTitle=new ObservableField<>();
    public ObservableField<CategoryPagerAdapter> adapter=new ObservableField();
}
