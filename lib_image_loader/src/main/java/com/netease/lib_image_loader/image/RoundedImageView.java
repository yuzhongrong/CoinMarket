package com.netease.lib_image_loader.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class RoundedImageView extends ImageView {

    private Paint paint;
    private Path clipPath;
    private RectF rectF;
    private int radius;
    private int bgColor;

    public RoundedImageView(Context context) {
        this(context, null);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        clipPath = new Path();
        rectF = new RectF();
        radius = 50;
        bgColor = 0xFFFF99;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        paint.setColor(bgColor);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        clipPath.reset();
        rectF.set(0, 0, getWidth(), getHeight());
        clipPath.addRoundRect(rectF, radius, radius, Path.Direction.CW);
        // int save = canvas.save();
        canvas.clipPath(clipPath);
        canvas.drawRect(rectF, paint);
        super.onDraw(canvas);
        // canvas.restoreToCount(save);
    }



}
