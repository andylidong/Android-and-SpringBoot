package com.example.dli.androiddemo.model;

import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.contract.LoginContract;
import com.example.dli.androiddemo.service.LoginService;
import com.example.dli.androiddemo.util.retrofit.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModelImpl implements LoginContract.ILoginModel {
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
                loginRxJava(user.getName(), user.getPassword()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(objectResult -> loginListener.loginSuccess(objectResult)
                        , throwable -> loginListener.loginFailed()
                );
    }

    @Override
    public void onDestroy() {
        data = null;
    }
}
