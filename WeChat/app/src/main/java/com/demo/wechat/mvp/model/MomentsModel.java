package com.demo.wechat.mvp.model;

import com.demo.wechat.listener.CallBackListener;

public interface MomentsModel {

    void getTweets(CallBackListener listener);

    void getUserInfo(CallBackListener listener);

    void loadMoreData(int startPage, int offset, CallBackListener listener);

}
