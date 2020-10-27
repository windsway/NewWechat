package com.demo.wechat;


import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.wechat.assisgnment.adapter.AssisgnmentListAdapter;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.assisgnment.bean.Tweets;
import com.demo.wechat.netsubscribe.JsmithSubscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    //    @BindView(R.id.iv_user_bg
//    ImageView mIvUserBg;
//    @BindView(R.id.iv_avater)
//    ImageView mIvAvater;
//    @BindView(R.id.tv_nick)
//    TextView mTvNick;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefershLayout;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

//    private AssisgnmentListAdapter assisgnmentListAdapter;
    private List<Tweets> tweetsNewList = new ArrayList<>();
    private List<Tweets> tweetsOldList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
//        initRec();
////        showUserInfo();
//        getSweets();
    }

    private void showUserInfo() {
        // 显示用户背景图像
//        String urlBg = "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png";
//        GlideUtil.loadsAquareImage(MainActivity.this, urlBg, mIvUserBg);
//
//        // 显示用户头像
//        String avater = "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png";
//        GlideUtil.loadGrayscaleImage(MainActivity.this, avater, mIvAvater, 10);
//
//        // 显示用户昵称
//        mTvNick.setText("John Smith");

    }


//    private void getSweets() {
//        JsmithSubscribe.Tweets(new DisposableObserver<List<Tweets>>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onNext(List<Tweets> tweet) {
//                // 将返回的null数据筛选出去
//                tweetsOldList = tweet.stream()
////                        .filter(x -> x != null)
//                        .filter(x -> x.getError() == null)
//                        .filter(x -> x.getUnknownerror() == null)
//                        .collect(Collectors.toList());
//
//
////                initRefreshData(tweetsNewList);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }


//    private void initRec() {
//        mRvContent.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//        assisgnmentListAdapter = new AssisgnmentListAdapter(MainActivity.this, R.layout.itme_content_img, new ArrayList<>());
//        mRvContent.setAdapter(assisgnmentListAdapter);
//        View headView = getLayoutInflater().inflate(R.layout.head_assignment, null);
//        //添加头布局尾布局
//        assisgnmentListAdapter.addHeaderView(headView);
//        assisgnmentListAdapter.setEnableLoadMore(true);
//        assisgnmentListAdapter.setOnLoadMoreListener(() -> {
//            initRefreshData(tweetsNewList);
//        }, mRvContent);
//
//    }


    @Override
    public void onRefresh() {
        mRefershLayout.setEnabled(false);
    }

//    List<Tweets> tempList= new ArrayList<>();

//    private void initRefreshData(List<Tweets> list) {
//        if (list.size() < tempList.size()) {
//            tempList = tweetsOldList.subList(list.size(), list.size() + 5);
//        }else{
//            tempList = tweetsOldList.subList(list.size(), tweetsOldList.size());
//        }
//
//
//        if (tempList.size() == 0) {
//            assisgnmentListAdapter.setEnableLoadMore(false);
//            return;
//        }
//        tweetsNewList.addAll(tempList);
//        assisgnmentListAdapter.setNewData(tweetsNewList);
//    }

}
