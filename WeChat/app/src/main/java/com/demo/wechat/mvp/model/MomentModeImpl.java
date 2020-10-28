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

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MomentModeImpl implements MomentsModel {

    private List<Tweet> list = new ArrayList<>();

    @Override
    public void getTweets(CallBackListener listener) {
        list.clear();
        MomentsObservable.Tweets(new DisposableObserverImp<List<Tweet>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(List<Tweet> tweet) {
                // 将返回的null数据筛选出去
                List<Tweet> tweetsList = tweet.stream()
                        .filter(item -> isInvalid(item))
                        .collect(Collectors.toList());
                list.addAll(tweetsList);
                if (listener != null) {
                    listener.onSuccess(getTweetsWithPage(0));
                }
            }
        });
    }

    private boolean isInvalid(Tweet item) {
        return item != null && (item.getContent() != null || item.getImages() != null);
    }

    @Override
    public void getUserInfo(CallBackListener listener) {
        MomentsObservable.userInfo(new DisposableObserverImp<User>() {
            @Override
            public void onNext(User user) {
                if (listener != null) {
                    listener.onSuccess(user);
                }
            }
        });
    }

    @Override
    public void loadMoreData(int startPage, int offset, CallBackListener listener) {
        Observable.create((ObservableOnSubscribe<List<Tweet>>) emitter -> {
            Thread.sleep(2000);
            emitter.onNext(getTweetsWithPage(startPage));
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tweets -> listener.onSuccess(tweets));
    }

    private List<Tweet> getTweetsWithPage(int startPage) {
        if (list == null || list.size() == 0)
            return new ArrayList<>();

        int startIndex = startPage * 5;
        int endIndex = startIndex + 5;

        if (startIndex > list.size()) {
            return new ArrayList<>();
        } else if (endIndex > list.size()) {
            return list.subList(startIndex, list.size());
        } else {
            return list.subList(startIndex, endIndex);
        }
    }
}
