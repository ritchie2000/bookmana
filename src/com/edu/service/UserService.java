package com.edu.service;

import com.edu.entity.User;

public interface UserService {
	
	/**
	 * 通过邮箱和密码查询用户信息
	 * @param email
	 * @param password
	 * @return
	 */
	User login(String email, String password);
}
