package com.edu.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.edu.dao.BaseDao;
import com.edu.dao.CategoryDao;
import com.edu.entity.Category;

public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao{

	@Override
	public List<Category> getCategoryList(Connection connection, String sql, Class<Category> clz, Object... args) {
		return getList(connection, sql, clz, args);
	}

	@Override
	public Category getCategory(Connection connection, String sql, Class<Category> clz, Object... args) {
		return getOne(connection, sql, clz, args);
	}
	
}
