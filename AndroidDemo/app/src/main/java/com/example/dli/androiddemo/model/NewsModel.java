package com.example.dli.androiddemo.model;

import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.util.retrofit.RetrofitUtil;
import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.service.NewsService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsModel implements NewsContract.INewsModel {

    @Override
    public List<News> getList(NewsContract.OnLoadDataListener onLoadDataListener) {
        RetrofitUtil.create(NewsService.class).getList().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(objectResult -> onLoadDataListener.loadData(objectResult.getData())
                        , throwable -> onLoadDataListener.loadData(null)
                );
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
