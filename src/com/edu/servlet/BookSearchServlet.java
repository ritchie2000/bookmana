package com.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.edu.dto.Page;
import com.edu.service.BookService;
import com.edu.service.Impl.BookServiceImpl;

/**
 * 图书搜索
 */
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String bookName = request.getParameter("bookName");
		
		Page books = bookService.searchBookLikeBookName(Integer.parseInt(pageNo), Integer.parseInt(pageSize), bookName);
		
		String json = JSON.toJSONStringWithDateFormat(books, "yyyy-MM-dd");
		response.getWriter().write(json);
	}

}
