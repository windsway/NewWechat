package com.demo.wechat.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.wechat.R;
import com.demo.wechat.adapter.MomentsAdapter;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.mvp.presenter.MomentsPresenter;
import com.demo.wechat.mvp.presenter.MomentsPresenterImpl;
import com.demo.wechat.mvp.view.MomentView;
import com.demo.wechat.util.GlideUtil;

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
    private int sizeList = 0;
    private List<Tweet> tweetListAll= new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initRec();
    }

    @Override
    protected void initData() {

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
        if(list.size()==0){
            momentsAdapter.setEnableLoadMore(false);
            return;
        }
        tweetListAll.addAll(list);
        sizeList = tweetListAll.size();
        momentsAdapter.setNewData(tweetListAll);
    }

    @Override
    public void showUserInfo(User user) {
        GlideUtil.loadSquareImage(MomentsActivity.this, user.getProfileImage(), mIvUserBg);
        GlideUtil.loadGrayScaleImage(MomentsActivity.this, user.getAvatar(), mIvUserAvater, 10);
        mTvNick.setText(user.getUsername());
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void initRec() {
        mRvContent.setLayoutManager(new LinearLayoutManager(MomentsActivity.this, LinearLayoutManager.VERTICAL, false));
        momentsAdapter = new MomentsAdapter(MomentsActivity.this, R.layout.itme_content_img, new ArrayList<>());
        mRvContent.setAdapter(momentsAdapter);
        mRefreshLayout.setOnRefreshListener(() -> mRefreshLayout.setEnabled(false));

        View headView = getLayoutInflater().inflate(R.layout.head_assignment, null);
        initHeadView(headView);
        //添加头布局尾布局
        momentsAdapter.addHeaderView(headView);
        momentsAdapter.setEnableLoadMore(true);
        momentsAdapter.setOnLoadMoreListener(() -> mPresenter.loadMoreData(sizeList, sizeList + 5), mRvContent);

    }

    private void initHeadView(View view) {
        mIvUserBg = view.findViewById(R.id.iv_user_bg);
        mIvUserAvater = view.findViewById(R.id.iv_avater);
        mTvNick = view.findViewById(R.id.tv_nick);
    }



}
