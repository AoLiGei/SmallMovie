package com.happy.bwiesample.wigdet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.happy.bwiesample.R;

/**
 * Created by:
 * 樊健翔
 * 2017/12/23 10:50
 */

public class TheamCircleView extends View {

    private int colors;
    private Paint paint;
    private int radius;
    private int width;
    private int height;

    public TheamCircleView(Context context) {
        this(context, null);
    }

    public TheamCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TheamCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TheamCircleView);
        colors = typedArray.getColor(R.styleable.TheamCircleView_colors, 0xffff0000);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        radius = Math.min(width, height);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w / 2;
        height = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取高度一半
        //paint bg
        paint.setColor(colors);     //设置画笔颜色为随机颜色
        canvas.drawCircle(width, height, 50, paint);        //利用canvas画一个圆
    }
}
