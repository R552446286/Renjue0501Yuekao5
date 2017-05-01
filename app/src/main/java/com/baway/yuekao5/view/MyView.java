package com.baway.yuekao5.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.baway.yuekao5.R;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/5/1 15:35
 */
public class MyView extends View{

    private int outRadius;
    private int inRadius;
    private int ringColor;
    private int textSize;
    private String text;
    private int centerX;
    private int centerY;
    private float pointX;
    private float pointY;
    private Paint paint;
    private OnCircleClickListener listener;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.myView);
        outRadius = typedArray.getInt(R.styleable.myView_outRadius, -1);
        inRadius = typedArray.getInt(R.styleable.myView_inRadius,-1);
        ringColor = typedArray.getColor(R.styleable.myView_ringColor,-1);
        text = typedArray.getString(R.styleable.myView_text);
        textSize = typedArray.getInt(R.styleable.myView_textSize,-1);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnCircleClickListener{
        void onCircleClick(String text);
    }

    public void setOnCircleClickListener(OnCircleClickListener listener){
        this.listener=listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(outRadius*2,outRadius*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX=outRadius;
        centerY=outRadius;
        canvas.drawColor(Color.GREEN);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ringColor);
        paint.setAntiAlias(true);

        canvas.drawCircle(centerX,centerY,outRadius,paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX,centerX,inRadius,paint);

        paint.setTextSize(textSize);
        paint.setColor(Color.BLACK);
        canvas.drawText(text,centerX-text.length()/2*textSize,centerY+textSize/2,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            pointX=event.getX();
            pointY=event.getY();
            double range = Math.sqrt((centerX - pointX) * (centerX - pointX) + (centerY - pointY) * (centerY - pointY));
            String text="";
            if (range<=inRadius){
                text="在小圆内";
            }else if (range>inRadius&&range<=outRadius){
                text="在圆环内";
            }else if (range>outRadius){
                text="在圆环外";
            }
            if (listener!=null){
                listener.onCircleClick(text);
            }
        }
        return true;
    }
}
