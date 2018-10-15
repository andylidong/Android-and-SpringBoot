package com.example.dli.androiddemo.common.base;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;

public class BaseBehavior {


    public static class ListenerAnimatorEndBuild {

        private boolean isOutExecute = false;

        private ViewPropertyAnimatorListener outAnimatorListener;

        public ListenerAnimatorEndBuild() {
            outAnimatorListener = new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    isOutExecute = true;
                }

                @Override
                public void onAnimationEnd(View view) {
                    view.setVisibility(View.GONE);
                    isOutExecute = false;
                }

                @Override
                public void onAnimationCancel(View view) {
                    isOutExecute = false;
                }
            };
        }

        public boolean isFinish() {
            return !isOutExecute;
        }

        public ViewPropertyAnimatorListener build() {
            return outAnimatorListener;
        }
    }

    public static final ViewPropertyAnimatorListener DEFAULT_OUT_ANIMATOR_LISTENER = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
        }

        @Override
        public void onAnimationEnd(View view) {
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View view) {
        }
    };

    public static final FastOutSlowInInterpolator FASTOUTSLOWININTERPOLATOR = new FastOutSlowInInterpolator();

    public static void scaleShow(View view) {
        scaleShow(view, null);
    }

    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setInterpolator(FASTOUTSLOWININTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    public static void scaleHide(View view) {
        scaleHide(view, DEFAULT_OUT_ANIMATOR_LISTENER);
    }

    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FASTOUTSLOWININTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }
}
