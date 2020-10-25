package com.demo.wechat.netsubscribe;


import com.demo.wechat.netutil.HttpMethods;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;


public class JsmithSubscribe {

    public static void Jsmith(DisposableObserver<ResponseBody> subscriber) {

        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpApi().jsmith();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void Tweets(DisposableObserver<ResponseBody> subscriber) {

        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpApi().tweets();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


}
