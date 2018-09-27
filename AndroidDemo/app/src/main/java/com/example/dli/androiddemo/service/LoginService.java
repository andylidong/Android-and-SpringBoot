package com.example.dli.androiddemo.service;

import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.common.util.result.Result;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @POST("api/v1/login")
    Observable<Result<Object>> login(@Body User user);


    @POST("api/v1/loginRxJava")
    @FormUrlEncoded
    Observable<Result<Object>> loginRxJava(@Field("name") String name, @Field("password") String password);
}
