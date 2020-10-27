package com.demo.wechat.mvp.view.wechatmomentsview;


import com.demo.wechat.bean.Tweets;

import java.util.List;

public interface WechatMomentsView {

    void showErrorMsg(String msg);

    void showTweetsList(List<Tweets> list);

    void finishLoadMore();

    void finishRefersh();
}
