package com.edu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.edu.entity.Book;

public interface BookDao {
	
	int getBookCount(Connection connection, String sql, Object... args) throws SQLException;
	
	/**
	 * 新增图书
	 * 
	 * @param sql
	 * @param args
	 */
	void addBook(Connection connection, String sql, Object... args);

	/**
	 * 删除图书
	 * 
	 * @param sql
	 * @param args
	 */
	void deleteBook(Connection connection, String sql, Object... args);

	/**
	 * 更新图书
	 * 
	 * @param sql
	 * @param args
	 */
	void updateBook(Connection connection, String sql, Object... args);

	/**
	 * 查询列表
	 * 
	 * @param sql
	 * @param clz
	 * @param args
	 * @return
	 */
	List<Book> getBookList(Connection connection, String sql, Class<Book> clz, Object... args);

	/**
	 * 查询单个
	 * 
	 * @param sql
	 * @param clz
	 * @param args
	 * @return
	 */
	Book getBook(Connection connection, String sql, Class<Book> clz, Object... args);

//	void connSet(Connection connection);
//
//	void connCommit(Connection connection);
//
//	void connRollback(Connection connection);
}
