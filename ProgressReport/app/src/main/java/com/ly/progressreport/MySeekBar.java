package com.ly.progressreport;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ly on 2019/6/28 11:27
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class MySeekBar extends View {
    int pgw, pgh; //进度条的背景
    int bw, bh;//进度条button的背景
    private int progress = 0; //进度条的进度

    Paint bgP = new Paint();
    Paint bP = new Paint();
    Paint overp = new Paint();
    private SetOnSeekBarChangeListener setOnSeekBarChangeListener;

    public MySeekBar(Context context) {
        super(context);
    }

    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MySeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar);
        pgw = DpOrPxUtils.dip2px(context, typedArray.getDimension(R.styleable.MySeekBar_seek_width, 0));
        pgh = DpOrPxUtils.dip2px(context, typedArray.getDimension(R.styleable.MySeekBar_seek_height, 0));
        bgP.setColor(typedArray.getColor(R.styleable.MySeekBar_seek_background, 0));
        bP.setColor(typedArray.getColor(R.styleable.MySeekBar_seek_button_color, 0));
        overp.setColor(typedArray.getColor(R.styleable.MySeekBar_seek_over_background, 0));


        bh = pgh; //进度条button和背景高度保持一致
        bw = pgw / 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //先第一种方式 根据坐标变化而变化

        bgP.setStyle(Paint.Style.FILL);
        bgP.setAntiAlias(true);

        Rect r = new Rect();
        r.left = 0;
        r.top = 0;
        r.right = pgw;
        r.bottom = bh;
        canvas.drawRect(r, bgP);//背景可以不用变

        //计算坐标起点 int
        int startX = (pgw * progress) / 100;

        if (startX > (pgw - bw)) {
            startX = pgw - bw;
        }

        Log.d("lylog", " x = " + startX + " progress =" + progress);


        bP.setStyle(Paint.Style.FILL);
        bP.setAntiAlias(true);
        canvas.drawRect(new Rect(startX, 0, startX + bw, bh), bP);

        canvas.drawText(progress + "%", startX + bw / 2 - bP.measureText(progress + "%") / 2, 2 * bh, bP);


        overp.setStyle(Paint.Style.FILL);
        overp.setAntiAlias(true);
        canvas.drawRect(new Rect(0, 0, startX, bh), overp);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        Log.d("lylogs", "setProgress = " + progress);
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getX();
                setProgress((int) (x1*100/pgw));
                setOnSeekBarChangeListener.onProgressChanged((int)x1*100/pgw);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    public interface  SetOnSeekBarChangeListener{
          void onProgressChanged(int position);
    }

    public void setOnSeekBarChangeListener(SetOnSeekBarChangeListener setOnSeekBarChangeListener) {
        this.setOnSeekBarChangeListener = setOnSeekBarChangeListener;
    }
}
