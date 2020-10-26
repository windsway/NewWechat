package com.demo.wechat.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.wechat.R;
import com.demo.wechat.bean.Tweets;
import com.demo.wechat.util.GlideUtil;
import com.demo.wechat.weight.AssignmentLayout;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AssisgnmentListAdapter extends BaseQuickAdapter<Tweets, BaseViewHolder> {


    private CommentAdapter commentAdapter;
    private Context mContext;

    public AssisgnmentListAdapter(int layoutResId) {
        super(layoutResId);
    }

    public AssisgnmentListAdapter(Context context,int layoutResId, @Nullable List<Tweets> data) {

        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Tweets tweets) {


        baseViewHolder.setText(R.id.tv_sender_name, tweets.getSender().getUsername());
        baseViewHolder.setText(R.id.tv_sendr_content, tweets.getContent());
        AssignmentLayout assignmentLayout = baseViewHolder.getView(R.id.at_sender_photo);
        RecyclerView recyclerView = baseViewHolder.getView(R.id.rv_comment);

        GlideUtil.loadGrayscaleImage(mContext, tweets.getSender().getAvatar(), baseViewHolder.getView(R.id.iv_sendr_img), 10);

        List<Tweets.ImagesBean> imagesBeanList = tweets.getImages();
        if (imagesBeanList != null) {
            assignmentLayout.setImageUrls(tweets.getImages());
        }
        // 接下来展示评论数据
        initRec(recyclerView);
        if (tweets.getComments() != null) {
            if (commentAdapter == null) {
                commentAdapter = new CommentAdapter(R.layout.itme_comment, tweets.getComments());
                recyclerView.setAdapter(commentAdapter);
            } else {
                commentAdapter.setNewData(tweets.getComments());
            }
        }

    }

    private void initRec(RecyclerView rvComment) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvComment.setLayoutManager(layoutManager);

    }


}
