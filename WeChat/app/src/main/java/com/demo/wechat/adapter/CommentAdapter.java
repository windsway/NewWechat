package com.demo.wechat.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.demo.wechat.R;
import com.demo.wechat.bean.Tweets;
import com.demo.wechat.util.GlideUtil;
import com.demo.wechat.weight.AssignmentLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<Tweets.CommentsBean, BaseViewHolder>  {


    public CommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    public CommentAdapter(int layoutResId, @Nullable List<Tweets.CommentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Tweets.CommentsBean commentsBean) {

        baseViewHolder.setText(R.id.tv_comment_name, commentsBean.getSender().getNick()+"：");
        baseViewHolder.setText(R.id.tv_comment_content, commentsBean.getContent());

    }



}
