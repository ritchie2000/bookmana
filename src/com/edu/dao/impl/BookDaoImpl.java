package com.edu.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.edu.dao.BaseDao;
import com.edu.dao.BookDao;
import com.edu.entity.Book;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public void addBook(Connection connection, String sql, Object... args) {
		update(connection, sql, args);
	}

	@Override
	public void deleteBook(Connection connection, String sql, Object... args) {
		update(connection, sql, args);
	}

	@Override
	public void updateBook(Connection connection, String sql, Object... args) {
		update(connection, sql, args);
	}

	@Override
	public List<Book> getBookList(Connection connection, String sql, Class<Book> clz, Object... args) {
		return getList(connection, sql, clz, args);
	}

	@Override
	public Book getBook(Connection connection, String sql, Class<Book> clz, Object... args) {
		return getOne(connection, sql, clz, args);
	}

	@Override
	public int getBookCount(Connection connection, String sql, Object... args) throws SQLException {
		return getCount(connection, sql, args);
	}

}
