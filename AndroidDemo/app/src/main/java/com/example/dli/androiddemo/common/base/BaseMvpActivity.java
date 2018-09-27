package com.example.dli.androiddemo.common.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        this.mPresenter = null;
    }
}
