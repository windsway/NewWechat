package com.demo.wechat.netutil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HttpApi {
    @GET("user/jsmith")
    Observable<ResponseBody> jsmith();

    @GET("user/jsmith/tweets")
    Observable<ResponseBody> tweets();
}
