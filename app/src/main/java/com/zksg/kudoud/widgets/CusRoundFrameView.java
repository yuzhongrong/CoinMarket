package com.zksg.kudoud.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zksg.kudoud.R;

/**
 * 自定义带有正圆形边框的View
 */
public class CusRoundFrameView extends androidx.appcompat.widget.AppCompatImageView {
    public int backgroundColor = Color.WHITE;//最外圈实心圆背景色
    public int progressCount = 7;//绿色进度条个数

    public CusRoundFrameView(Context context) {
        super(context);
    }

    public CusRoundFrameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CusRoundFrameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int padding = 11;//内圈距离外框距离
        int strokeWidth = 10;//内圈宽度

        Paint paint = new Paint();
        paint.setAntiAlias(true);//打开抗锯齿





        // 画外面的正圆形
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        int circleRadiusAndCenter = getHeight()/2;
        canvas.drawCircle( circleRadiusAndCenter, circleRadiusAndCenter, circleRadiusAndCenter, paint);
        //画圆框(底图)
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        RectF rectf = new RectF(padding, padding, circleRadiusAndCenter*2-padding, circleRadiusAndCenter*2-padding);
        canvas.drawArc(rectf,-90,35,false,paint);
        for (int i = 2;i<=10;i++){
            canvas.drawArc(rectf,-90+36*(i-1),35,false,paint);
        }

        //画圆框（进度图）
        if (progressCount>0){
            paint.setColor(getResources().getColor(R.color.teal_700,null));
            //用来添加后面的圆弧
            if (progressCount == 1){
                canvas.drawArc(rectf,-90,35,false,paint);
            }else {
                canvas.drawArc(rectf,-90,35,false,paint);
                for (int i = 2;i<progressCount+1;i++){
                    canvas.drawArc(rectf,-90+36*(i-1),35,false,paint);
                }
            }
        }

        canvas.save();
    }
}

