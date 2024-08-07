package com.zksg.kudoud.adapters.binding_adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kunminx.architecture.utils.Utils;
import com.netease.lib_common_ui.navigator.CommonNavigatorCreater;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.MyPagerAdapter;
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.beans.DiscoveryChannelEnum;
import com.zksg.kudoud.widgets.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class MagicIndicatorBindingAdapter {

    //主界面 初始化TabLayout
    @BindingAdapter(value = {"initHomeMagicIndicator"}, requireAll = false)
    public static void bindHomeMagicIndocator(MagicIndicator magicIndicator, DiscoveryChannelEnum[] channels) {
        magicIndicator.setBackgroundColor(Color.WHITE);

        View rootView = magicIndicator.getRootView();
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        CommonNavigator commonNavigator = new CommonNavigator(Utils.getApp());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return channels.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(channels[index].getKey());
                simplePagerTitleView.setTextSize(19);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);

        ViewPagerHelper.bind(magicIndicator, viewPager);
    }


    // 带tab间距的 间距设置为20dp 适用于页面展示不开的情况下使用
    @BindingAdapter(value = {"commonIndicatorTitleWithDivide"}, requireAll = false)
    public static void bindCommonMagicIndocatorWithDivider(MagicIndicator magicIndicator, String[] channels) {
        if (channels != null) {
            ViewPager viewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
            magicIndicator.setBackgroundColor(magicIndicator.getContext().getColor(R.color.c_29313d));
            CommonNavigator commonNavigator = CommonNavigatorCreater.setDefaultNavigator(magicIndicator.getContext(), channels, viewPager);
            magicIndicator.setNavigator(commonNavigator);

            LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
            titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            titleContainer.setDividerDrawable(new ColorDrawable() {
                @Override
                public int getIntrinsicWidth() {
                    return UIUtil.dip2px(Utils.getApp(), 20);
                }
            });
            ViewPagerHelper.bind(magicIndicator, viewPager);
        }
    }

    @BindingAdapter(value = {"commonIndicatorTitle"}, requireAll = false)
    public static void bindCommonMagicIndocator(MagicIndicator magicIndicator, String[] channels) {
        if (channels != null) {
            ViewPager viewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
//            magicIndicator.setBackgroundColor(Color.WHITE);

            CommonNavigator commonNavigator = CommonNavigatorCreater.setDefaultNavigator(magicIndicator.getContext(), channels, viewPager);
            // 自适应模式
            commonNavigator.setAdjustMode(true);
            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, viewPager);
        }
    }

    @BindingAdapter(value = {"customIndicatorTitle"}, requireAll = false)
    public static void bindCusCommonMagicIndocator(MagicIndicator magicIndicator, String[] channels) {
        if (channels != null) {
//            ViewPager viewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
//            magicIndicator.setBackgroundColor(Color.WHITE);
//            CommonNavigator commonNavigator = CommonNavigatorCreater.setDefaultNavigator(magicIndicator.getContext(), channels, viewPager);
//            // 自适应模式
//            commonNavigator.setAdjustMode(true);
//            magicIndicator.setNavigator(commonNavigator);
//            ViewPagerHelper.bind(magicIndicator, viewPager);

//            magicIndicator.setBackgroundColor(Color.TRANSPARENT);
            CommonNavigator commonNavigator = new CommonNavigator(magicIndicator.getContext());
//            commonNavigator.setScrollPivotX(0.35f);
//            commonNavigator.setAdjustMode(true);
            ViewPager mViewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);

            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return channels == null ? 0 : channels.length;
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                    simplePagerTitleView.setText(channels[index]);
                    simplePagerTitleView.setTextSize(16f);
                    simplePagerTitleView.setTypeface(null, Typeface.BOLD);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#aeaeb0"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(index);
                        }
                    });
                    return simplePagerTitleView;

                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                    indicator.setFillColor(Color.parseColor("#00FFFFFF"));
                    indicator.setRoundRadius(18);
                    return indicator;


//                    LinePagerIndicator indicator = new LinePagerIndicator(context);
//                    float navigatorHeight = context.getResources().getDimension(R.dimen.dp30);
//                    float lineWidth = UIUtil.dip2px(context, 60);
//                    float lineHeight = navigatorHeight;
//                    indicator.setLineHeight(lineHeight);
//                    indicator.setRoundRadius(lineHeight / 2);
////                    indicator.setYOffset(borderWidth);
//                    indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                    indicator.setLineWidth(lineWidth);
//                    indicator.setColors(Color.parseColor("#f6a50c"));
//                    return indicator;

                }
            });

            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, mViewPager);


        }
    }



    @BindingAdapter(value = {"memecustomIndicatorTitle"}, requireAll = false)
    public static void memebindCusCommonMagicIndocator(MagicIndicator magicIndicator, String[] channels) {
        if (channels != null) {

            CommonNavigator commonNavigator = new CommonNavigator(magicIndicator.getContext());
            ViewPager mViewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
            mViewPager.setOffscreenPageLimit(4);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return channels.length;
                }

                @Override
                public IPagerTitleView getTitleView(Context context, int index) {
                    SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                    simplePagerTitleView.setText(channels[index]);
                    simplePagerTitleView.setNormalColor(magicIndicator.getContext().getColor(R.color.colorTextNormal));
                    simplePagerTitleView.setSelectedColor(Color.WHITE);
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(index);
                        }
                    });
                    return simplePagerTitleView;
                }



                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setColors(Color.WHITE);
                    return indicator;
                }
            });

            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, mViewPager);


        }
    }


    @BindingAdapter(value = {"customIndicatorTitle_markets"}, requireAll = false)
    public static void customIndicatorTitle_markets(MagicIndicator magicIndicator, String[] channels) {
        if(magicIndicator==null)return;
        if (channels != null) {
//            ViewPager viewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
//            magicIndicator.setBackgroundColor(Color.WHITE);
//            CommonNavigator commonNavigator = CommonNavigatorCreater.setDefaultNavigator(magicIndicator.getContext(), channels, viewPager);
//            // 自适应模式
//            commonNavigator.setAdjustMode(true);
//            magicIndicator.setNavigator(commonNavigator);
//            ViewPagerHelper.bind(magicIndicator, viewPager);

//            magicIndicator.setBackgroundColor(Color.TRANSPARENT);
            // 设置ViewPager的适配器

            CommonNavigator commonNavigator = new CommonNavigator(magicIndicator.getContext());
//            commonNavigator.setScrollPivotX(0.35f);
//            commonNavigator.setAdjustMode(true);
            ViewPager mViewPager = magicIndicator.getRootView().findViewById(R.id.view_pager1);
            mViewPager.setAdapter(new MyPagerAdapter());
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return channels.length;
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                    simplePagerTitleView.setText(channels[index]);
                    simplePagerTitleView.setTextSize(14f);
                    simplePagerTitleView.setTypeface(null, Typeface.BOLD);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#aeaeb0"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(index);
                        }
                    });
                    return simplePagerTitleView;

                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
//                    WrapPagerIndicator indicator = new WrapPagerIndicator(context);
//                    indicator.setFillColor(Color.parseColor("#00FFFFFF"));
//                    indicator.setRoundRadius(18);
//                    return indicator;


                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    float navigatorHeight = context.getResources().getDimension(R.dimen.dp2);
//                    float lineWidth = UIUtil.dip2px(context, 60);
                    float lineHeight = navigatorHeight;
                    indicator.setLineHeight(lineHeight);
//                    indicator.setLineWidth(lineWidth);
                    indicator.setRoundRadius(lineHeight / 2);
//                    indicator.setYOffset(borderWidth);
                    indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);

                    indicator.setColors(Color.parseColor("#ffffff"));
                    indicator.setYOffset(6f);

                    return indicator;

                }
            });

            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, mViewPager);


        }
    }


    @BindingAdapter(value = {"customIndicator_titles","customIndicator_viewpagerid","customIndicator_tabAdapter"}, requireAll = false)
    public static void customIndicatorTitle_common(MagicIndicator magicIndicator, String[] channels, int viewpagerId, SimpleFragmentPagerAdapter tabAdapter) {
        if(magicIndicator==null||viewpagerId==0||tabAdapter==null)return;
        if (channels != null) {
//            ViewPager viewPager = magicIndicator.getRootView().findViewById(R.id.view_pager);
//            magicIndicator.setBackgroundColor(Color.WHITE);
//            CommonNavigator commonNavigator = CommonNavigatorCreater.setDefaultNavigator(magicIndicator.getContext(), channels, viewPager);
//            // 自适应模式
//            commonNavigator.setAdjustMode(true);
//            magicIndicator.setNavigator(commonNavigator);
//            ViewPagerHelper.bind(magicIndicator, viewPager);

//            magicIndicator.setBackgroundColor(Color.TRANSPARENT);
            // 设置ViewPager的适配器

            CommonNavigator commonNavigator = new CommonNavigator(magicIndicator.getContext());
//            commonNavigator.setScrollPivotX(0.35f);
//            commonNavigator.setAdjustMode(true);
            ViewPager mViewPager = magicIndicator.getRootView().findViewById(viewpagerId);
            mViewPager.setOffscreenPageLimit(3);
            mViewPager.setAdapter(tabAdapter);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return channels.length;
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                    simplePagerTitleView.setText(channels[index]);
                    simplePagerTitleView.setTextSize(14f);
                    simplePagerTitleView.setTypeface(null, Typeface.BOLD);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#aeaeb0"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(index);
                        }
                    });
                    return simplePagerTitleView;

                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
//                    WrapPagerIndicator indicator = new WrapPagerIndicator(context);
//                    indicator.setFillColor(Color.parseColor("#00FFFFFF"));
//                    indicator.setRoundRadius(18);
//                    return indicator;


                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    float navigatorHeight = context.getResources().getDimension(R.dimen.dp2);
//                    float lineWidth = UIUtil.dip2px(context, 60);
                    float lineHeight = navigatorHeight;
                    indicator.setLineHeight(lineHeight);
//                    indicator.setLineWidth(lineWidth);
                    indicator.setRoundRadius(lineHeight / 2);
//                    indicator.setYOffset(borderWidth);
                    indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);

                    indicator.setColors(Color.parseColor("#ffffff"));
                    indicator.setYOffset(6f);

                    return indicator;

                }
            });

            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, mViewPager);


        }
    }



}
