package com.edu.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.entity.Book;
import com.edu.service.BookService;
import com.edu.service.Impl.BookServiceImpl;

/**
 * 图书更新
 */
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取请求参数
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String price = request.getParameter("price");
		String flag = request.getParameter("flag");
		String categoryId = request.getParameter("categoryId");
		String createTime = request.getParameter("createTime");

		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));
		book.setBookName(bookName);
		book.setAuthorName(authorName);
		book.setPrice(Double.parseDouble(price));
		book.setFlag(Integer.parseInt(flag));
		book.setCategoryId(Integer.parseInt(categoryId));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = simpleDateFormat.parse(createTime);
			book.setCreateTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		bookService.updateBook(book);

		// 重定向
		response.sendRedirect("BookListServlet");

	}

}
