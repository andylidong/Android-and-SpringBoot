package com.example.dli.androiddemo.presenter;

import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.base.BasePresenter;
import com.example.dli.androiddemo.contract.NewsContract;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter extends BasePresenter<NewsContract.INewsModel, NewsContract.INewsView> {

    @Inject
    public NewsPresenter(NewsContract.INewsModel model, NewsContract.INewsView view) {
        super(model, view);
    }

    public List<News> getList(int type) {
        return mModel.getList(type, mView.getOnLoadDataListener());
    }
}
