package com.zksg.kudoud.widgets;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.zksg.kudoud.R;

public class TwoColorProgressBar extends ProgressBar {
    private int firstColor;
    private int secondColor;
    private int gapWidth;
    private int cornerRadius;

    public TwoColorProgressBar(Context context) {
        super(context);
        init(null);
    }

    public TwoColorProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TwoColorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TwoColorProgressBar);
            firstColor = a.getColor(R.styleable.TwoColorProgressBar_firstColor, Color.GREEN);
            secondColor = a.getColor(R.styleable.TwoColorProgressBar_secondColor, Color.RED);
            gapWidth = a.getDimensionPixelSize(R.styleable.TwoColorProgressBar_gapWidth, 0);
            cornerRadius = a.getDimensionPixelSize(R.styleable.TwoColorProgressBar_cornerRadius, 0);
            a.recycle();
        } else {
            // 默认颜色设置为绿色和红色
            firstColor = Color.GREEN;
            secondColor = Color.RED;
            // 默认间隔宽度设置为0
            gapWidth = 0;
            // 默认圆角半径设置为0
            cornerRadius = 0;
        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        int progress = getProgress();
        int max = getMax();
        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        paint.setColor(secondColor);
        paint.setStyle(Paint.Style.FILL);
        float secondColorWidth = (float) width * progress / max;

        RectF secondRect = new RectF(0, 0, secondColorWidth, height);
        canvas.drawRoundRect(secondRect, cornerRadius, cornerRadius, paint);

        // 绘制间隔
        paint.setColor(Color.TRANSPARENT); // 设置为透明色
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(secondColorWidth, 0, secondColorWidth + gapWidth, height, paint);

        paint.setColor(firstColor);
        RectF firstRect = new RectF(secondColorWidth + gapWidth, 0, width, height);
        canvas.drawRoundRect(firstRect, cornerRadius, cornerRadius, paint);
    }


    public void setFirstColor(int color) {
        firstColor = color;
        invalidate();
    }

    public void setSecondColor(int color) {
        secondColor = color;
        invalidate();
    }

    public void setGapWidth(int width) {
        gapWidth = width;
        invalidate();
    }

    public void setCornerRadius(int radius) {
        cornerRadius = radius;
        invalidate();
    }
}
