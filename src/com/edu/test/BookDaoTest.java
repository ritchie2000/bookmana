package com.edu.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.edu.dao.BookDao;
import com.edu.dao.impl.BookDaoImpl;
import com.edu.entity.Book;
import com.edu.utils.JdbcUtils;

public class BookDaoTest {
	
	private BookDao bookDao = new BookDaoImpl();
			
	/**
	 * 测试-图书添加
	 * @throws SQLException 
	 */
	@Test
	public void addBook() throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		String sql = "INSERT INTO book(book_name, author_name, price, category_id, flag, create_time) VALUES(?,?,?,?,?,?)";
		bookDao.addBook(connection, sql, "测试图书名03", "测试作者名03", 13.3, 3, 1, new Date());
	}
	
	/**
	 * 测试-图书列表获取
	 * @throws SQLException 
	 */
	@Test
	public void getBookList() throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		String sql = "SELECT book_id as bookId, book_name as bookName, author_name as authorName, price, category_id as categoryId, flag, create_time as createTime FROM book";
		List<Book> bookList = bookDao.getBookList(connection, sql, Book.class);
		System.out.println(bookList);
	}
}
