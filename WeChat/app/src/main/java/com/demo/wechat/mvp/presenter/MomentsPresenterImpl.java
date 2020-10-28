package com.demo.wechat.mvp.presenter;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.mvp.model.MomentModeImpl;
import com.demo.wechat.mvp.model.MomentsModel;
import com.demo.wechat.mvp.view.MomentView;

import java.util.List;

public class MomentsPresenterImpl implements MomentsPresenter {
    private MomentView momentView;
    private MomentsModel momentsModel;
    private int page = 0;

    public MomentsPresenterImpl(MomentView momentView) {
        this.momentView = momentView;
        this.momentsModel = new MomentModeImpl();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void getUserInfo() {
        momentsModel.getUserInfo(new CallBackListener<User>() {
            @Override
            public void onSuccess(User user) {
                if (momentView != null) {
                    momentView.showUserInfo(user);
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (momentView != null) {
                    momentView.showErrorMsg(errorMsg);
                }
            }
        });
    }


    @Override
    public void getTweetsList() {
        page =0;
        momentsModel.getTweets(new CallBackListener<List<Tweet>>() {
            @Override
            public void onError(String errorMsg) {
                if (momentView != null) {
                    momentView.showErrorMsg(errorMsg);
                }
            }

            @Override
            public void onSuccess(List<Tweet> tweets) {
                if (momentView != null) {
                    momentView.showTweetsList(tweets);
                }
            }
        });
    }

    @Override
    public void loadMoreData() {
            momentsModel.loadMoreData(++page, 5, new CallBackListener<List<Tweet>>() {
                @Override
                public void onSuccess(List<Tweet> tweets) {
                    momentView.showLoadMore(tweets);
                }

                @Override
                public void onError(String errorMsg) {
                    momentView.showErrorMsg(errorMsg);
                }
            });
    }
}
