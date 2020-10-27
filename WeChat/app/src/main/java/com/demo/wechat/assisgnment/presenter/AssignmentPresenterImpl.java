package com.demo.wechat.assisgnment.presenter;


import com.demo.wechat.assisgnment.bean.Tweets;
import com.demo.wechat.assisgnment.listener.OnAssisgnmentFinishedListener;
import com.demo.wechat.assisgnment.model.AssignmentModeImpl;
import com.demo.wechat.assisgnment.model.AssisgnmentModel;
import com.demo.wechat.assisgnment.view.AssignmentView;

import java.util.List;

public class AssignmentPresenterImpl implements AssignmentPresenter, OnAssisgnmentFinishedListener {
    private AssignmentView assignmentView;
    private AssisgnmentModel assisgnmentModel;

    public AssignmentPresenterImpl(AssignmentView assignmentView) {
        this.assignmentView = assignmentView;
        this.assisgnmentModel = new AssignmentModeImpl();
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
