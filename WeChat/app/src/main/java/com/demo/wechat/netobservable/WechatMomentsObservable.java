package com.demo.wechat.netobservable;

import com.demo.wechat.bean.Tweets;
import com.demo.wechat.bean.User;
import com.demo.wechat.netutil.HttpMethods;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class WechatMomentsObservable {

    public static void Tweets(DisposableObserver<List<Tweets>> subscriber) {

        Observable<List<Tweets>> observable = HttpMethods.getInstance().getHttpApi().tweets();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void jsmith(DisposableObserver<User> subscriber){
        Observable<User> observable = HttpMethods.getInstance().getHttpApi().jsmith();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

}
