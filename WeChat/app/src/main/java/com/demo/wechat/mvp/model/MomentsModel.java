package com.demo.wechat.mvp.model;


import com.demo.wechat.listener.CallBackListener;

public interface MomentsModel {

    void getTweets(int startNum, int endNum, CallBackListener listener);

}
