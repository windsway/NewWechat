package com.demo.wechat.netsubscribe;


import com.demo.wechat.assisgnment.bean.Tweets;
import com.demo.wechat.netutil.HttpMethods;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class JsmithSubscribe {

    public static void Tweets(DisposableObserver<List<Tweets>> subscriber) {

        Observable<List<Tweets>> observable = HttpMethods.getInstance().getHttpApi().tweets();
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


}
