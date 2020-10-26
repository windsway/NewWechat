package com.demo.wechat.netsubscribe;


import com.demo.wechat.bean.Tweet;
import com.demo.wechat.netutil.HttpMethods;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class JsmithSubscribe {

    public static void Tweets(DisposableObserver<Tweet> subscriber) {

        Observable<Tweet> observable = HttpMethods.getInstance().getHttpApi().tweets();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


}
