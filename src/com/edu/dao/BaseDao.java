package com.edu.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {
	private QueryRunner queryRunner = new QueryRunner();

	/**
	 * 查数据库表的总记录数
	 * @param connection
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Connection connection, String sql, Object... params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		
		if (params != null && params.length > 0) {
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
		}
		
		ResultSet executeQuery = ps.executeQuery();
		int dbCount = 0;
		if (executeQuery.next()) {
			dbCount = executeQuery.getInt("count");
		}
		return dbCount;
	}
	/**
	 * 通用增删改方法
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(Connection connection, String sql, Object... params) {

		try {
			int update = queryRunner.update(connection, sql, params);
			return update;
		} catch (SQLException e) {
			// 这里将编译异常转换为运行异常，抛出。调用者可以捕获也可以不捕获，采用默认的处理方式
			throw new RuntimeException(e);
		}

	}

	/**
	 * 通用的查询方法，返回多行
	 * 
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public List<T> getList(Connection connection, String sql, Class<T> clz, Object... params) {

		try {
			List<T> list = queryRunner.query(connection, sql, new BeanListHandler<T>(clz), params);
			return list;
		} catch (SQLException e) {
			// 这里将编译异常转换为运行异常，抛出。调用者可以捕获也可以不捕获，采用默认的处理方式
			throw new RuntimeException(e);
		}

	}

	/**
	 * 通用的查询方法，返回单行
	 * 
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public T getOne(Connection connection, String sql, Class<T> clz, Object... params) {

		try {
			T t = queryRunner.query(connection, sql, new BeanHandler<T>(clz), params);
			return t;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 通用的查询方法，返回单行单列(即单值) * @param sql
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getScalar(Connection connection, String sql, Object... params) {

		try {
			return queryRunner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}