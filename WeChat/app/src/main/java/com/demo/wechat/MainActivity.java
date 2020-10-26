package com.demo.wechat;


import android.os.Build;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.wechat.adapter.AssisgnmentListAdapter;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.bean.Tweets;
import com.demo.wechat.netsubscribe.JsmithSubscribe;
import com.demo.wechat.util.GlideUtil;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_user_bg)
    ImageView mIvUserBg;
    @BindView(R.id.iv_avater)
    ImageView mIvAvater;
    @BindView(R.id.tv_nick)
    TextView mTvNick;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    private AssisgnmentListAdapter assisgnmentListAdapter;
    private List<Tweets> tweetsList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        initRec();
        showUserInfo();
        getSweets();
    }

    private void showUserInfo() {
        // 显示用户背景图像
        String urlBg = "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png";
        GlideUtil.loadsAquareImage(MainActivity.this, urlBg, mIvUserBg);

        // 显示用户头像
        String avater = "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png";
        GlideUtil.loadGrayscaleImage(MainActivity.this, avater, mIvAvater, 10);

        // 显示用户昵称
        mTvNick.setText("John Smith");

    }


    private void getSweets() {
        JsmithSubscribe.Tweets(new DisposableObserver<List<Tweets>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(List<Tweets> tweet) {

                Optional<Tweets> tweetsOptional = tweet.stream()
                        .filter(item -> item.getError() == null)
                        .findAny();

                if (tweetsOptional.isPresent()) {
                    Tweets cart = tweetsOptional.get();
                    tweetsList.add(cart);
                }
                assisgnmentListAdapter.setList(tweetsList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void initRec() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvContent.setLayoutManager(layoutManager);
        assisgnmentListAdapter = new AssisgnmentListAdapter(R.layout.itme_content_img, new ArrayList<>());
        mRvContent.setAdapter(assisgnmentListAdapter);
    }


}
