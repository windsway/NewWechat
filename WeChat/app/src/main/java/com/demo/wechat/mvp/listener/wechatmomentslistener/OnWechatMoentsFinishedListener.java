package com.demo.wechat.mvp.listener.wechatmomentslistener;


import com.demo.wechat.bean.Tweets;

import java.util.List;

public interface OnWechatMoentsFinishedListener {
    void onSuccess(List<Tweets> tweetsOldList);

    void onError(String errorMsg);

    void onFinishLoadMore();

    void onFinishRefersh();
}
