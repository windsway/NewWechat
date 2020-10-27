package com.demo.wechat.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.wechat.R;
import com.demo.wechat.bean.Comment;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder>  {

    public CommentAdapter(int layoutResId, @Nullable List<Comment> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert( BaseViewHolder baseViewHolder, Comment comment) {

        baseViewHolder.setText(R.id.tv_comment_name, comment.getSender().getNick()+"：");
        baseViewHolder.setText(R.id.tv_comment_content, comment.getContent());

    }
}
