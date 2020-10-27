package com.demo.wechat.mvp.presenter.wechatmomentspresenter;

public interface WechatMomentsPresenter {
    void onDestroy();

    void getTweetsList(int startNum, int endNum);// 此处可以传递参数

    void getUserInfo();
}