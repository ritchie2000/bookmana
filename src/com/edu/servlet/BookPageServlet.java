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
 * Servlet implementation class BookPageServlet
 */
public class BookPageServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		
		Page bookPage = bookService.getBookPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		
		// 用了Ajax就不能转发和重定向
		// 将Java对象转换成json数据
		String jsonString = JSON.toJSONStringWithDateFormat(bookPage, "yyyy-MM-dd");
		// 将json数据传到前端
		response.getWriter().write(jsonString);;
		
	}


}
