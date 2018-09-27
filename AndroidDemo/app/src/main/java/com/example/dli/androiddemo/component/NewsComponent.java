package com.example.dli.androiddemo.component;

import com.example.dli.androiddemo.module.NewsModule;
import com.example.dli.androiddemo.view.NewsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsModule.class})
public interface NewsComponent {

    void inject(NewsActivity newsActivity);
}
