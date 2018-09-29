package com.example.dli.androiddemo.model;

import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.constants.CRecyclerView;
import com.example.dli.androiddemo.common.util.retrofit.RetrofitUtil;
import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.service.NewsService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsModel implements NewsContract.INewsModel {

    private List<News> data = null;

    @Override
    public List<News> getList(int type, NewsContract.OnLoadDataListener onLoadDataListener) {
        RetrofitUtil.create(NewsService.class).getList().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(objectResult -> {
                            if (type == CRecyclerView.REFRESH) {
                                data = objectResult.getData();
                            } else {
                                data.addAll(objectResult.getData());
                            }
                            onLoadDataListener.loadData(data);
                        }
                        , throwable -> onLoadDataListener.loadData(null)
                );
        return null;
    }

    @Override
    public void onDestroy() {
        if (data == null || data.size() == 0)
            data = null;
    }
}
