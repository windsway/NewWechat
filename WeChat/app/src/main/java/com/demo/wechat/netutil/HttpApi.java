package com.demo.wechat.netutil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {
    @POST("login")
    Observable<ResponseBody> appUserLogin(@Body String s);
}
