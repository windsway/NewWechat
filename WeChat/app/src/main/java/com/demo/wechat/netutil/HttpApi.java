package com.demo.wechat.netutil;

import com.demo.wechat.bean.Tweets;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpApi {

    @GET("user/jsmith/tweets")
    Observable<List<Tweets>> tweets();
}
