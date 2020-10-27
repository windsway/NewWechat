package com.demo.wechat.mvp.model.usermodel;


import com.demo.wechat.mvp.listener.userinfolistener.OnUserInfoFinishedListener;

public interface UserInfoModel {

    void getUserInfo(OnUserInfoFinishedListener listener);

}
