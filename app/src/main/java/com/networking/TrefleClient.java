//package com.networking;
//
//
//import com.Constants;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class TrefleClient {
//    public static Retrofit retrofit=null;
//
//    public static TrefleApi apiInstances(){
//        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
//
//        if (retrofit == null)   {
//
//            HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
//            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
//
//            okHttpClient.addInterceptor(httpLoggingInterceptor).build();
//        }
//        retrofit=new Retrofit
//                .Builder()
//                .baseUrl(Constants.TREFLE_BASE_URL)
//                .client(okHttpClient.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(TrefleApi.class);
//    }
//}


package com.networking;

import com.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrefleClient{

    private static Retrofit retrofit = null;

    public static TrefleApi getPlants() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .addHeader("Authorization", Constants.TREFLE_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

//            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TREFLE_LEFT_URL + Constants.TREFLE_API_KEY + Constants.TREFLE_RIGHT_URL + userInput).newBuilder();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.TREFLE_LEFT_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
            return retrofit.create(TrefleApi.class);
    }
}



























































}

