package com.ly.progressreport;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/**
 * Created by ly on 2019/6/27 16:39
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class MyCirclePB extends View {
    private CircleBarAnim anim;
    private float angle = 0;
    private float focusx, focusy;

    public MyCirclePB(Context context) {
        super(context);
        init();
    }

    public MyCirclePB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCirclePB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyCirclePB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        anim = new CircleBarAnim();
        anim.setDuration(5000);
        startAnimation(anim);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        Log.d("lylog", "w =" + w + " h=" + h);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setStrokeWidth(5);
        p.setAntiAlias(true);
        p.setColor(Color.parseColor("#FFB4F0DF"));
        p.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF();

        rectF.set(10, 10, 90, 90);
        focusx = rectF.centerX();
        focusy = rectF.centerY();

        canvas.drawArc(rectF, -240, 300, false, p);

        p.setColor(Color.parseColor("#FFEC9618"));

        canvas.drawArc(rectF, -240, angle, false, p);
        drawtext(canvas, angle);
    }

    private void drawtext(Canvas canvas, float angle) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.measureText((int) angle / 3 + "%");
        canvas.drawText((int) angle / 3 + "%", focusx - p.measureText((int) angle / 3 + "%") / 2, focusy, p);
    }

    public class CircleBarAnim extends Animation {

        public CircleBarAnim() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            angle = interpolatedTime * 300;
            postInvalidate();
        }
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
