package com.edu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.entity.Book;
import com.edu.entity.Category;
import com.edu.service.BookService;
import com.edu.service.CategoryService;
import com.edu.service.Impl.BookServiceImpl;
import com.edu.service.Impl.CategoryServiceImpl;

/**
 * 跳转更新
 */
public class BookToUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 回显数据
		String bookId = request.getParameter("bookId");
	
		Book book = bookService.getBookByBookId(Integer.parseInt(bookId));
		request.setAttribute("book", book);
		
		// 分类下拉框
		List<Category> categories = categoryService.getCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/book-update.jsp").forward(request, response);
	}

}
