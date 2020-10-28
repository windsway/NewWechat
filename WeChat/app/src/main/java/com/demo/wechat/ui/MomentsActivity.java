package com.demo.wechat.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.demo.wechat.R;
import com.demo.wechat.adapter.MomentsAdapter;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.mvp.presenter.MomentsPresenter;
import com.demo.wechat.mvp.presenter.MomentsPresenterImpl;
import com.demo.wechat.mvp.view.MomentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MomentsActivity extends BaseActivity<MomentsPresenter> implements MomentView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    private ImageView mIvUserBg;
    private ImageView mIvUserAvater;
    private TextView mTvNick;

    private MomentsAdapter momentsAdapter;
    private List<Tweet> tweetListAll = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initWidget();
    }

    @Override
    protected void initData() {
        refreshData();
    }

    private void refreshData() {
        mPresenter.getUserInfo();
        mPresenter.getTweetsList();
    }

    @Override
    protected MomentsPresenter getPresenter() {
        return new MomentsPresenterImpl(this);
    }

    @Override
    public void showErrorMsg(String msg) {
        $toastLong(msg);
    }

    @Override
    public void showTweetsList(List<Tweet> list) {
        mRefreshLayout.setRefreshing(false);
        if (list.size() == 0) {
            momentsAdapter.setEnableLoadMore(false);
            return;
        }
        tweetListAll.addAll(list);
        momentsAdapter.setNewData(tweetListAll);
    }

    @Override
    public void showUserInfo(User user) {
        Glide.with(this).load(user.getProfileImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(mIvUserBg);
        Glide.with(this).load(user.getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(mIvUserAvater);
        mTvNick.setText(user.getNick());
    }

    @Override
    public void showLoadMore(List<Tweet> list) {
        int insertIndex = tweetListAll.size();
        if (list != null && list.size() != 0) {
            tweetListAll.addAll(list);
            momentsAdapter.loadMoreEnd();
        } else {
            momentsAdapter.loadMoreComplete();
        }
//        momentsAdapter.notifyItemRangeInserted(insertIndex,tweetListAll.size()-1);
        momentsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void initWidget() {
        mRefreshLayout.setOnRefreshListener(() -> refreshData());
        mRvContent.setLayoutManager(new LinearLayoutManager(MomentsActivity.this, LinearLayoutManager.VERTICAL, false));
        momentsAdapter = new MomentsAdapter(MomentsActivity.this, R.layout.itme_content_img, new ArrayList<>());
        // 添加头布局
        View headView = getLayoutInflater().inflate(R.layout.head_assignment, null);
        initHeadView(headView);
        momentsAdapter.addHeaderView(headView);
        momentsAdapter.setEnableLoadMore(true);
        momentsAdapter.setOnLoadMoreListener(() -> {
            momentsAdapter.openLoadAnimation();
            mPresenter.loadMoreData();
        },mRvContent);
        mRvContent.setAdapter(momentsAdapter);
    }

    private void initHeadView(View view) {
        mIvUserBg = view.findViewById(R.id.iv_user_bg);
        mIvUserAvater = view.findViewById(R.id.iv_avatar);
        mTvNick = view.findViewById(R.id.tv_nick);
    }
}
