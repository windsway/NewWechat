package com.demo.wechat.assisgnment.presenter;


public interface AssignmentPresenter {
    void onDestroy();

    void getTweetsList(int startNum, int endNum);// 此处可以传递参数

    void getUserInfo();
}