package com.demo.wechat.mvp.model.wechatomentsmodel;


import com.demo.wechat.mvp.listener.wechatmomentslistener.OnWechatMoentsFinishedListener;

public interface WechatMomentsModel {
    void getUserInfo(OnWechatMoentsFinishedListener listener);

    void getTweets(int startNum, int endNum, OnWechatMoentsFinishedListener listener);

}
