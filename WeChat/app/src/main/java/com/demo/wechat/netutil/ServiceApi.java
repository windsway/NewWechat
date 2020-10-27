package com.demo.wechat.netutil;



import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceApi {

    @GET("user/jsmith")
    Observable<User> jsmith();

    @GET("user/jsmith/tweets")
    Observable<List<Tweet>> tweets();
}
