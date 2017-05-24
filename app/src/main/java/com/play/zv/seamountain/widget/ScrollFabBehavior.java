package com.play.zv.seamountain.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.InterpolatorRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;

/**
 * Created by Zv on 2016/11/27.
 */

public class ScrollFabBehavior extends FloatingActionButton.Behavior {
    /*** 隐藏动画是否正在执行。*/
    private boolean isAnimatingOut = false;
    /*加速器*/
    public static final LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    public ScrollFabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    /**
     * 处理竖直方向的滑动
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //检测第一步
        Log.e("onNestedScroll()", "dyConsumed:"+dyConsumed);
        if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimatingOut && child.getVisibility() == View.VISIBLE) {//往下滑
            scaleHide(child);
        } else if ((dyConsumed < 0 || dyUnconsumed < 0) && child.getVisibility() != View.VISIBLE) {
            scaleShow(child);
        }
    }

    /**
     * 显示btn
     * @param child
     */
    private void scaleShow(FloatingActionButton child) {
        child.setVisibility(View.VISIBLE);
        ViewCompat.animate(child)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(200)
                .setListener(null)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start();
    }

    /**
     * 隐藏btn
     * @param child
     */
    private void scaleHide(FloatingActionButton child) {
        ViewCompat.animate(child)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(200)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }
        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOut = false;
            view.setVisibility(View.INVISIBLE);
            //检测第二步
            Log.e("onAnimationEnd()", "隐藏按钮");
        }
        @Override
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };
}
