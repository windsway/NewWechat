package com.demo.wechat.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.wechat.R;
import com.demo.wechat.adapter.WechatMomentsListAdapter;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.bean.Tweets;
import com.demo.wechat.bean.User;
import com.demo.wechat.mvp.presenter.userpresenter.UserInfoPresenter;
import com.demo.wechat.mvp.presenter.userpresenter.UserInfoPresenterImpl;
import com.demo.wechat.mvp.presenter.wechatmomentspresenter.WechatMomentsPresenter;
import com.demo.wechat.mvp.presenter.wechatmomentspresenter.WechatMomentsPresenterImpl;
import com.demo.wechat.mvp.view.userinfoview.UserInfoView;
import com.demo.wechat.mvp.view.wechatmomentsview.WechatMomentsView;
import com.demo.wechat.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WechatMomentsActivity extends BaseActivity implements WechatMomentsView, UserInfoView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefershLayout;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    private ImageView mIvUserBg;
    private ImageView mIvUserAvater;
    private TextView mTvNick;

    private WechatMomentsPresenter assignmentPresenter;
    private UserInfoPresenter userInfoPresenter;
    private WechatMomentsListAdapter wechatMomentsListAdapter;
    private int sizeList = 0;

    @Override
    protected int getLayoutId() {
        assignmentPresenter = new WechatMomentsPresenterImpl(this);
        userInfoPresenter = new UserInfoPresenterImpl(this);
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initRec();
        userInfoPresenter.getUserInfo();
        assignmentPresenter.getTweetsList(sizeList, sizeList + 5);
    }

    @Override
    public void showUserInfo(User user) {

        GlideUtil.loadsAquareImage(WechatMomentsActivity.this, user.getProfileImage(), mIvUserBg);
        GlideUtil.loadGrayscaleImage(WechatMomentsActivity.this, user.getAvatar(), mIvUserBg, 10);
        mTvNick.setText(user.getUsername());
    }

    @Override
    public void showErrorMsg(String msg) {

        $toastLong(msg);
    }

    @Override
    public void showTweetsList(List<Tweets> list) {
        sizeList = list.size();
        wechatMomentsListAdapter.setNewData(list);
    }

    @Override
    public void finishLoadMore() {
        wechatMomentsListAdapter.loadMoreEnd();
    }

    @Override
    public void finishRefersh() {

    }

    @Override
    protected void onDestroy() {
        assignmentPresenter.onDestroy();
        super.onDestroy();
    }

    private void initRec() {
        mRvContent.setLayoutManager(new LinearLayoutManager(WechatMomentsActivity.this, LinearLayoutManager.VERTICAL, false));
        wechatMomentsListAdapter = new WechatMomentsListAdapter(WechatMomentsActivity.this, R.layout.itme_content_img, new ArrayList<>());
        mRvContent.setAdapter(wechatMomentsListAdapter);
        View headView = getLayoutInflater().inflate(R.layout.head_assignment, null);
        initHeadView(headView);
        //添加头布局尾布局
        wechatMomentsListAdapter.addHeaderView(headView);
        wechatMomentsListAdapter.setEnableLoadMore(true);
        wechatMomentsListAdapter.setOnLoadMoreListener(() -> assignmentPresenter.getTweetsList(sizeList, sizeList + 5), mRvContent);

    }

    private void initHeadView(View view) {
        mIvUserBg = view.findViewById(R.id.iv_user_bg);
        mIvUserAvater = view.findViewById(R.id.iv_avater);
        mTvNick = view.findViewById(R.id.tv_nick);
    }

}
