package com.edu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.service.BookService;
import com.edu.service.Impl.BookServiceImpl;

/**
 * 批量删除
 */
public class BookBatchDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("批量删除");
		String[] bookIds = request.getParameterValues("arr[]");
//		System.out.println(Arrays.asList(bookIds));
		
		// for循环删除
		for (String string : bookIds) {
			bookService.deleteBookByBookId(Integer.parseInt(string));
		}
		
//		// 重定向到列表页
//		response.sendRedirect("BookListServlet");
		// Ajax不能重定向
	
		response.getWriter().write("ok");
	}
}
