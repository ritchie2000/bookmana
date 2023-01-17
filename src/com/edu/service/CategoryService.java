package com.edu.service;

import java.util.List;

import com.edu.entity.Category;

public interface CategoryService {

	/**
	 * 获取分类列表
	 * @return
	 */
	List<Category> getCategories();
}
