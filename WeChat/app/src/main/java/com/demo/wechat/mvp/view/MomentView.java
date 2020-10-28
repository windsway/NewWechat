package com.demo.wechat.mvp.view;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;

import java.util.List;

public interface MomentView {

    void showErrorMsg(String msg);

    void showTweetsList(List<Tweet> list);

    void showUserInfo(User user);

    void finishLoadMore();

    void finishRefresh();
}
