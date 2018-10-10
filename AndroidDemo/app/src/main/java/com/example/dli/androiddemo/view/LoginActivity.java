package com.example.dli.androiddemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dli.androiddemo.R;
import com.example.dli.androiddemo.model.bean.User;
import com.example.dli.androiddemo.common.base.BaseMvpActivity;
import com.example.dli.androiddemo.common.util.result.Result;
import com.example.dli.androiddemo.component.DaggerLoginComponent;
import com.example.dli.androiddemo.contract.LoginContract;
import com.example.dli.androiddemo.module.LoginModule;
import com.example.dli.androiddemo.presenter.LoginPresenter;
import com.meis.widget.MeiTextPathView;
import com.meis.widget.loading.MeiFanView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.ILoginView, LoginContract.OnLoginListener {


    @BindView(R.id.et_Name)
    EditText userName;

    @BindView(R.id.et_Password)
    EditText userPassword;

    @BindView(R.id.mfv)
    MeiFanView meiFanView;

    @BindView(R.id.mt_pv)
    MeiTextPathView meiTextPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // 设置不可以滑动
        swipeBackLayout.setEnableGesture(false);
        // 调用
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
    }

    /*************  start  按钮的点击事件 *****************/

    public void login(View v) {
        this.stopAnimation();
        mPresenter.login();
    }

    /*************  end  按钮的点击事件 *****************/

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
        super.startActivity(this, NewsActivity.class);
    }

    @Override
    public void loginFailed(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        this.startAnimation();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.startAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopAnimation();
        if (userName != null)
            userName = null;
        if (userPassword != null)
            userPassword = null;
    }

    private void stopAnimation() {
        if (meiTextPathView != null)
            meiTextPathView.stopAnimation();
        if (meiFanView != null)
            meiFanView.stopAnimation();
    }

    private void startAnimation() {
        if (meiTextPathView != null)
            meiTextPathView.startAnimation();
        if (meiFanView != null)
            meiFanView.startAnimation();
    }
}
