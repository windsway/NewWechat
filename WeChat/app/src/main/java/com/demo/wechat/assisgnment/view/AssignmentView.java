package com.demo.wechat.assisgnment.view;

//import com.demo.wechat.assisgnment.adapter.AssisgnmentListAdapter;

import com.demo.wechat.assisgnment.bean.Tweets;

import java.util.List;

public interface AssignmentView {


//    void showProgress();
//
//    void hideProgress();

    void showErrorMsg(String msg);

    void showTweetsList(List<Tweets> list);
}
