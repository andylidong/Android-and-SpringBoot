package com.example.dli.androiddemo.service;

import com.example.dli.androiddemo.model.bean.News;
import com.example.dli.androiddemo.common.util.result.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewsService {

    @GET("api/v1/recylerview")
    Observable<Result<List<News>>> getList();
}
