package com.example.dli.androiddemo.module;

import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.model.NewsModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {
    private NewsContract.INewsModel newsModel;

    private NewsContract.INewsView newsView;

    public NewsModule(NewsContract.INewsView newsView) {
        this.newsView = newsView;
    }


    @Provides
    @Singleton
    public NewsContract.INewsModel getModel() {
        newsModel = new NewsModel();
        return newsModel;
    }

    @Provides
    @Singleton
    public NewsContract.INewsView getView() {
        return this.newsView;
    }
}
