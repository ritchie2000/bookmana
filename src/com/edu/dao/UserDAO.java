package com.edu.dao;

import java.sql.Connection;

import com.edu.entity.User;

public interface UserDAO {
	
	/**
	 * 查询单个用户
	 * @param connection
	 * @param sql
	 * @param clz
	 * @param args
	 * @return
	 */
	User getUser(Connection connection, String sql, Class<User> clz, Object... args);
}
