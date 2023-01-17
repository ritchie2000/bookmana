package com.edu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.service.BookService;
import com.edu.service.Impl.BookServiceImpl;

/**
 * 通过id删除图书
 */
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先拿到图书id
		String bookId = request.getParameter("bookId");

		bookService.deleteBookByBookId(Integer.parseInt(bookId));

		// 转发 -> 地址栏不变，F5刷新一下又删除一次，不好。
//		request.getRequestDispatcher("/BookListServlet").forward(request, response);
		// 重定向
		response.sendRedirect("BookListServlet");
	}

}
