package com.example.testing.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL = "https://rickandmortyapi.com/";

    private static RetrofitClient sInstance;
    private Retrofit retrofit;

    public static void create() {
        if (sInstance == null) {
            synchronized (RetrofitClient.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitClient();
                }
            }
        }
    }

    public static APIInterface getAPIInterface() {
        if (sInstance == null)
            create();
        return sInstance.retrofit.create(APIInterface.class);
    }

    private RetrofitClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        // httpClient.addInterceptor(new ApplicationInterceptor());
        httpClient.connectTimeout(300, TimeUnit.SECONDS);
        httpClient.writeTimeout(300, TimeUnit.SECONDS);
        httpClient.readTimeout(300, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
