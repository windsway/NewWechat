package com.demo.wechat.mvp.view.userinfoview;


import com.demo.wechat.bean.User;

public interface UserInfoView {

    void showUserInfo(User user);

    void showErrorMsg(String msg);
}
