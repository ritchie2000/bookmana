package com.edu.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.edu.dao.UserDAO;
import com.edu.dao.impl.UserDaoImpl;
import com.edu.entity.User;
import com.edu.service.UserService;
import com.edu.utils.JdbcUtils;

public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO = new UserDaoImpl();

	@Override
	public User login(String email, String password) {
		
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT user_id as userId, email, password FROM user WHERE email = ? AND password = ?";
			User user = userDAO.getUser(connection, sql, User.class, email, password);
			connection.commit();
			return user;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
		
		return null;
	}

}
