package com.duanmenghuan.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duanmenghuan.bean.Article;
import com.duanmenghuan.bean.ResultMsg;
import com.duanmenghuan.bean.User;
import com.duanmenghuan.comon.ConstClass;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.UserService;
import com.duanmenghuan.web.PageUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @RequestMapping("index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("manArticle")
    public String adminArticle(HttpServletRequest request,
                               @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "0") Integer status) {
        PageInfo<Article> pageInfo = articleService.getAdminArticle(page);
        String pageStr = PageUtils.pageLoad(pageInfo.getPageNum(), pageInfo.getPages(), "/admin/manArticle?status=" + status, 10);
        request.setAttribute("page", pageStr);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("status", status);
        return "admin/article/list";

    }

    @RequestMapping("getArticle")
    public String getArticle(HttpServletRequest request, Integer id) {
        Article article = articleService.findById(id);
        request.setAttribute("article", article);
        return "admin/article/detail";

    }

    /**
     * @param request
     * @param articleId 文章的Id
     * @param status    状态  1通过 2不通过
     * @return
     */
    @RequestMapping("checkArticle")
    @ResponseBody
    public ResultMsg checkArticle(HttpServletRequest request, Integer id, int status) {
        User login = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        if (login == null) {
            return new ResultMsg(2, "您尚未登录", null);
        }

        if (login.getRole() != ConstClass.USER_ROLE_ADMIN) {
            return new ResultMsg(3, "权限不够", null);
        }

        Article article = articleService.findById(id);
        System.out.println(article + "////////////////////////////");
        if (article == null) {
            return new ResultMsg(4, "此文章已被移除", null);
        }

        if (article.getStatus() == status) {
            return new ResultMsg(5, "无需此操作", null);
        }

        int result = articleService.updateStatus(id, status);
        System.out.println("i is" + result);
        if (result > 0) {
            return new ResultMsg(1, "恭喜，审核成功！！", null);
        } else {
            return new ResultMsg(6, "很遗憾，操作失败，请与管理员联系或者稍后再试！！", null);
        }


    }

    @RequestMapping("sethot")
    @ResponseBody
    public ResultMsg sethot(HttpServletRequest request, Integer id, int status) {
        User login = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        if (login == null) {
            return new ResultMsg(2, "您尚未登录", null);
        }

        if (login.getRole() != ConstClass.USER_ROLE_ADMIN) {
            return new ResultMsg(3, "权限不够", null);
        }

        Article article = articleService.findById(id);
        System.out.println("///////////////////////////////" + article);
        if (article == null) {
            return new ResultMsg(4, "此文章已被移除", null);
        }

        if (article.getHot() == status) {
            return new ResultMsg(5, "无需此操作", null);
        }

        int result = articleService.updateHot(id, status);
        System.out.println("i is" + result);
        if (result > 0) {
            return new ResultMsg(1, "恭喜，审核成功！！", null);
        } else {
            return new ResultMsg(6, "很遗憾，操作失败，请与管理员联系或者稍后再试！！", null);
        }


    }

    @RequestMapping("list")
    public String louk1(HttpServletRequest request, @RequestParam(defaultValue = "1") int page
            , String name) {

        PageInfo<User> pageuser = userService.userlist(page, name);
        request.setAttribute("pageuser", pageuser);
        String pageStr = PageUtils.pageLoad(pageuser.getPageNum(), pageuser.getPages(), "/admin/list?pageuser=" + page, 10);
        request.setAttribute("pageStr", pageStr);
        return "admin/user/list";
    }


    @RequestMapping("disabled")
    @ResponseBody
    public boolean disabled(@RequestParam(defaultValue = "0") Integer locked, Integer userid) {
        int i = userService.update1(locked, userid);
        System.out.println(i);
        return i > 0;
    }


}
