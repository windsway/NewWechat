package com.demo.wechat.mvp.presenter;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.mvp.model.MomentModeImpl;
import com.demo.wechat.mvp.model.MomentsModel;
import com.demo.wechat.mvp.view.MomentView;

import java.util.List;

public class MomentsPresenterImpl implements MomentsPresenter, CallBackListener<List<Tweet>> {
    private MomentView momentView;
    private MomentsModel momentsModel;

    public MomentsPresenterImpl(MomentView momentView) {
        this.momentView = momentView;
        this.momentsModel = new MomentModeImpl();
    }

    @Override
    public void onSuccess(List<Tweet> tweetsOldList) {

        momentView.showTweetsList(tweetsOldList);
    }

    @Override
    public void onError(String errorMsg) {

        // 错误时，提示
        if (momentView != null) {
            momentView.showErrorMsg(errorMsg);
        }
    }

    @Override
    public void onFinishLoadMore() {
        momentView.finishLoadMore();
    }

    @Override
    public void onFinishRefresh() {
        momentView.finishRefresh();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void getTweetsList(int startNum, int endNum) {
        momentsModel.getTweets(startNum, endNum, this);
    }

}
