package com.example.dli.androiddemo.view.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.dli.androiddemo.common.base.BaseBehavior;

public class ScaleUpShowBehavior extends FloatingActionButton.Behavior {

    private BaseBehavior.ListenerAnimatorEndBuild listenerAnimatorEndBuild;

    public ScaleUpShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        listenerAnimatorEndBuild = new BaseBehavior.ListenerAnimatorEndBuild();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0)) && child.getVisibility() != View.VISIBLE) {
            BaseBehavior.scaleShow(child, null);
        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed < 0)) && child.getVisibility() != View.GONE && listenerAnimatorEndBuild.isFinish()) {
            BaseBehavior.scaleHide(child, listenerAnimatorEndBuild.build());
        }
    }
}
