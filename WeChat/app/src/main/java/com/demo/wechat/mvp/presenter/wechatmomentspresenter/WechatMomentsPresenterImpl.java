package com.demo.wechat.mvp.presenter.wechatmomentspresenter;


import com.demo.wechat.bean.Tweets;
import com.demo.wechat.mvp.listener.wechatmomentslistener.OnWechatMoentsFinishedListener;
import com.demo.wechat.mvp.model.wechatomentsmodel.WechatMomentsModeImpl;
import com.demo.wechat.mvp.model.wechatomentsmodel.WechatMomentsModel;
import com.demo.wechat.mvp.view.wechatmomentsview.WechatMomentsView;

import java.util.List;

public class WechatMomentsPresenterImpl implements WechatMomentsPresenter, OnWechatMoentsFinishedListener {
    private WechatMomentsView wechatMomentsView;
    private WechatMomentsModel wechatMomentsModel;

    public WechatMomentsPresenterImpl(WechatMomentsView wechatMomentsView) {
        this.wechatMomentsView = wechatMomentsView;
        this.wechatMomentsModel = new WechatMomentsModeImpl();
    }

    @Override
    public void onSuccess(List<Tweets> tweetsOldList) {

        wechatMomentsView.showTweetsList(tweetsOldList);
    }

    @Override
    public void onError(String errorMsg) {

        // 错误时，提示
        if (wechatMomentsView != null) {
            wechatMomentsView.showErrorMsg(errorMsg);
        }
    }

    @Override
    public void onFinishLoadMore() {
        wechatMomentsView.finishLoadMore();
    }

    @Override
    public void onFinishRefersh() {
        wechatMomentsView.finishRefersh();
    }

    @Override
    public void onDestroy() {
        wechatMomentsModel = null;
    }

    @Override
    public void getTweetsList(int startNum, int endNum) {
        wechatMomentsModel.getTweets(startNum, endNum, this);
    }

}
