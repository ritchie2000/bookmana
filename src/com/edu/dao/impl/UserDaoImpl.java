package com.edu.dao.impl;

import java.sql.Connection;

import com.edu.dao.BaseDao;
import com.edu.dao.UserDAO;
import com.edu.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDAO{

	@Override
	public User getUser(Connection connection, String sql, Class<User> clz, Object... args) {
		return getOne(connection, sql, clz, args);
	}

}
