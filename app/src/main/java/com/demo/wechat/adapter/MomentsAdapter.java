package com.demo.wechat.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.wechat.R;
import com.demo.wechat.bean.Comment;
import com.demo.wechat.bean.Image;
import com.demo.wechat.bean.Tweet;
import com.demo.wechat.util.GlideUtil;
import com.demo.wechat.weight.NineGridLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MomentsAdapter extends BaseQuickAdapter<Tweet, BaseViewHolder> {

    private Context mContext;

    public MomentsAdapter(Context context, int layoutResId, @Nullable List<Tweet> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder viewHolder, Tweet item) {
        viewHolder.setText(R.id.tv_sender_name, item.getSender().getUsername());
        viewHolder.setText(R.id.tv_sendr_content, item.getContent());
        NineGridLayout momentsLayout = viewHolder.getView(R.id.at_sender_photo);

        GlideUtil.loadGrayScaleImage(mContext, item.getSender().getAvatar(), viewHolder.getView(R.id.iv_sendr_img), 10);
        List<Image> imagesBeanList = item.getImages();
        if (imagesBeanList != null) {
            momentsLayout.setVisibility(View.VISIBLE);
            momentsLayout.setImageUrls(item.getImages());
        }else{
            momentsLayout.setVisibility(View.GONE);
        }
        setCommentData(viewHolder, item.getComments());
    }

    private void setCommentData(BaseViewHolder viewHolder, List<Comment> commentList) {
        // 接下来展示评论数据
        RecyclerView recyclerView = viewHolder.getView(R.id.rv_comment);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (commentList != null) {
            recyclerView.setVisibility(View.VISIBLE);
            CommentAdapter commentAdapter = new CommentAdapter(R.layout.itme_comment, commentList);
            recyclerView.setAdapter(commentAdapter);
        }else {
            recyclerView.setVisibility(View.GONE);
        }
    }
}
