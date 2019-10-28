package com.duanmenghuan.controller;

import com.duanmenghuan.bean.Comment;
import com.duanmenghuan.bean.User;
import com.duanmenghuan.comon.ConstClass;
import com.duanmenghuan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("commnent")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("post")
    @ResponseBody
    public int pinglun(HttpServletRequest request, Comment comment) {
        User attribute = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        if(attribute.getId()==null){
            return 0;
        }
            comment.setUserId(attribute.getId());

            int i = commentService.pinglun(comment);

        return  i;

    }

    


}
