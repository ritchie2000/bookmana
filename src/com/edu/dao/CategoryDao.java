package com.edu.dao;

import java.sql.Connection;
import java.util.List;

import com.edu.entity.Category;

public interface CategoryDao {
	/**
	 * 通过分类ID查询分类信息
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	Category getCategory(Connection connection, String sql, Class<Category> clz, Object...args);
	
	/**
	 * 查询列表
	 * @param connection
	 * @param sql
	 * @param clz
	 * @param args
	 * @return
	 */
	List<Category> getCategoryList(Connection connection, String sql, Class<Category> clz, Object...args);
}
