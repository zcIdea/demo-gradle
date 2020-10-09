package com.example.demogradle.test;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangchuan
 */

public enum OkHttpClientObject {
    CLIENT;

    private OkHttpClient clientInstance;

    private static final int CONNECTION_TIME_OUT = 5;
    private static final int WRITE_TIME_OUT = 5;
    private static final int READ_TIME_OUT = 5;

    OkHttpClientObject() {
        clientInstance = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                /*.retryOnConnectionFailure(true)
                .addInterceptor(chain -> {
                    int retryNum = 0;
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    while (!response.isSuccessful() && retryNum < 2) {
                        retryNum++;
                        response = chain.proceed(request);
                    }
                    return response;
                })*/
                .build();
    }

    public OkHttpClient getClientInstance() {
        return clientInstance;
    }
}