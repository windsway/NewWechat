package com.demo.wechat.mvp.presenter;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.mvp.model.MomentModeImpl;
import com.demo.wechat.mvp.model.MomentsModel;
import com.demo.wechat.mvp.view.MomentView;

import java.util.List;

public class MomentsPresenterImpl implements MomentsPresenter{
    private MomentView momentView;
    private MomentsModel momentsModel;

    public MomentsPresenterImpl(MomentView momentView) {
        this.momentView = momentView;
        this.momentsModel = new MomentModeImpl();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void getTweetsList() {
        momentsModel.getTweets(new CallBackListener() {
            @Override
            public void onSuccess(Object o) {
                momentView.showTweetsList((List<Tweet>) o);
            }

            @Override
            public void onError(String errorMsg) {
                momentView.showErrorMsg(errorMsg);
            }
        });
    }

    @Override
    public void getUserInfo() {
        momentsModel.getUserInfo(new CallBackListener() {
            @Override
            public void onSuccess(Object o) {
                momentView.showUserInfo((User) o);
            }

            @Override
            public void onError(String errorMsg) {
                momentView.showErrorMsg(errorMsg);
            }
        });
    }

    @Override
    public void loadMoreData(int startNum, int endNum) {
        momentsModel.loadMoreData(startNum, endNum, new CallBackListener() {
            @Override
            public void onSuccess(Object o) {
                momentView.showTweetsList((List<Tweet>) o);
            }

            @Override
            public void onError(String errorMsg) {
                momentView.showErrorMsg(errorMsg);
            }
        });
    }

}
