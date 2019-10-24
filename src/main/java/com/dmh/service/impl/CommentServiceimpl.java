package com.dmh.service.impl;

import com.dmh.bean.Comment;
import com.dmh.dao.CommentMapper;
import com.dmh.service.CommentService;
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




}