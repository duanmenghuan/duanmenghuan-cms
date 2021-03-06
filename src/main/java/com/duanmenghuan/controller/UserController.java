package com.duanmenghuan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duanmenghuan.bean.Article;
import com.duanmenghuan.bean.User;
import com.duanmenghuan.comon.ConstClass;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.UserService;
import com.duanmenghuan.web.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;


    //只能接受GET请求
    @GetMapping("register")
    public String register() {
        return "user/register";
    }

    @RequestMapping("index")
    public String index() {
        return "user/index";
    }

    /**
     * 判断用户名是否已经被占用
     *
     * @param username
     * @return
     */
    @RequestMapping("checkExist")
    @ResponseBody
    public boolean checkExist(String username) {
        return !userService.checkUserExist(username);
    }

    //只能接受POST请求
    @PostMapping("register")
    public String register(HttpServletRequest request,
                           @Validated User user,
                           BindingResult errorResult) {
        if (errorResult.hasErrors()) {
            return "user/register";
        }

        int result = userService.register(user);
        if (result > 0) {
            return "redirect:login";
        } else {
            request.setAttribute("errorMsg", "系统错误，请稍后重试");
            return "user/register";
        }


    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
        return "user/login";
    }

    @PostMapping("login")
    public String login(HttpServletRequest request,
                        @Validated User user,
                        BindingResult errorResult) {

        if (errorResult.hasErrors()) {
            return "login";
        }

        //登录
        User loginUser = userService.login(user);
        System.out.println("loginUser is " + loginUser);
        if (loginUser == null) {
            request.setAttribute("errorMsg", "用户名密码错误");
            return "user/login";
        } else {
            request.getSession().setAttribute(ConstClass.SESSION_USER_KEY, loginUser);
            if (loginUser.getRole() == ConstClass.USER_ROLE_GENERAL) {
                return "redirect:../index";

            } else if (loginUser.getRole() == ConstClass.USER_ROLE_ADMIN) {
                return "redirect:../admin/index";
            } else {
                // 其他情况
                return "user/login";
            }
        }

    }

    /**
     * 进入个人主业
     *
     * @return
     */
    @RequestMapping("home")
    public String home(HttpServletRequest request) {
        return "my/home";

    }

    /**
     * 获取个人文章
     *
     * @return
     */
    @RequestMapping("myarticlelist")
    public String myarticle(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {
        User user = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
        PageInfo<Article> pageInfo = articleService.myarticle(user.getId(), page);

        PageUtils.page(request, "/user/myarticlelist", 5, pageInfo.getList(), (long) pageInfo.getSize(), pageInfo.getPageNum());
        request.setAttribute("pageArticles", pageInfo);
        return "/my/list";
    }

    /**
     * 删除用户自己的文章
     *
     * @param id
     * @return
     */
    @RequestMapping("delArticle")
    @ResponseBody
    public boolean delArticle(Integer id) {
        return articleService.remove(id) > 0;
    }

    /**
     * 进入上传头像页面
     */
    @GetMapping("portrait")
    public String portrait(){
        return "/my/portrait";
    }

    /**
     * 上传头像动作
     * @param request
     * @param file
     * @return
     */
    @PostMapping("portrait")
    public  String portrait(HttpServletRequest request, MultipartFile file) throws IOException {
        User user = (User) request.getSession().getAttribute("SESSION_USER_KEY");
        processFile(file,user);
        int i = userService.addportrait(user);
        return "redirect:home";
    }


    /**
     * 处理接收到的文件
     */

    private void processFile(MultipartFile file,User user) throws IllegalStateException, IOException {


        // 原来的文件名称
        System.out.println("file.isEmpty() :" + file.isEmpty()  );
        System.out.println("file.name :" + file.getOriginalFilename());

        if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
            user.setPicture("");
            return;
        }

        String originName = file.getOriginalFilename();
        String suffixName = originName.substring(originName.lastIndexOf('.'));
        SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
        String path = "d:/pic/" + sdf.format(new Date());
        File pathFile = new File(path);
        if(!pathFile.exists()) {
            pathFile.mkdir();
        }
        String destFileName = path + "/" +  UUID.randomUUID().toString() + suffixName;
        File distFile = new File( destFileName);
        file.transferTo(distFile);//文件另存到这个目录下边
        user.setPicture(destFileName.substring(7));

    }
}
