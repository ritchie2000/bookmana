package com.edu.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.edu.dao.CategoryDao;
import com.edu.dao.impl.CategoryDaoImpl;
import com.edu.entity.Category;
import com.edu.service.CategoryService;
import com.edu.utils.JdbcUtils;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDao categoryDao = new CategoryDaoImpl();
	Connection connection = null;
	
	@Override
	public List<Category> getCategories() {
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT category_id as categoryId, category_name as categoryName FROM category";
			List<Category> categoryList = categoryDao.getCategoryList(connection, sql, Category.class);
			connection.commit();
			return categoryList;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
