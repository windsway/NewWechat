package com.demo.wechat.assisgnment.model;

import com.demo.wechat.assisgnment.listener.OnAssisgnmentFinishedListener;

public interface AssisgnmentModel {
    void getUserInfo(OnAssisgnmentFinishedListener listener);

    void getTweets(int startNum, int endNum,OnAssisgnmentFinishedListener listener);

//    void onLoadMoreImpl();
//
//    void onRefershImpl();
}
