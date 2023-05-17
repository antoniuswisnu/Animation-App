package com.example.animationapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class PulseAnimation extends View {

    private float mRadius;
    private final Paint mPaint = new Paint();
    private static final int COLOR_ADJUSTER = 5;
    private float mx,my;
    private static final int ANIMATION_DURATION = 400;
    private static final int ANIMATION_DELAY = 100;
    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        // Membuat Grow Animation
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        // Membuat Shrink Animation
        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius", getWidth(), 0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        // Membuat Repeat Animation
        ObjectAnimator repeatAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);
        repeatAnimator.setRepeatCount(3);
        repeatAnimator.setRepeatMode(ObjectAnimator.REVERSE);

        // Membuat AnimatorSet
//        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
//        mPulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator);
        mPulseAnimatorSet.play(repeatAnimator);
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mx,my,mRadius,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mx = event.getX();
            my = event.getY();
            if(mPulseAnimatorSet != null && mPulseAnimatorSet.isRunning()){
                mPulseAnimatorSet.cancel();
            }
            mPulseAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(float radius){
        mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius/COLOR_ADJUSTER);
        invalidate();
    }

    public PulseAnimation(Context context) {
        super(context);
    }

    public PulseAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



}
