package com.demo.wechat.listener;


public interface CallBackListener<T> {
    void onSuccess(T t);

    void onError(String errorMsg);
}
