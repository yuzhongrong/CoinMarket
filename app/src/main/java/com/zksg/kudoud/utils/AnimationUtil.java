package com.zksg.kudoud.utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class AnimationUtil {

    /**
     * 执行ImageView的旋转动画
     *
     * @param imageView 要旋转的ImageView
     * @param fromDegrees 起始角度
     * @param toDegrees 结束角度
     * @param durationMillis 动画持续时间（毫秒）
     */
    private static float currentRotation = 0f;
    public static void rotateImageView(ImageView imageView, float deltaDegrees, long durationMillis) {

        float fromDegrees = currentRotation;
        float toDegrees = currentRotation + deltaDegrees;
        RotateAnimation rotateAnimation = new RotateAnimation(
                fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(durationMillis);
        rotateAnimation.setFillAfter(true);
        imageView.startAnimation(rotateAnimation);
        // 更新当前旋转角度
        currentRotation = toDegrees % 360; // 保持角度在 0 到 360 度之间
    }
}

