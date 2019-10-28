package com.duanmenghuan.controller;

import com.duanmenghuan.bean.Blogroll;
import com.duanmenghuan.bean.ResultMsg;
import com.duanmenghuan.service.BolgrollService;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("blogroll")
public class BlogrollController {

    @Autowired
    BolgrollService bolgrollService;

    /***
     * 友情链接集合
     * @return
     */
    @GetMapping("friendship")
    public  String friendship(HttpServletRequest request){
        List<Blogroll> listblogroll = bolgrollService.list();
        request.setAttribute("listblogroll",listblogroll);
        return "blogro/list";
    }

    /**
     * 进入添加页面
     * @return
     */
    @GetMapping("add")
    public String jia(){

       return "blogro/add";
    }

    /**
     * 添加链接
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public  ResultMsg jia(Blogroll blogroll){
        int i = bolgrollService.add(blogroll);
        if (i>0){
            return  new ResultMsg(1,"成功","");
        }else{
            return  new ResultMsg(2,"失败","");
        }

    }







}
