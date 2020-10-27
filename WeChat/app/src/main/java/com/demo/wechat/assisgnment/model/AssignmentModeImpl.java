package com.demo.wechat.assisgnment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.demo.wechat.assisgnment.bean.Tweets;
import com.demo.wechat.assisgnment.listener.OnAssisgnmentFinishedListener;
import com.demo.wechat.netsubscribe.JsmithSubscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.observers.DisposableObserver;

public class AssignmentModeImpl implements AssisgnmentModel {


    private List<Tweets> tweetsNewList = new ArrayList<>();
//    private List<Tweets> tweetsOldList = new ArrayList<>();

    @Override
    public void getUserInfo(OnAssisgnmentFinishedListener listener) {

    }

    @Override
    public void getTweets(int startNum, int endNum, OnAssisgnmentFinishedListener listener) {
        getSweets(startNum, endNum, listener);
    }


    private void getSweets(int startNum, int endNum, OnAssisgnmentFinishedListener listener) {
        JsmithSubscribe.Tweets(new DisposableObserver<List<Tweets>>() {
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
//                listener.onSuccess(tweetsOldList);

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


    private void initRefreshData(OnAssisgnmentFinishedListener listener, int startNum, int endNum, List<Tweets> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (endNum < list.size()) {
            endNum = list.size();
        }
        List<Tweets> tempList = list.subList(startNum, endNum);
        tweetsNewList.addAll(tempList);
        listener.onSuccess(tweetsNewList);
    }


}
