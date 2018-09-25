package com.example.dli.androiddemo.presenter;

import com.example.dli.androiddemo.base.BasePresenter;
import com.example.dli.androiddemo.contract.LoginContract;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.ILoginView> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel loginModel, LoginContract.ILoginView loginView) {
        super(loginModel, loginView);
    }

    public void saveUser() {
        mModel.saveUser(mView.getUser());
    }

    public void loadUser() {
        mView.setUser(mModel.loadUser());
    }

    public void login() {
        mModel.login(mView.getUser(), mView.getOnLoginListener());
    }
}
