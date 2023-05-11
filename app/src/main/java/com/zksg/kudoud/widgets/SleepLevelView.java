package com.zksg.kudoud.widgets;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.lxj.xpopup.util.XPopupUtils;
import com.zksg.kudoud.beans.LineChartBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**TODO:tip 由于没有实际数据参考 模拟数据自定义view的 这个view可能在后面实际数据后重写
 * Description: 加载View
 * Create by dance, at 2018/12/18
 */
public class SleepLevelView extends View {

    //数据设置
    private List<LineChartBean.GRID0DTO.ResultDTO.CompositeIndexShenzhenDTO> datas;


    private int paint_with_half=27;
    private int y_space=90;
    //虚线睡眠画笔
    private Paint linepaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    //清醒状态画笔
    private Paint normalpaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int y=0+y_space;
    //浅度睡眠画笔
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int y1=y+y_space;

    //深度睡眠画笔
    private Paint depthpaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int y2=y1+y_space;

    //虚线画笔
    private Paint dottedpaint=new Paint(Paint.ANTI_ALIAS_FLAG);


    float x_interval=10;

    float vhight=0f;


    public SleepLevelView(Context context) {
        this(context, null);
    }

    public SleepLevelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SleepLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(paint);
        initDepthPaint(depthpaint);
        initNormalpaint(normalpaint);
        initDottedPaint(dottedpaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /** TODO:tip 目前x轴 使用的是固定间隔 x_interval 到生产环境需要动态计算 x_interval=(数据结束时间戳-数据开始时间戳)/getMeasuredHeight()
         * 
         */
        vhight=getMeasuredHeight();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        // 1 2 3 4 5
        // 2 3 4 5 1
        // 3 4 5 1 2
        // ...
        if(datas==null||datas.size()==0)return;

        float startX=0.0f;
        for (int i = 0;i<datas.size(); i++) {
            float xValue= Float.parseFloat(datas.get(i).getRate());
            if(xValue<0){
                if((datas.get(i).getStart()).equals("1")){
                    canvas.drawLine(startX,y,startX,y+y_space-paint_with_half,dottedpaint);
                }
                canvas.drawLine(startX, y, startX=startX+x_interval,y, normalpaint);
                if((datas.get(i).getEnd()).equals("1")){
                    canvas.drawLine(startX,y,startX,y+y_space-paint_with_half,dottedpaint);
                }


            }else if(xValue>0&&xValue<=1){
                canvas.drawLine(startX, y1, startX=startX+x_interval,y1, paint);
            }else if(xValue>1){

                if((datas.get(i).getStart()).equals("1")){
                    canvas.drawLine(startX,y2-paint_with_half,startX,y2-y_space,dottedpaint);
                }
                canvas.drawLine(startX, y2, startX=startX+x_interval,y2, depthpaint);
                if((datas.get(i).getEnd()).equals("1")){
                    canvas.drawLine(startX,y2+paint_with_half*2,startX,y2+y_space+paint_with_half,dottedpaint);
                }
            }



            // 线的两端画个点，看着圆滑
//            canvas.drawCircle(startX, centerY, stokeWidth / 2, paint);
//            canvas.drawCircle(endX, centerY, stokeWidth / 2, paint);
//            canvas.rotate(avgAngle, centerX, centerY);
        }
//        postDelayed(increaseTask, 60);
        invalidate();
    }

//    private Runnable increaseTask = new Runnable() {
//        @Override
//        public void run() {
//            postInvalidate(0,0,getMeasuredWidth(), getMeasuredHeight());
//        }
//    };

//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        removeCallbacks(increaseTask);
//    }


    private void initPaint(Paint paint){
        paint.setColor(Color.parseColor("#62B1FF"));
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setPathEffect(new CornerPathEffect(200));
        paint.setStrokeWidth(55);

    };
    private void initDepthPaint(Paint depthpaint){
        depthpaint.setColor(Color.parseColor("#CD8BFF"));
        depthpaint.setStyle(Paint.Style.STROKE);
//        depthpaint.setStrokeCap(Paint.Cap.ROUND);
        depthpaint.setPathEffect(new CornerPathEffect(200));
        depthpaint.setStrokeWidth(55);

    };


    private void initNormalpaint(Paint normalpaint){

        normalpaint.setColor(Color.parseColor("#FF7E7E"));
        normalpaint.setStyle(Paint.Style.STROKE);
        normalpaint.setPathEffect(new CornerPathEffect(200));
        normalpaint.setStrokeWidth(55);

    }
    //虚线初始化
    private void initDottedPaint(Paint mPaintDotted){
        mPaintDotted.setStyle(Paint.Style.STROKE);
        mPaintDotted.setAntiAlias(true);
        mPaintDotted.setStrokeWidth(2);
        mPaintDotted.setColor(Color.parseColor("#4d7aad"));
        mPaintDotted.setPathEffect(new DashPathEffect(new float[]{4, 4}, 0));
    };


    public void setDatas(List<LineChartBean.GRID0DTO.ResultDTO.CompositeIndexShenzhenDTO> datas){
        this.datas=datas;
        invalidate();
        //设置数据的时候计算间隔
        //1.测量view的宽度
//        int mWidth=getMeasuredWidth();
        //2.计算x轴单位mill形式输出出来
//        String max=datas.get(datas.size()).getRate();
//        String min=datas.get(0).getRate();
//        long space=TimeUtils.getTimeSpan(max,min, TimeConstants.MSEC);
//        //space 毫秒转分钟
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(space);
//        x_interval=getMeasuredWidth()/minutes;//x间隔步长单位
//        Log.d("SleepLevelView", "x_interval: "+x_interval);



    }


}