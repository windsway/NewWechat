package com.demo.wechat.mvp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.demo.wechat.bean.Tweet;
import com.demo.wechat.listener.CallBackListener;
import com.demo.wechat.netobservable.DisposableObserverImp;
import com.demo.wechat.netobservable.WeChatMomentsObservable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MomentModeImpl implements MomentsModel {

    @Override
    public void getTweets(int startNum, int endNum, CallBackListener listener) {
        getSweets(startNum, endNum, listener);
    }

    private void getSweets(int startNum, int endNum, CallBackListener listener) {
        WeChatMomentsObservable.Tweets(new DisposableObserverImp<List<Tweet>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(List<Tweet> tweet) {
                // 将返回的null数据筛选出去
                List<Tweet> tweetsList = tweet.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                listener.onSuccess(tweetsList);
//                initRefreshData(listener, startNum, endNum, tweetsList);
            }
        });
    }

//    private void initRefreshData(CallBackListener listener, int startNum, int endNum, List<Tweets> list) {
//
//        if (endNum > list.size()) {
//            endNum = list.size();
//            listener.onFinishLoadMore();
//        }
//        List<Tweets> tempList = list.subList(startNum, endNum);
//        tweetsNewList.addAll(tempList);
//
//    }

}
