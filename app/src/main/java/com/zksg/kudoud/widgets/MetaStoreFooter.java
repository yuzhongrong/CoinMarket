package com.zksg.kudoud.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.util.SmartUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zksg.kudoud.R;

public class MetaStoreFooter extends LinearLayout implements RefreshFooter {

    private View root;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private TextView tv_mlookonchain;
    public MetaStoreFooter(Context context) {
        super(context);
        initView(context);
    }
    public MetaStoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }
    public MetaStoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    private void initView(Context context) {
        setGravity(Gravity.CENTER);
         root= LayoutInflater.from(context).inflate(R.layout.loading_refresh,null,false);
         mAVLoadingIndicatorView=root.findViewById(R.id.avi);
        tv_mlookonchain=root.findViewById(R.id.nomoredata);
        addView(root, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(SmartUtil.dp2px(60));
    }


    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPrimaryColors(int... colors) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int maxDragHeight) {
       root.setVisibility(VISIBLE);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        root.setVisibility(GONE);
        if (success){

        } else {

        }
        return 500;//延迟500毫秒之后再弹回
    }



    @SuppressLint("RestrictedApi")
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                root.setVisibility(VISIBLE);
//                mHeaderText.setText("下拉开始刷新");
//                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
//                mProgressView.setVisibility(GONE);//隐藏动画
//                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                root.setVisibility(VISIBLE);
//                root.setVisibility(VISIBLE);
//                mHeaderText.setText("正在刷新");
//                mProgressView.setVisibility(VISIBLE);//显示加载动画
//                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
                root.setVisibility(VISIBLE);
//                root.setVisibility(VISIBLE);
//                mHeaderText.setText("释放立即刷新");
//                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }
    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
//        if (noMoreData) {
//            tv_mlookonchain.setVisibility(VISIBLE);
//            mAVLoadingIndicatorView.setVisibility(GONE);
//        } else {
//            tv_mlookonchain.setVisibility(VISIBLE);
//            mAVLoadingIndicatorView.setVisibility(GONE);
//
//        }
        return false;
    }
}
