package com.example.dli.androiddemo.common.base;

import javax.inject.Inject;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

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
