package com.zksg.kudoud.widgets;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.zksg.kudoud.R;

public class CircularProgressBarCountDown extends View {

    private Paint paint;
    private RectF rect;
    private float angle = 360;
    private int strokeWidth = 20;
    private long duration = 25000;
    private OnCountDownFinishListener listener;

    public interface OnCountDownFinishListener {
        void onFinish();
    }

    public CircularProgressBarCountDown(Context context) {
        super(context);
        init(context);
    }

    public CircularProgressBarCountDown(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircularProgressBarCountDown(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);

        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width, height);
        int radius = min / 2 - strokeWidth;

        rect.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);
        canvas.drawArc(rect, -90, angle, false, paint);
    }

    public void setProgress(float progress) {
        this.angle = 360 * (1 - progress);
        invalidate();
    }

    public void setOnCountDownFinishListener(OnCountDownFinishListener listener) {
        this.listener = listener;
    }

    public void startCountDown() {
        new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                setProgress((float) millisUntilFinished / duration);
            }

            public void onFinish() {
                setProgress(0);
                if (listener != null) {
                    listener.onFinish();
                }

            }
        }.start();
    }
}
