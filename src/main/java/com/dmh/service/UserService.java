package com.dmh.service;

import com.dmh.bean.User;

public interface UserService {
	
	int register(User user) ;
	User login(User user);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);
	

}
