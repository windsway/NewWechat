package com.demo.wechat.mvp.presenter;

import com.demo.wechat.base.BasePresenter;

public interface MomentsPresenter extends BasePresenter {
    void onDestroy();

    void getTweetsList();// 此处可以传递参数

    void getUserInfo();

    void loadMoreData(int startNum, int endNum);
}