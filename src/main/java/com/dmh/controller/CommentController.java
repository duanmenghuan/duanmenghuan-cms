package com.dmh.controller;

import com.dmh.bean.Comment;
import com.dmh.bean.User;
import com.dmh.comon.ConstClass;
import com.dmh.service.CommentService;
import org.apache.ibatis.annotations.Insert;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.PanelUI;
import java.net.SocketTimeoutException;

@Controller
@RequestMapping("commnent")
public class CommentController {

   @Autowired
    CommentService commentService;

   @RequestMapping("post")
   @ResponseBody
   public  int pinglun(HttpServletRequest request, Comment comment){
        User attribute = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
       System.out.println(attribute);
        comment.setUserId(attribute.getId());
         int i = commentService.pinglun(comment);
       return  i;
   }



}
