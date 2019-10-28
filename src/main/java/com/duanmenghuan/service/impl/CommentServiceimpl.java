package com.duanmenghuan.service.impl;

import com.duanmenghuan.bean.Comment;
import com.duanmenghuan.dao.CommentMapper;
import com.duanmenghuan.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceimpl implements CommentService {


    @Autowired
    CommentMapper commentMapper;


    @Override
    public int pinglun(Comment comment) {
        return commentMapper.pinglun(comment);
    }

   

    @Override
    public PageInfo<Comment> listss(Integer id,int page) {
        PageHelper.startPage(page,20);
        return new PageInfo<Comment>(commentMapper.listss(id));
    }


}