package com.example.dli.androiddemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        registerActivityLifecycleCallbacks();
        Fresco.initialize(this);
    }

    private void registerActivityLifecycleCallbacks() {
        super.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static Context getContext() {
        if (context != null)
            return context;
        return null;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Fresco.shutDown();
    }
}
