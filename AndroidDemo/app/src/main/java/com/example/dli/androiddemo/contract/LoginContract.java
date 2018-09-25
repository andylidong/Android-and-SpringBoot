package com.example.dli.androiddemo.contract;

import com.example.dli.androiddemo.base.BaseModel;
import com.example.dli.androiddemo.base.BaseView;
import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.util.result.Result;

public class LoginContract {

    public interface ILoginModel extends BaseModel {
        User loadUser();

        void saveUser(User user);

        void login(User user, OnLoginListener loginListener);
    }

    public interface ILoginView extends BaseView {

        void setUser(User user);

        User getUser();

        OnLoginListener getOnLoginListener();
    }

    public interface OnLoginListener {

        void loginSuccess(Result result);

        void loginFailed();
    }
}
