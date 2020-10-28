package com.demo.wechat.netobservable;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.netutil.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WeChatMomentsObservable {

    public static void Tweets(DisposableObserver<List<Tweet>> subscriber) {

        Observable<List<Tweet>> observable = RetrofitClient.getInstance().getHttpApi().tweets();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void jsmith(DisposableObserver<User> subscriber){
        Observable<User> observable = RetrofitClient.getInstance().getHttpApi().jsmith();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
