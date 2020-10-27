package com.demo.wechat.mvp.model.wechatomentsmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.demo.wechat.bean.Tweets;
import com.demo.wechat.mvp.listener.wechatmomentslistener.OnWechatMoentsFinishedListener;
import com.demo.wechat.netobservable.WechatMomentsObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.observers.DisposableObserver;

public class WechatMomentsModeImpl implements WechatMomentsModel {

    private List<Tweets> tweetsNewList = new ArrayList<>();

    @Override
    public void getUserInfo(OnWechatMoentsFinishedListener listener) {

    }

    @Override
    public void getTweets(int startNum, int endNum, OnWechatMoentsFinishedListener listener) {
        getSweets(startNum, endNum, listener);
    }

    private void getSweets(int startNum, int endNum, OnWechatMoentsFinishedListener listener) {
        WechatMomentsObservable.Tweets(new DisposableObserver<List<Tweets>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(List<Tweets> tweet) {
                // 将返回的null数据筛选出去
                List<Tweets> tweetsList = tweet.stream()
//                        .filter(x -> x != null)
                        .filter(x -> x.getError() == null)
                        .filter(x -> x.getUnknownerror() == null)
                        .collect(Collectors.toList());

                initRefreshData(listener, startNum, endNum, tweetsList);

            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initRefreshData(OnWechatMoentsFinishedListener listener, int startNum, int endNum, List<Tweets> list) {

        if (endNum > list.size()) {
            endNum = list.size();
            listener.onFinishLoadMore();
        }
        List<Tweets> tempList = list.subList(startNum, endNum);
        tweetsNewList.addAll(tempList);
        listener.onSuccess(tweetsNewList);
    }

}
