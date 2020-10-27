package com.demo.wechat.mvp.presenter.userpresenter;


import com.demo.wechat.bean.User;
import com.demo.wechat.mvp.listener.userinfolistener.OnUserInfoFinishedListener;
import com.demo.wechat.mvp.model.usermodel.UserInfoModeImpl;
import com.demo.wechat.mvp.model.usermodel.UserInfoModel;
import com.demo.wechat.mvp.view.userinfoview.UserInfoView;

public class UserInfoPresenterImpl implements UserInfoPresenter, OnUserInfoFinishedListener {
    private UserInfoView userInfoView;
    private UserInfoModel userInfoModel;

    public UserInfoPresenterImpl(UserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        this.userInfoModel = new UserInfoModeImpl();
    }


    @Override
    public void onSuccess(User user) {
        userInfoView.showUserInfo(user);
    }

    @Override
    public void onError(String errorMsg) {

        // 错误时，提示
        if (userInfoView != null) {
            userInfoView.showErrorMsg(errorMsg);
        }
    }


    @Override
    public void onDestroy() {
        userInfoView = null;
    }



    @Override
    public void getUserInfo() {
        userInfoModel.getUserInfo(this);
    }
}
