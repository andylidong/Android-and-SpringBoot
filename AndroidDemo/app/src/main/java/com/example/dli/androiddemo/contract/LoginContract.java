package com.example.dli.androiddemo.contract;

import com.example.dli.androiddemo.common.base.BaseModel;
import com.example.dli.androiddemo.common.base.BaseView;
import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.common.util.result.Result;

public interface LoginContract {

    interface ILoginModel extends BaseModel {
        User loadUser();

        void saveUser(User user);

        void login(User user, OnLoginListener loginListener);
    }

    interface ILoginView extends BaseView {

        void setUser(User user);

        User getUser();

        OnLoginListener getOnLoginListener();
    }

    interface OnLoginListener {

        void loginSuccess(Result result);

        void loginFailed();
    }
}
