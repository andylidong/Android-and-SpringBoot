package com.example.dli.androiddemo.component;

import com.example.dli.androiddemo.view.LoginActivity;
import com.example.dli.androiddemo.module.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
