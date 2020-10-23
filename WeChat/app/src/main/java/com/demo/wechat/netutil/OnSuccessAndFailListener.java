package com.demo.wechat.netutil;


public interface OnSuccessAndFailListener {
    void onSuccess(String result);
    void onFault(String errorMsg);
}
