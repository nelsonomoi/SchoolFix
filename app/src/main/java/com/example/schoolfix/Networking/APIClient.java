package com.example.schoolfix.Networking;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    final  static String BASE_URL="http://1934d5fb.ngrok.io/api/";
//    final static String BASE_URL="https://royalexams.herokuapp.com/api/";

    public static Retrofit getAPIClient(Context context){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        AuthenticationInterceptor authenticationInterceptor=new AuthenticationInterceptor(context);

        OkHttpClient.Builder httpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(authenticationInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS);

        Retrofit.Builder builder=new Retrofit.Builder()
//                .addCallAdapterFactory(RetryCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        Retrofit retrofit=builder
                .client(httpClient.build())
                .build();

        return retrofit;
    }

}
