package com.example.schoolfix.Networking;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetryCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = "RetryCallAdapterFactory";

    public static RetryCallAdapterFactory create() {
        return new RetryCallAdapterFactory();
    }

    @Override
    public CallAdapter<?,?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        int itShouldRetry = 3;
        final Retry retry = getRetry(annotations);
        if (retry != null) {
            itShouldRetry = retry.max();
        }
        Log.d(TAG, "Starting a CallAdapter with {} retries." + itShouldRetry);

        return new RetryCallAdapter<>(
                retrofit.nextCallAdapter(this, returnType, annotations),
                itShouldRetry
        );
    }

    private Retry getRetry(@NonNull Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Retry) {
                return (Retry) annotation;
            }
        }
        return null;
    }


    private class RetryCallAdapter<R,T> implements CallAdapter<R,T> {
        private CallAdapter<R,T> delegated;
        private int maxRetries;

        public RetryCallAdapter(CallAdapter<?,?> delegated, int maxRetries) {
            this.delegated = (CallAdapter<R,T>) delegated;
            this.maxRetries = maxRetries;
        }

        @Override
        public Type responseType() {
            return delegated.responseType();
        }

        @Override
        public T adapt(Call<R> call) {
            return null;
        }

    }

    private class RetryingCall<R> implements Call<R> {

        private final Call<R> delegated;
        private final int maxRetries;

        public RetryingCall(Call<R> delegated, int maxRetries) {
            this.delegated = delegated;
            this.maxRetries = maxRetries;
        }

        @Override
        public Response<R> execute() throws IOException {
            return delegated.execute();
        }

        @Override
        public void enqueue(Callback<R> callback) {
            delegated.enqueue(new RetryCallback<>(delegated, callback, maxRetries));
        }

        @Override
        public boolean isExecuted() {
            return delegated.isExecuted();
        }

        @Override
        public void cancel() {
            delegated.cancel();
        }

        @Override
        public boolean isCanceled() {
            return delegated.isCanceled();
        }

        @Override
        public Call<R> clone() {
            return new RetryingCall<>(delegated.clone(), maxRetries);
        }

        @Override
        public Request request() {
            return delegated.request();
        }
    }

    private class RetryCallback<T> implements Callback<T> {
        private final Call<T> call;
        private final Callback<T> callback;
        private final int maxRetries;

        public RetryCallback(Call<T> call, Callback<T> callback, int maxRetries) {
            this.call = call;
            this.callback = callback;
            this.maxRetries = maxRetries;
        }

        private final AtomicInteger retryCount = new AtomicInteger(0);

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (!response.isSuccessful() && retryCount.incrementAndGet() <= maxRetries) {
                Log.d(TAG, "Call with no success result code: {} " + response.code());
                retryCall();
            } else {
                callback.onResponse(call, response);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.d(TAG, "Call failed with message:  " + t.getMessage(), t);
            if (retryCount.incrementAndGet() <= maxRetries) {
                retryCall();
            } else if (maxRetries > 0) {
                Log.d(TAG, "No retries left sending timeout up.");
                callback.onFailure(call,
                        new TimeoutException(String.format("No retries left after %s attempts.", maxRetries)));
            } else {
                callback.onFailure(call, t);
            }
        }

        private void retryCall() {
            Log.w(TAG, "" + retryCount.get() + "/" + maxRetries + " " + " Retrying...");
            call.clone().enqueue(this);
        }

    }
}
