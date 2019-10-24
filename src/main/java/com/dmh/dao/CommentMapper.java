package com.dmh.dao;

import com.dmh.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface CommentMapper {

    @Insert("INSERT into cms_comment set articleId=#{articleId},userId=#{userId},content=#{content},created=now()")
    int pinglun(Comment comment);
}
