package com.example.dli.androiddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dli.androiddemo.base.BaseMvpActivity;
import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.component.DaggerLoginComponent;
import com.example.dli.androiddemo.contract.LoginContract;
import com.example.dli.androiddemo.module.LoginModule;
import com.example.dli.androiddemo.presenter.LoginPresenter;
import com.example.dli.androiddemo.util.result.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.ILoginView, LoginContract.OnLoginListener {


    @BindView(R.id.et_Name)
    EditText userName;

    @BindView(R.id.et_Password)
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // 调用
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
    }

    /*************  start  按钮的点击事件 *****************/

    public void saveUser(View v) {
        mPresenter.saveUser();
    }

    public void loadUser(View v) {
        mPresenter.loadUser();
    }

    public void login(View v) {
        mPresenter.login();
    }

    @Override
    public void setUser(User user) {
        if (user == null) return;
        userName.setText(user.getName());
        userPassword.setText(user.getPassword());
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setName(userName.getText().toString());
        user.setPassword(userPassword.getText().toString());
        return user;
    }

    @Override
    public LoginContract.OnLoginListener getOnLoginListener() {
        return this;
    }


    @Override
    public void loginSuccess(Result result) {
        Toast.makeText(this, result.getData().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userName != null)
            userName = null;
        if (userPassword != null)
            userPassword = null;
    }
}
