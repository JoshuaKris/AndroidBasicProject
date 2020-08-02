package com.example.androidbasicproject.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.androidbasicproject.utils.CommonUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InternetService {

    private ChuckerInterceptor chuckerInterceptor;
    private static Retrofit retrofit;
    private static ServicesApi servicesApi;

    public InternetService(Context context) {
        chuckerInterceptor = new ChuckerInterceptor(context);
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "token " + CommonUtils.API_TOKEN)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(CommonUtils.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static ServicesApi getServicesApi() {
        servicesApi = retrofit.create(ServicesApi.class);
        return servicesApi;
    }
}
