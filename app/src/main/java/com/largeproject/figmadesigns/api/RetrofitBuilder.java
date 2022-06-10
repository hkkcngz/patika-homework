package com.largeproject.figmadesigns.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://api.minerstat.com/";
    private static RetrofitBuilder INSTANCE;

    private RetrofitBuilder() {
    }

    public static RetrofitBuilder getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitBuilder();
                }
            }
        }

        return INSTANCE;
    }

    public Api getAPI() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }
}
