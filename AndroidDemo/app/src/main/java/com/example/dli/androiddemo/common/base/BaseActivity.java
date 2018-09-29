package com.example.dli.androiddemo.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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

    public void startActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


    protected void initToolBar(Activity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        if (toolbar == null) return;
        toolbar.setTitle(activity.getTitle());
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


}
