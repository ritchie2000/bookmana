package com.edu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.entity.Category;
import com.edu.service.CategoryService;
import com.edu.service.Impl.CategoryServiceImpl;

/**
 * 
 */
public class BookToAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> categories = categoryService.getCategories();
		request.setAttribute("categories", categories);

		request.getRequestDispatcher("/book-add.jsp").forward(request, response);
	}

}
