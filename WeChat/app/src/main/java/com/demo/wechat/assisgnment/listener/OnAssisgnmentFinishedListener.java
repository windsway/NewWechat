package com.demo.wechat.assisgnment.listener;

import com.demo.wechat.assisgnment.bean.Tweets;

import java.util.List;

public interface OnAssisgnmentFinishedListener {
    void onSuccess(List<Tweets> tweetsOldList);

    void onError(String errorMsg);
}
