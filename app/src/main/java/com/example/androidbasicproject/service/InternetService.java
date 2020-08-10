package com.example.androidbasicproject.service;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.androidbasicproject.BuildConfig;
import com.example.androidbasicproject.utils.CommonUtils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InternetService {

    private ChuckerInterceptor chuckerInterceptor;
    private static Retrofit retrofit;

    public InternetService(Context context) {
        chuckerInterceptor = new ChuckerInterceptor(context);
        initializeRetrofit();
    }

    public static ServicesApi getServicesApi() {
        return retrofit.create(ServicesApi.class);
    }

    private void initializeRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "token " + BuildConfig.GITHUB_TOKEN)
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(CommonUtils.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
