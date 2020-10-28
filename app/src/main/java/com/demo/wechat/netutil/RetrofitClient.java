package com.demo.wechat.netutil;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://thoughtworks-ios.herokuapp.com/";
    public static String url = BASE_URL;
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 30;
    private Retrofit retrofit;

    private RetrofitClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        // 设置头信息
        Interceptor headInterceptor = chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Accpet", "application/json")
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .method(request.method(), request.body());
            Request r = requestBuilder.build();
            return chain.proceed(r);
        };
        okHttpBuilder.addInterceptor(headInterceptor);
        // 设置超时和重新连接
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public ServiceApi getHttpApi() {
        return retrofit.create(ServiceApi.class);
    }

    /**
     * 双重检查锁定（Double CheckLock）实现单例
     * 特点：延迟加载，解决了多余的同步，线程安全。
     * 两次判空，第一层是为了避免不必要的同步。 第二层是为了在instance为null的情况下创建实例。
     */
    private static RetrofitClient instance = null;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }
}
