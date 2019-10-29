package com.duanmenghuan.service;

import com.duanmenghuan.bean.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {

    int pinglun(Comment comment);

	PageInfo<Comment> listss(Integer id, int page);
}
