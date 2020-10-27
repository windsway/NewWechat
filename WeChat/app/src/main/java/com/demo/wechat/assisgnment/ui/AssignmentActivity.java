package com.demo.wechat.assisgnment.ui;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.wechat.MainActivity;
import com.demo.wechat.R;
import com.demo.wechat.assisgnment.bean.Tweets;
import com.demo.wechat.assisgnment.presenter.AssignmentPresenter;
import com.demo.wechat.assisgnment.presenter.AssignmentPresenterImpl;
import com.demo.wechat.assisgnment.view.AssignmentView;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.assisgnment.adapter.AssisgnmentListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AssignmentActivity extends BaseActivity implements AssignmentView {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefershLayout;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    private AssignmentPresenter assignmentPresenter;
    private AssisgnmentListAdapter assisgnmentListAdapter;
    private int sizeList = 0;

    @Override
    protected int getLayoutId() {
        assignmentPresenter = new AssignmentPresenterImpl(this);
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initRec();
        assignmentPresenter.getTweetsList(sizeList, sizeList + 5);
    }


    @Override
    public void showErrorMsg(String msg) {

        $toastLong(msg);
    }

    @Override
    public void showTweetsList(List<Tweets> list) {
        sizeList = list.size();
        assisgnmentListAdapter.setNewData(list);
    }

    @Override
    protected void onDestroy() {
        assignmentPresenter.onDestroy();
        super.onDestroy();
    }

    private void initRec() {
        mRvContent.setLayoutManager(new LinearLayoutManager(AssignmentActivity.this, LinearLayoutManager.VERTICAL, false));
        assisgnmentListAdapter = new AssisgnmentListAdapter(AssignmentActivity.this, R.layout.itme_content_img, new ArrayList<>());
        mRvContent.setAdapter(assisgnmentListAdapter);
        View headView = getLayoutInflater().inflate(R.layout.head_assignment, null);
        //添加头布局尾布局
        assisgnmentListAdapter.addHeaderView(headView);
        assisgnmentListAdapter.setEnableLoadMore(true);
        assisgnmentListAdapter.setOnLoadMoreListener(() -> assignmentPresenter.getTweetsList(sizeList, sizeList + 5), mRvContent);

    }

}
