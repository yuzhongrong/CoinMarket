package com.zksg.kudoud.widgets;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 正方形图片做圆形
 */
public class CusCircleDrawable extends Drawable {
    private Paint mPaint;
    private float mWidth; //半个宽/高，半径
    private Bitmap mBitmap;
    private float mX;
    private float mY;

    public CusCircleDrawable(Bitmap bitmap, float width, float x, float y) {
        this.mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);//着色器 水平和竖直都需要填充满
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        mWidth = width;
        mX = x;
        mY = y;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle( mX, mY, mWidth, mPaint);//绘制圆形
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT; //设置系统默认，让drawable支持和窗口一样的透明度
    }
}