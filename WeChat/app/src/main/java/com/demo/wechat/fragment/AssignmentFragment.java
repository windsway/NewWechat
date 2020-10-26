package com.demo.wechat.fragment;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.wechat.R;
import com.demo.wechat.base.BaseFragment;
import com.demo.wechat.bean.Tweet;
import com.demo.wechat.netsubscribe.JsmithSubscribe;
import com.demo.wechat.util.GlideUtil;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;

public class AssignmentFragment extends BaseFragment {
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
        return R.layout.frag_assisgnment;
    }


    @Override
    protected void initData() {
        showUserInfo();
        getSweets();
    }


    private void showUserInfo() {
        // 显示用户背景图像
        String urlBg = "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png";
        GlideUtil.loadsAquareImage(getContext(), urlBg, mIvUserBg);

        // 显示用户头像
        String avater = "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png";
        GlideUtil.loadGrayscaleImage(getContext(), avater, mIvAvater, 10);

        // 显示用户昵称
        mTvNick.setText("John Smith");

    }

    private void getSweets() {
        JsmithSubscribe.Tweets(new DisposableObserver<Tweet>() {
            @Override
            public void onNext(Tweet tweet) {
                String content = tweet.getContent();
                Log.e("====",content);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("====",e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("====","");
            }
        });
    }


}
