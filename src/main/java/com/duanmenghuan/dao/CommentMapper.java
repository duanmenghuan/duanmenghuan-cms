package com.duanmenghuan.dao;

import com.duanmenghuan.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    @Insert("INSERT into cms_comment set articleId=#{articleId},userId=#{userId},content=#{content},created=now()")
    int pinglun(Comment comment);


    @Select(" SELECT a.*,b.`username` FROM cms_comment  a  LEFT JOIN cms_user  b ON a.`userId`=b.`id`  WHERE articleId=#{articleId} ORDER BY id DESC")
    List<Comment> listss(@Param("articleId")Integer id);
}
