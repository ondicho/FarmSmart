package com.moringaschool.farmsmart;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrefleClient {
    public static Retrofit retrofit=null;

    public static TrefleApi apiInstances(){
        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();

        if (retrofit == null)   {

            HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            okHttpClient.addInterceptor(httpLoggingInterceptor).build();
        }
        retrofit=new Retrofit
                .Builder()
                .baseUrl("https://trefle.io/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TrefleApi.class);
    }
}
