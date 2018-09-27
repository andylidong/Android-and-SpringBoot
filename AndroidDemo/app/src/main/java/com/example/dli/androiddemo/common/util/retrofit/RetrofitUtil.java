package com.example.dli.androiddemo.common.util.retrofit;

import com.example.dli.androiddemo.common.util.PropertiesUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static <T> T create(Class<T> clazz) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RetrofitUrl retrofitUrl = clazz.getAnnotation(RetrofitUrl.class);
        String url;
        if (retrofitUrl == null) {
            url = PropertiesUtil.getValue("baseUrl");
        } else {
            url = retrofitUrl.value();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }


}
