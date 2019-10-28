package com.duanmenghuan.controller;

import com.bw.utility.StringUtility;
import com.duanmenghuan.bean.ResultMsg;
import com.duanmenghuan.bean.Special;
import com.duanmenghuan.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("special")
public class SpecialController {

    @Autowired
    SpecialService specialService;


    /**
     * 获取专题管理列表
     * @param request
     * @return
     */
    @RequestMapping("list")
    public String list(HttpServletRequest request){
        List<Special> specialList = specialService.list();
        request.setAttribute("specialList",specialList);
        return "admin/special/list";
    }

    /**
     * 跳转到添加标题页面
     * @param request
     * @return
     */
    @GetMapping("add")
    public String add(HttpServletRequest request){

        return "admin/special/add";
    }

    /**
     * 添加标题
     * @param request
     * @param special
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public ResultMsg add(Special special){
        if (!StringUtility.isvalue(special.getDigest())){
            return  new ResultMsg(3,"不能为空","");
        }
        int result = specialService.add(special);
        if(result>0){
            return new ResultMsg(1,"添加成功","");
        }else{

            return  new ResultMsg(2,"添加失败","");
        }
    }



    @RequestMapping("addArticle1")
    @ResponseBody
    public ResultMsg addArticle(HttpServletRequest request,Integer specId,Integer articleId){
        int result = specialService.addArticle(specId,articleId);
        if(result>0){
            return  new ResultMsg(1,"添加成功","");
        }else {

            return  new ResultMsg(2,"添加失败请与段孟欢交流","");
        }
    }


    /**
     * 进入添加页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public String detail(HttpServletRequest request,Integer id ) {
        Special special =  specialService.findById(id);
        request.setAttribute("special", special);
        return "admin/special/detail";
    }

    /**
     * 删除文章
     * @param request
     * @param specId
     * @param articleId
     * @return
     */
    @PostMapping("removeArticle")
    @ResponseBody
    public  ResultMsg removeArticle(HttpServletRequest request,Integer specId,Integer articleId){
       int  result =  specialService.removeArticle(specId,articleId);
       if(result>0){
           return new ResultMsg(1,"移除成功","");
       }else {
           return new ResultMsg(2,"移除失败","");
       }

       }

    /**
     * 进入修改标题文章页面
     * @param request
     * @param id
     * @return
     */
    @GetMapping("update")
       public   String update(HttpServletRequest request,Integer id){
        Special special = specialService.findById(id);
           request.setAttribute("special", special);
        return "admin/special/update";
     }


     @PostMapping("update")
     @ResponseBody
     public ResultMsg update(HttpServletRequest request,Special special){
         int result = specialService.update(special);
         if(result>0) {
             return new ResultMsg(1, "修改成功", "");
         }else {
             return new ResultMsg(2, "修改失败，请与管理员联系", "");
         }
     }










}
