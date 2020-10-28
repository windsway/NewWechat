package com.demo.wechat.mvp.view;

import com.demo.wechat.base.BaseView;
import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;

import java.util.List;

public interface MomentView extends BaseView {


    void showTweetsList(List<Tweet> list);

    void showUserInfo(User user);
}
