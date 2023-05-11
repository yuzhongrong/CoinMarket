package com.zksg.kudoud.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.zksg.kudoud.R;

public class ColorWheelView extends View {

    private static final int DEFAULT_BRIGHTNESS = 224;

    private float centerX;
    private float centerY;
    private float radius;

    private Paint huePaint;
    private Paint saturationPaint;
    private Paint brightnessOverlayPaint;
    private Paint centerPaint;
    private Paint smallCirclePaint;
    private float smallCenterx=0; //移动圆圈的圆点x
    private float smallCentery=0;//移动圆圈的圆点y

    private float smallCenterxTag=0; //移动圆圈的圆点x
    private float smallCenteryTag=0;//移动圆圈的圆点y

    private float smallradius=30;//默认移动圆圈半径为20
    private float smalllimitx_out_min=0;//限制移动最x轴外环最小单位
    private float smalllimitx_out_max=0;//限制移动最x轴外环最大单位
    private float smalllimity_out_min=0;//限制移动最外环y轴最小单位
    private float smalllimity_out_max=0;//限制移动最外环y轴最大单位
    private boolean isDrawSmallCircle=true;//是否去画小圆



    public ColorWheelView(Context context) {
        super(context);
    }

    public ColorWheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public ColorWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    {
        huePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        saturationPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        brightnessOverlayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        smallCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        smallCirclePaint.setStyle(Paint.Style.STROKE);
        smallCirclePaint.setStrokeWidth(8f);
        brightnessOverlayPaint.setColor(Color.BLACK);
        brightnessOverlayPaint.setAlpha(brightnessToAlpha(DEFAULT_BRIGHTNESS));
        centerPaint.setColor(Color.WHITE);
        smallCirclePaint.setColor(Color.WHITE);

    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) * 0.48f;
        centerX = w * 0.5f;
        centerY = h * 0.5f;
        smallCenterx=centerX+radius/2+smallradius;
        smallCentery=centerY;
        smalllimitx_out_min=centerX-radius;
        smalllimitx_out_max=centerX+radius;
        smalllimity_out_min=centerY-radius;
        smalllimity_out_max=centerY+radius;

        recomputeShader();
    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius, huePaint);
        canvas.drawCircle(centerX, centerY, radius, saturationPaint);
        canvas.drawCircle(centerX, centerY, radius, brightnessOverlayPaint);
        canvas.drawCircle(centerX, centerY, radius/2, centerPaint);
        drawSmallCircle(canvas,smallCenterx,smallCentery);

    }

    private void recomputeShader() {
        Shader hueShader = new SweepGradient(centerX, centerY,
                new int[] {
                        Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN,
                        Color.GREEN, Color.YELLOW, Color.RED},
                new float[] {
                        0.000f, 0.166f, 0.333f, 0.499f,
                        0.666f, 0.833f, 0.999f});
        huePaint.setShader(hueShader);

        Shader satShader = new RadialGradient(centerX, centerY, radius,
                Color.WHITE, 0x00FFFFFF,
                Shader.TileMode.CLAMP);
        saturationPaint.setShader(satShader);
    }

    public void setBrightness(int brightness) {
        brightnessOverlayPaint.setAlpha(brightnessToAlpha(brightness));
        invalidate();
    }

    private int brightnessToAlpha(int brightness) {
        return 255 - brightness;
    }

    private void drawSmallCircle(Canvas canvas,float smallCenterx,float smallCentery){
        canvas.drawCircle(smallCenterx, smallCentery, smallradius, smallCirclePaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.smallCenterx=event.getX();
        this.smallCentery=event.getY();


        float r=Distance2Center(centerX,centerY,smallCenterx,smallCentery);
        if(r>(radius-smallradius)||r<(smallradius+radius/2)){
            return false;
        }else{
            if(colorListener!=null){
                int pointColor=getColorAtPoint(smallCenterx,smallCentery);
                Log.d("pointColor", "onTouchEvent: "+pointColor);
                colorListener.getPointColor(pointColor);
            }

            this.invalidate();
            return true;
        }





    }


    //TODO:tip 计算点击未知到圆心的距离

    /**
     *
     * @param centerX:圆心坐标x
     * @param centerY:圆心坐标y
     * @param touchx:触摸坐标x
     * @param touchy:触摸坐标y
     * @return
     */
    private float Distance2Center(float centerX,float centerY,float touchx,float touchy){

        //点击位置x坐标与圆心的x坐标的距离
        float distanceX = Math.abs(centerX-touchx);
        //点击位置y坐标与圆心的y坐标的距离
        float distanceY = Math.abs(centerY-touchy);
        //点击位置与圆心的直线距离
        float distanceZ = (float) Math.sqrt(Math.pow(distanceX,2)+Math.pow(distanceY,2));

        return distanceZ;


    }


    private int getColorAtPoint(float eventX, float eventY) {
        float x = eventX - centerX;
        float y = eventY - centerY;
        double r = Math.sqrt(x * x + y * y);
        float[] hsv = {0, 0, 1};
        hsv[0] = (float) (Math.atan2(y, -x) / Math.PI * 180f) + 180;
        hsv[1] = Math.max(0f, Math.min(1f, (float) (r / radius)));
        return Color.HSVToColor(hsv);
    }



    private OnColorListener colorListener;
    public void setOnColorListener(OnColorListener colorListener){
        this.colorListener=colorListener;
    }
    public interface  OnColorListener{
        void getPointColor(int color);

    }
    public int getColor(){
        return getColorAtPoint(smallCenterx,smallCentery);
    }

}