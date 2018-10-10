package com.example.dli.androiddemo.contract;

import com.example.dli.androiddemo.model.bean.News;
import com.example.dli.androiddemo.common.base.BaseModel;
import com.example.dli.androiddemo.common.base.BaseView;

import java.util.List;

public interface NewsContract {

    interface INewsModel extends BaseModel {
        List<News> getList(int type, OnLoadDataListener onLoadDataListener);
    }

    interface INewsView extends BaseView {
        OnLoadDataListener getOnLoadDataListener();
    }

    interface OnLoadDataListener {
        void loadData(List<News> data);
    }
}
