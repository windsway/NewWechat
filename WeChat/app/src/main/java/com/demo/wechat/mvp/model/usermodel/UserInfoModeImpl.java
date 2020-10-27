package com.demo.wechat.mvp.model.usermodel;

import com.demo.wechat.bean.User;
import com.demo.wechat.mvp.listener.userinfolistener.OnUserInfoFinishedListener;
import com.demo.wechat.netobservable.WechatMomentsObservable;

import io.reactivex.observers.DisposableObserver;

public class UserInfoModeImpl implements UserInfoModel {

    @Override
    public void getUserInfo(OnUserInfoFinishedListener listener) {
        getUserInfoBean(listener);
    }

    public void getUserInfoBean(OnUserInfoFinishedListener listener) {
        WechatMomentsObservable.jsmith(new DisposableObserver<User>() {
            @Override
            public void onNext(User user) {

                listener.onSuccess(user);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
