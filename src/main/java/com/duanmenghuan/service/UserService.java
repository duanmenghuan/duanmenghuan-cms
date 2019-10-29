package com.duanmenghuan.service;

import com.duanmenghuan.bean.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    int register(User user);

    User login(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    boolean checkUserExist(String username);

    PageInfo<User> userlist(int page, String name);

    int update1(int locked, int userid);


    int addportrait(User user);

}
