package com.example.dli.androiddemo.model;

import com.example.dli.androiddemo.model.bean.User;
import com.example.dli.androiddemo.contract.LoginContract;
import com.example.dli.androiddemo.service.LoginService;
import com.example.dli.androiddemo.common.util.retrofit.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.ILoginModel {
    private User data;

    @Override
    public User loadUser() {
        if (data == null)
            return null;
        else
            return data;
    }

    @Override
    public void saveUser(User user) {
        if (user != null)
            data = user;
    }

    @Override
    public void login(User user, LoginContract.OnLoginListener loginListener) {
        if (user == null) return;
        RetrofitUtil.create(LoginService.class).
                login(user).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(objectResult -> {
                            if (objectResult.getCode() == 200) {
                                loginListener.loginSuccess(objectResult);
                                return;
                            }
                            loginListener.loginFailed(objectResult.getMsg());
                        }
                        , throwable -> loginListener.loginFailed(throwable.getMessage())
                );
    }

    @Override
    public void onDestroy() {
        data = null;
    }
}
