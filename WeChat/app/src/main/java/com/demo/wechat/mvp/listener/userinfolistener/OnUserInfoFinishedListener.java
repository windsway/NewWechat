package com.demo.wechat.mvp.listener.userinfolistener;


import com.demo.wechat.bean.User;

public interface OnUserInfoFinishedListener {
    void onSuccess(User user);

    void onError(String errorMsg);

}
