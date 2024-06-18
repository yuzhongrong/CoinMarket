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

import static com.blankj.utilcode.util.StringUtils.getStringArray;
import static com.netease.lib_network.constants.config.ipfs_base_url;
import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeButton;
import com.kunminx.architecture.utils.ClickUtils;
import com.netease.lib_common_ui.HornizeItemView;
import com.netease.lib_common_ui.bannder.HolderCreator;
import com.netease.lib_common_ui.widget.ArtistSortView;
import com.netease.lib_common_ui.widget.CaptchaView;
import com.netease.lib_image_loader.app.ImageLoaderManager;
import com.netease.lib_network.entitys.QuoEntity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.suke.widget.SwitchButton;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.SelectWalletEntity;
import com.zksg.kudoud.state.ExchangeFragmentViewModel;
import com.zksg.kudoud.utils.manager.SimpleWallet;
import com.zksg.lib_api.beans.BannerBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by KunMinX at 19/9/18
 */
@SuppressWarnings("unused")
public class CommonBindingAdapter {

    @BindingAdapter(value = {"visible"}, requireAll = false)
    public static void visible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {"visible1"}, requireAll = false)
    public static void visible1(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    @BindingAdapter(value = {"notifyselect"}, requireAll = false)
    public static void select(View view, boolean isselect) {
        view.setSelected(isselect);
    }

    @BindingAdapter(value = {"visibleforstr"}, requireAll = false)
    public static void visible(View view, String str) {
        if(TextUtils.isEmpty(str)){

            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }


    }


    @BindingAdapter(value = {"layout_bg",}, requireAll = false)
    public static void layout_bg(ShapeLinearLayout view,boolean value) {
        if(view ==null)return;
        if(value){
            view.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#29313d")).intoBackground();
        }else{
            view.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#202630")).intoBackground();
        }

    }




    @BindingAdapter(value = {"webviewload","progressBar"}, requireAll = false)
    public static void webload(WebView webView, String url, ProgressBar progressBar) {
        if(webView==null||TextUtils.isEmpty(url)||progressBar==null)return;
        webView.getSettings().setJavaScriptEnabled(true);// Enable JavaScript in WebView (optional)
        // Set a WebViewClient to handle the webpage navigation within the WebView

        // Set a WebViewClient to handle the webpage navigation within the WebView
        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(android.view.View.GONE);
                } else {
                    progressBar.setVisibility(android.view.View.VISIBLE);
                }
            }
        });
        // Load the webpage

        // Load the webpage
        webView.loadUrl(url);

    }


    @BindingAdapter(value = {"loadimage"}, requireAll = false)
    public static void loadImage(ImageView view,String ipfsurl) {
        Log.d("---loadimagexxx---->","xxx");
        if(view==null|| TextUtils.isEmpty(ipfsurl))return;
        String murl=ipfs_base_url+ipfsurl;
        ImageLoaderManager.getInstance().displayImageForView(view,murl);
    }


    @BindingAdapter(value = {"loadint"}, requireAll = false)
    public static void loadint(TextView view,long str) {
        if(view==null)return;
        view.setText(str+"");
    }


    @BindingAdapter(value = {"loadtext"}, requireAll = false)
    public static void loadtext(TextView view,String str) {
        if(view==null|| TextUtils.isEmpty(str))return;
        view.setText(str);
    }


    @BindingAdapter(value = {"loadtextforcategory"}, requireAll = false)
    public static void loadtextforcategory(TextView view,String category) {
        if(view==null|| TextUtils.isEmpty(category))return;
        String[] categorys = getStringArray(R.array.category_str);
        int index=Integer.parseInt(category);
        String result=categorys[index];
        view.setText(result);
    }



    @BindingAdapter(value = {"textColor"}, requireAll = false)
    public static void setTextColor(TextView textView, int textColorRes) {
        textView.setTextColor(textView.getResources().getColor(textColorRes));
    }

    @BindingAdapter(value = {"selected"}, requireAll = false)
    public static void selected(View view, boolean select) {
        view.setSelected(select);
    }

    @BindingAdapter(value = {"alpha"}, requireAll = false)
    public static void setAlpha(View view, float alpha) {
        view.setAlpha(alpha);
    }

    @BindingAdapter(value = {"leftCompoundDrawable"}, requireAll = false)
    public static void setCompoundDrawables(TextView view, Drawable drawable) {
        view.setCompoundDrawables(drawable, null, null, null);
    }

    @BindingAdapter(value = {"backgroundDrawable"}, requireAll = false)
    public static void setBackGround(View view, Drawable alpha) {
        view.setBackground(alpha);
    }

    @BindingAdapter(value = {"imgalpha"}, requireAll = false)
    public static void setImageViewAlpha(ImageView view, int alpha) {
        view.setImageAlpha(alpha);
    }


    @BindingAdapter(value = {"imgdrawable"}, requireAll = false)
    public static void setImageViewDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }




    @BindingAdapter(value = {"onClickWithDebouncing"}, requireAll = false)
    public static void onClickWithDebouncing(View view, View.OnClickListener clickListener) {
        ClickUtils.applySingleDebouncing(view, clickListener);
    }

    @BindingAdapter(value = {"adjustWidth"})
    public static void adjustWidth(View view, int adjustWidth) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = adjustWidth;
        view.setLayoutParams(params);
    }

    @BindingAdapter(value = {"adjustHeight"})
    public static void adjustHeight(View view, int adjustHeight) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = adjustHeight;
        view.setLayoutParams(params);
    }

    @BindingAdapter(value = {"initCaptureViewListener"})
    public static void setListener(CaptchaView captchaView, CaptchaView.OnInputListener listener) {
        captchaView.setOnInputListener(listener);
    }

    @BindingAdapter(value = {"showLoadingAnim"})
    public static void showLoadingAnim(ImageView imageView, Boolean show) {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        if (show) {
            animationDrawable.start();
        } else {
            animationDrawable.stop();
        }
    }

    @BindingAdapter(value = {"onEditorActionListener"})
    public static void setOnEditorActionListener(EditText editText, TextView.OnEditorActionListener listener) {
        editText.setOnEditorActionListener(listener);
    }

    @BindingAdapter(value = {"textChangedListener"})
    public static void addTextChangedListener(EditText editText, TextWatcher listener) {
        editText.addTextChangedListener(listener);
    }

    @BindingAdapter(value = {"textEditClickListener"})
    public static void textfClickListener(EditText editText, View.OnClickListener listener) {
        editText.setOnClickListener(listener);
    }


    @BindingAdapter(value = {"textfocusChangedListener"})
    public static void textfocusChangedListener(EditText editText, View.OnFocusChangeListener listener) {
        editText.setOnFocusChangeListener(listener);
    }

    @BindingAdapter(value = {"checkChangedListener"})
    public static void checkChangedListener(SwitchButton checkBox, SwitchButton.OnCheckedChangeListener listener) {
        checkBox.setOnCheckedChangeListener(listener);
    }


    @BindingAdapter(value = {"viewPagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }



    @BindingAdapter(value = {"currentItem"})
    public static void setCurrentItem(ViewPager viewPager, int item) {
        viewPager.setCurrentItem(item);
    }

    @BindingAdapter(value = {"viewPagerOffsetLimit"})
    public static void setViewPagerAdapter(ViewPager viewPager, int limit) {
        viewPager.setOffscreenPageLimit(limit);
    }


    @BindingAdapter(value = {"onSmartRefreshListener"}, requireAll = false)
    public static void setOffsetChangeListener(SmartRefreshLayout refreshLayout, OnRefreshListener listener) {
        refreshLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter(value = {"onSmartLoadMoreListener"}, requireAll = false)
    public static void setOnLoadMoreListener(SmartRefreshLayout refreshLayout, OnLoadMoreListener listener) {
        refreshLayout.setOnLoadMoreListener(listener);
    }

    @BindingAdapter(value = {"onOffsetChangedListener"}, requireAll = false)
    public static void addOnOffsetChangedListener(AppBarLayout appBarLayout, AppBarLayout.OnOffsetChangedListener listener) {
        appBarLayout.addOnOffsetChangedListener(listener);
    }

    @BindingAdapter(value = {"bannerPic", "bannerListener"}, requireAll = false)
    public static void setDefault(ConvenientBanner<BannerBean> convenientBanner,
                                  List<BannerBean> banners,
                                  OnItemClickListener listener) {
        if(convenientBanner!=null&&banners!=null){
            Log.d("CommonBindingAdapter"," setDefault banners size is "+banners.size());
            convenientBanner.setPages(new HolderCreator(), banners)
                    .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setPageTransformer(new DefaultTransformer())
                    .setCanLoop(true);
            
        }

    }



    @BindingAdapter(value = "onChooseAreaListener")
    public static void setOnChooseListener(ArtistSortView sortView, ArtistSortView.OnChooseArtistSortListener listener) {
        sortView.setOnChooseArtistSortListener(listener);
    }

    //HornizeItemView 相关的BindingAdapter
    @BindingAdapter(value = "hornizeItemViewTileText")
    public static void setHornizeItemViewTileText(HornizeItemView view, CharSequence text) {
        view.getTileView().setText(text);
    }

    @BindingAdapter(value = "hornizeItemViewRightText")
    public static void setHornizeItemViewRightText(HornizeItemView view, CharSequence text) {
        view.getRightTextView().setText(text);
    }

    @BindingAdapter(value = "onClickHornizeItemView")
    public static void setOnClickHornizeItemView(HornizeItemView view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    @BindingAdapter(value = "shape_btn_solidcolor")
    public static void shape_btn_solidcolor(ShapeButton view, boolean value) {
        if(view==null)return;
        if(value){
            view.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#f7a600")).intoBackground();
        }else{

            view.getShapeDrawableBuilder().setSolidColor(Color.parseColor("#aeaeb0")).intoBackground();

        }
        view.setEnabled(value);
    }

    @BindingAdapter(value = {"smartlayout_auto"}, requireAll = false)
    public static void smartlayout_auto(SmartRefreshLayout view,boolean value) {
        if(view==null)return;
        if(value){
            view.autoRefresh();
        }else{
            if(view.isRefreshing()){
                view.finishRefresh();
            }

        }
    }

    @BindingAdapter(value = {"smartlayout_binding_refresh"}, requireAll = false)
    public static void smartlayout_binding_refresh(SmartRefreshLayout view, OnRefreshListener listner) {
        if(view==null||listner==null)return;
        view.setOnRefreshListener(listner);
    }

    @BindingAdapter(value = {"loadtext_quo","loadtext_quo_fromdecimal"}, requireAll = false)
    public static void loadtext_quo(TextView view, String value,String fromdecimal) {
        if(view==null||TextUtils.isEmpty(value)||TextUtils.isEmpty(fromdecimal))return;
        BigDecimal outAmount=new BigDecimal(value);
        BigDecimal result= outAmount.divide(new BigDecimal(Math.pow(10,Integer.parseInt(fromdecimal))));

        if(view.getId()==R.id.outputAmount){
            view.setText(result.toString());
        }else{
            view.setText("≈ "+result+" ");
        }

    }

    @BindingAdapter(value = {"loadtext_quo_rate","loadtext_quo_fromdecimal_rate","loadtext_quo_todecimal_rate"}, requireAll = false)
    public static void loadtext_quo(TextView view, QuoEntity value,String fromdecimal,String todecimal) {
        if(view==null||value==null)return;
        BigDecimal one=new BigDecimal(1);
        BigDecimal inputAmount=null;
        BigDecimal outputAmount=null;
        inputAmount= new BigDecimal(value.getInAmount()).divide(new BigDecimal(Math.pow(10,Integer.parseInt(fromdecimal))));
        Log.d("----inputAmount---->",inputAmount.toPlainString());
        outputAmount= new BigDecimal(value.getOutAmount()).divide(new BigDecimal(Math.pow(10,Integer.parseInt(todecimal))));
        Log.d("----outputAmount---->",outputAmount.toPlainString());

        BigDecimal result= one.divide(inputAmount).multiply(outputAmount);
        Log.d("----result---->",result.toPlainString());
        view.setText("≈ "+ result.toPlainString()+" ");

    }



    @BindingAdapter(value = {"loadtext_slippageBps"}, requireAll = false)
    public static void loadtext_slippageBps(TextView view,String str) {
        if(view==null|| TextUtils.isEmpty(str)) return;
        BigDecimal div=new BigDecimal(str).divide(new BigDecimal(100));
        view.setText(div.toString()+"%");
    }

    @BindingAdapter(value = {"loadtext_router"}, requireAll = false)
    public static void loadtext_router(TextView view,List<QuoEntity.RoutePlanDTO> router) {
        if(view==null|| router==null) return;
        view.setText(router.size()+">");
    }




}
