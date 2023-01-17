package com.edu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//    private BookService bookService = new BookServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		List<Book> bookList = bookService.getBookList();
//		
//		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/book-list.jsp").forward(request, response);
	}

}
