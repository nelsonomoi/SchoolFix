package com.example.schoolfix.Networking;

import android.content.Context;

import com.example.schoolfix.Helpers.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    private Context context=null;

    public AuthenticationInterceptor(Context context) {
        this.context = context;
    }

    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();

        Request request = builder.build();

        if(original.url().encodedPath().equalsIgnoreCase("register") || original.url().encodedPath().equalsIgnoreCase("login") && original.method().equalsIgnoreCase("post")){
            return chain.proceed(request);
        }
        else {
            if(original.header("No-Authentication") == null) {
                PreferenceManager preferenceManager = new PreferenceManager(context);
                if (preferenceManager.fetchAuthToken("AUTH_TOKEN") != null) {
                    builder.header("Authorization", "Bearer " + preferenceManager.fetchAuthToken("AUTH_TOKEN"));
                    builder.header("Accept", "application/json");
                    builder.header("Content-Type", "appliaction/x-www-form-urlencoded");
                }
            }
            return chain.proceed(request);
        }
    }
}
