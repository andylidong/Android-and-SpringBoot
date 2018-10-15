package com.example.dli.androiddemo.view.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.dli.androiddemo.common.base.BaseBehavior;

/**
 * <p>下拉时显示FAB，上拉隐藏，留出更多位置给用户。</p>
 * Created on 2016/7/14.
 */
public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    private BaseBehavior.ListenerAnimatorEndBuild listenerAnimatorEndBuild;

    private OnStateChangedListener mOnStateChangedListener;

    public ScaleDownShowBehavior(Context context, AttributeSet attrs) {
        super();
        listenerAnimatorEndBuild = new BaseBehavior.ListenerAnimatorEndBuild();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if ((dyConsumed > 0 || dyUnconsumed > 0) && listenerAnimatorEndBuild.isFinish() && child.getVisibility() == View.VISIBLE) {//往下滑
            BaseBehavior.scaleHide(child, listenerAnimatorEndBuild.build());
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(false);
            }
        } else if ((dyConsumed < 0 || dyUnconsumed < 0) && child.getVisibility() != View.VISIBLE) {
            BaseBehavior.scaleShow(child, null);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(true);
            }
        }
    }

    public void setOnStateChangedListener(OnStateChangedListener mOnStateChangedListener) {
        this.mOnStateChangedListener = mOnStateChangedListener;
    }

    // 外部监听显示和隐藏。
    public interface OnStateChangedListener {
        void onChanged(boolean isShow);
    }

    public static <V extends View> ScaleDownShowBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof ScaleDownShowBehavior)) {
            throw new IllegalArgumentException("The view is not associated with ScaleDownShowBehavior");
        }
        return (ScaleDownShowBehavior) behavior;
    }
}
