package com.demo.wechat.mvp.presenter.wechatmomentspresenter;


import com.demo.wechat.bean.Tweets;
import com.demo.wechat.mvp.listener.wechatmomentslistener.OnWechatMoentsFinishedListener;
import com.demo.wechat.mvp.model.wechatomentsmodel.WechatMomentsModeImpl;
import com.demo.wechat.mvp.model.wechatomentsmodel.WechatMomentsModel;
import com.demo.wechat.mvp.view.wechatmomentsview.WechatMomentsView;

import java.util.List;

public class WechatMomentsPresenterImpl implements WechatMomentsPresenter, OnWechatMoentsFinishedListener {
    private WechatMomentsView assignmentView;
    private WechatMomentsModel assisgnmentModel;

    public WechatMomentsPresenterImpl(WechatMomentsView assignmentView) {
        this.assignmentView = assignmentView;
        this.assisgnmentModel = new WechatMomentsModeImpl();
    }

    @Override
    public void onSuccess(List<Tweets> tweetsOldList) {

        assignmentView.showTweetsList(tweetsOldList);
    }

    @Override
    public void onError(String errorMsg) {

        // 错误时，提示
        if (assignmentView != null) {
            assignmentView.showErrorMsg(errorMsg);
        }
    }

    @Override
    public void onFinishLoadMore() {
        assignmentView.finishLoadMore();
    }

    @Override
    public void onFinishRefersh() {
        assignmentView.finishRefersh();
    }

    @Override
    public void onDestroy() {
        assisgnmentModel = null;
    }

    @Override
    public void getTweetsList(int startNum, int endNum) {
        assisgnmentModel.getTweets(startNum, endNum, this);
    }

    @Override
    public void getUserInfo() {
        assisgnmentModel.getUserInfo(this);
    }
}
