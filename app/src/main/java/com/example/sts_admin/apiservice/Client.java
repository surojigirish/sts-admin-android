package com.example.sts_admin.apiservice;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

<<<<<<<< HEAD:app/src/main/java/com/example/sts_admin/apiservice/AuthClient.java
public class AuthClient {

    private static final String BASE_URL = "http://192.168.232.169:5000/employee/";
========
public class Client {

    private final Retrofit retrofit;
>>>>>>>> 7edc965b6fe8bd6df472f9f546f1113e51f3d1de:app/src/main/java/com/example/sts_admin/apiservice/Client.java

    private Client(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized Client getInstance(String baseUrl) {
        return new Client(baseUrl);
    }

    public Api getRoute() {
        return retrofit.create(Api.class);
    }
}
