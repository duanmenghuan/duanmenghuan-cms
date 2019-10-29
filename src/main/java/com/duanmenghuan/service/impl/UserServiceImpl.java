package com.duanmenghuan.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.utility.Md5Utils;
import com.duanmenghuan.bean.User;
import com.duanmenghuan.dao.UserMapper;
import com.duanmenghuan.service.ArticleService;
import com.duanmenghuan.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author duanmenghuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleService articleService;


    /**
     * 注册
     */
    @Override
    public int register(User user) {
        // TODO Auto-generated method stub
        User existUser = userMapper.findByName(user.getUsername());
        if (existUser != null) {
            return -1;// 用户已经存在
        }
        //设置密码密文
        user.setPassword(
                Md5Utils.md5(user.getPassword(), user.getUsername()));

        return userMapper.add(user);

    }

    @Override
    public User login(User user) {
        // TODO Auto-generated method stub
        String pwdStr = Md5Utils.md5(user.getPassword(), user.getUsername());
        User loginUser = userMapper.findByName(user.getUsername());
        if (loginUser != null && pwdStr.equals(loginUser.getPassword())) {
            return loginUser;
        }
        return null;
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public boolean checkUserExist(String username) {
        // TODO Auto-generated method stub
        return null != userMapper.findByName(username);

    }

    /**
     * 进入个人中心获取我的文章
     *
     * @param request
     * @return
     */
    public String myArticle(HttpServletRequest request) {
        return null;
    }

    @Override
    public PageInfo<User> userlist(int page, String name) {
        // TODO Auto-generated method stub
        PageHelper.startPage(page, 10);
        return new PageInfo<User>(userMapper.userlist(name));
    }

    @Override
    public int update1(int locked, int userid) {
        // TODO Auto-generated method stub
        return userMapper.userlist1(locked, userid);
    }

    @Override
    public int addportrait(User user) {
        return userMapper.addportrait(user);
    }




}
