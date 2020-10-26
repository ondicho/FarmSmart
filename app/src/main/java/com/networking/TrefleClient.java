package com.networking;


import com.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                .baseUrl(Constants.TREFLE_BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TrefleApi.class);
    }
}
