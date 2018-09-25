package com.example.dli.androiddemo.module;

import com.example.dli.androiddemo.contract.LoginContract;
import com.example.dli.androiddemo.model.LoginModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.ILoginView mILoginView;

    private LoginContract.ILoginModel mILoginModel;

    public LoginModule(LoginContract.ILoginView iLoginView) {
        mILoginView = iLoginView;
    }

    @Provides
    @Singleton
    LoginContract.ILoginView getView() {
        return mILoginView;
    }

    @Provides
    @Singleton
    LoginContract.ILoginModel getModel() {
        mILoginModel = new LoginModelImpl();
        return mILoginModel;
    }
}
