package com.example.dli.androiddemo.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.dli.androiddemo.R;
import com.jaeger.library.StatusBarUtil;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity {

    protected SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeLayout();
        initStatusBar();
    }

    private void initSwipeLayout() {
        if (swipeBackLayout == null) {
            swipeBackLayout = getSwipeBackLayout();
        }
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (swipeBackLayout != null) {
            swipeBackLayout.requestLayout();
        }
    }

    protected void startActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


    protected void initToolBar(Activity currentActivity) {
        if (currentActivity == null) return;
        Toolbar toolbar = currentActivity.findViewById(R.id.toolbar);
        if (toolbar == null) return;
        toolbar.setTitle(currentActivity.getTitle());
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


}
