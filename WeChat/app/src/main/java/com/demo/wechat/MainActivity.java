package com.demo.wechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.bean.JsmithBean;
import com.demo.wechat.netsubscribe.JsmithSubscribe;
import com.demo.wechat.netutil.OnSuccessAndFailListener;
import com.demo.wechat.netutil.OnSuccessAndFailSub;
import com.demo.wechat.ui.AssignmentActivity;
import com.demo.wechat.util.LoginInfoUtils;
import com.demo.wechat.util.SPUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_to_assignment)
     TextView mTvToAssignment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.tv_to_assignment})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_to_assignment:
                startActivity(new Intent(MainActivity.this, AssignmentActivity.class));
                break;

        }
    }

    @Override
    protected void initData() {
        jsmith();
    }



    private void jsmith() {
        JsmithSubscribe.Jsmith(new OnSuccessAndFailSub(new OnSuccessAndFailListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                JsmithBean jsmithBean = gson.fromJson(result, JsmithBean.class);
                saveInfo(jsmithBean);

            }

            @Override
            public void onFault(String errorMsg) {
            }
        }, MainActivity.this, true));
    }

    private void saveInfo(JsmithBean jsmithBean) {

        SPUtils.put(MainActivity.this, LoginInfoUtils.USER_AVATEAR, jsmithBean.getAvatar());
        SPUtils.put(MainActivity.this, LoginInfoUtils.USER_NICK, jsmithBean.getNick());
        SPUtils.put(MainActivity.this, LoginInfoUtils.USER_IMG, jsmithBean.getProfileimage());
        SPUtils.put(MainActivity.this, LoginInfoUtils.USER_NAME, jsmithBean.getUsername());

    }
}
