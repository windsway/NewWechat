package com.demo.wechat.mvp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.bean.User;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.netobservable.DisposableObserverImp;
import com.demo.wechat.netobservable.MomentsObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MomentModeImpl implements MomentsModel {

    private List<Tweet> list = new ArrayList<>();

    @Override
    public void getTweets(CallBackListener listener) {
        getSweets(listener);
    }

    @Override
    public void getUserInfo(CallBackListener listener) {
        getUser(listener);
    }

    @Override
    public void loadMoreData(int startNum, int endNum, CallBackListener listener) {
        initRefreshData(startNum, endNum, listener);
    }


    private void getSweets(CallBackListener listener) {
        MomentsObservable.Tweets(new DisposableObserverImp<List<Tweet>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(List<Tweet> tweet) {
                // 将返回的null数据筛选出去
                List<Tweet> tweetsList = tweet.stream()
                        .filter(item -> item.getError() != null)
//                        .filter(item -> item.getUnknownerror() == null)
                        .collect(Collectors.toList());
                list = tweetsList;
                listener.onSuccess(tweetsList.subList(0, 5));
            }
        });
    }

    private void getUser(CallBackListener listener) {
        MomentsObservable.userInfo(new DisposableObserverImp<User>() {
            @Override
            public void onNext(User user) {
                listener.onSuccess(user);
            }
        });
    }

    private void initRefreshData(int startNum, int endNum, CallBackListener listener) {

        if (endNum > list.size()) {
            endNum = list.size();
        }
        List<Tweet> tempList = list.subList(startNum, endNum);
        listener.onSuccess(tempList);
    }
}
