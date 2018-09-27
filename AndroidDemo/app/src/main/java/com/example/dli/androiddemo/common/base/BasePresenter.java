package com.example.dli.androiddemo.common.base;

public class BasePresenter<M extends BaseModel, V extends BaseView> implements IPresenter {

    protected M mModel;

    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mView = null;
    }
}