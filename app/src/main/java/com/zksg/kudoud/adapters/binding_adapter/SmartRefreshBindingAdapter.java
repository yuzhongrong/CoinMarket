/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zksg.kudoud.adapters.binding_adapter;

import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.zksg.kudoud.beans.LineChartBean;
import com.zksg.kudoud.widgets.SleepLevelView;

import java.util.List;

/**
 * Create by KunMinX at 19/9/18
 */
@SuppressWarnings("unused")
public class SmartRefreshBindingAdapter {


    @BindingAdapter(value = {"smart_refresh_finish"}, requireAll = false)
    public static void smart_refresh_finish(SmartRefreshLayout view,boolean show) {
        if(view==null) return;
        if(view.isLoading()){
            view.finishLoadMore();
        }
        if(view.isRefreshing()){
            view.finishRefresh();
        }

    }




}
