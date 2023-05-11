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

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.kunminx.architecture.utils.ClickUtils;
import com.netease.lib_common_ui.HornizeItemView;
import com.netease.lib_common_ui.bannder.HolderCreator;
import com.netease.lib_common_ui.widget.ArtistSortView;
import com.netease.lib_common_ui.widget.CaptchaView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zksg.kudoud.R;
import com.zksg.kudoud.beans.LineChartBean;
import com.zksg.kudoud.widgets.SleepLevelView;

import java.util.List;

/**
 * Create by KunMinX at 19/9/18
 */
@SuppressWarnings("unused")
public class WidgetsBindingAdapter {


    @BindingAdapter(value = {"sleepDatas"}, requireAll = false)
    public static void onSleepDatas(SleepLevelView view, List<LineChartBean.GRID0DTO.ResultDTO.CompositeIndexShenzhenDTO> datas) {
        view.setDatas(datas);
    }




}
