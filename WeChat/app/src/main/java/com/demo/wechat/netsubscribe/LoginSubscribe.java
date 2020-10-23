package com.demo.wechat.netsubscribe;



import com.demo.wechat.netutil.HttpMethods;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;


public class LoginSubscribe {

    public static void appUserLogin(String s, DisposableObserver<ResponseBody> subscriber) {

        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpApi().appUserLogin(s);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


}
