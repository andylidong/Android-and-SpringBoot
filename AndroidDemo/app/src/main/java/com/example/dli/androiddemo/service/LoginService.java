package com.example.dli.androiddemo.service;

import com.example.dli.androiddemo.bean.User;
import com.example.dli.androiddemo.util.result.Result;
import com.example.dli.androiddemo.util.retrofit.RetrofitUrl;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@RetrofitUrl("http://10.1.3.155:9090")
public interface LoginService {

    @POST("/api/v1/login")
    @FormUrlEncoded
    Call<Result<Object>> login(@Field("name") String name, @Field("password") String password);


    @POST("/api/v1/login")
    @FormUrlEncoded
    Observable<Result<Object>> loginRxJava(@Field("name") String name, @Field("password") String password);
}
