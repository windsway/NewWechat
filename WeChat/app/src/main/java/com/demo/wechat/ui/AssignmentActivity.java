package com.demo.wechat.ui;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.wechat.R;
import com.demo.wechat.base.BaseActivity;
import com.demo.wechat.netsubscribe.JsmithSubscribe;
import com.demo.wechat.netutil.OnSuccessAndFailListener;
import com.demo.wechat.netutil.OnSuccessAndFailSub;
import com.demo.wechat.util.GlideUtil;

import butterknife.BindView;

public class AssignmentActivity extends BaseActivity {
    @BindView(R.id.iv_user_bg)
    ImageView mIvUserBg;
    @BindView(R.id.iv_avater)
    ImageView mIvAvater;
    @BindView(R.id.tv_nick)
    TextView mTvNick;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;


    @Override
    protected int getLayoutId() {
        return R.layout.ac_assisgnment;
    }

    @Override
    protected void initView() {

        showUserInfo();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getSweets();
    }


    private void showUserInfo() {
        // 显示用户背景图像
        String urlBg="http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png";
        GlideUtil.loadsAquareImage(AssignmentActivity.this, urlBg, mIvUserBg);

        // 显示用户头像
        String avater ="http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png";
        GlideUtil.loadGrayscaleImage(AssignmentActivity.this, avater, mIvAvater, 10);

        // 显示用户昵称
        mTvNick.setText("John Smith");

    }

    private void getSweets() {
        JsmithSubscribe.Tweets(new OnSuccessAndFailSub(new OnSuccessAndFailListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    //    @OnClick({R.id.iv_user_bg, R.id.iv_avater, R.id.tv_nick, R.id.rv_content})
//    public void onClick(View v) {
//        switch (v.getId()) {
////            default:
////                break;
////            case R.id.iv_user_bg:
////                break;
////            case R.id.iv_avater:
////                break;
////            case R.id.tv_nick:
////                break;
////            case R.id.rv_content:
////                break;
//        }
//    }
}
