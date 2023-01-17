package com.edu.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.edu.dao.BookDao;
import com.edu.dao.CategoryDao;
import com.edu.dao.impl.BookDaoImpl;
import com.edu.dao.impl.CategoryDaoImpl;
import com.edu.dto.Page;
import com.edu.entity.Book;
import com.edu.entity.Category;
import com.edu.service.BookService;
import com.edu.utils.JdbcUtils;

public class BookServiceImpl implements BookService {

	private BookDao bookDao = new BookDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public List<Book> getBookList() {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			// 取消默认提交
			connection.setAutoCommit(false);
			String sql = "SELECT book_id as bookId, book_name as bookName, author_name as authorName, price, category_id as categoryId, flag, create_time as createTime FROM book";

			List<Book> bookList = bookDao.getBookList(connection, sql, Book.class);

			// 通过book表的id查category表
			for (Book book : bookList) {
				Category category = categoryDao.getCategory(connection,
						"SELECT category_id as categoryId, category_name as categoryName FROM category WHERE category_id = ? ",
						Category.class, book.getCategoryId());
				// 把category添加到bookList中
				book.setCategory(category);
			}
			// 手动提交事务
			connection.commit();
			return bookList;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
		return null;

	}

	@Override
	public void addBook(Book book) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO book(book_name, author_name, price, category_id, flag, create_time) VALUES(?,?,?,?,?,?)";
			bookDao.addBook(connection, sql, book.getBookName(), book.getAuthorName(), book.getPrice(),
					book.getCategoryId(), book.getFlag(), book.getCreateTime());
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
	}

	@Override
	public void updateBook(Book book) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "UPDATE book SET book_name = ?, author_name = ?, price = ?, category_id = ?, flag = ?, create_time = ? WHERE book_id = ?";

			bookDao.updateBook(connection, sql, book.getBookName(), book.getAuthorName(), book.getPrice(),
					book.getCategoryId(), book.getFlag(), book.getCreateTime(), book.getBookId());
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
	}

	@Override
	public void deleteBookByBookId(Integer bookId) {

		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "DELETE FROM book WHERE book_id=?";

			bookDao.deleteBook(connection, sql, bookId);
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
	}

	/*
	 * 
	 */
	@Override
	public Book getBookByBookId(Integer bookId) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			// 取消默认提交
			connection.setAutoCommit(false);

			String sql = "SELECT book_id as bookId, book_name as bookName, author_name as authorName, price, category_id as categoryId, flag, create_time as createTime FROM book WHERE book_id = ?";

			Book book = bookDao.getBook(connection, sql, Book.class, bookId);

			connection.commit();
			return book;
		} catch (SQLException e) {

			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}

		return null;

	}

	@Override
	public Page getBookPage(int pageNo, int pageSize) {

		// 需要一个page对象的返回值
		Page page = new Page();
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT book_id as bookId, book_name as bookName, author_name as authorName, price, category_id as categoryId, flag, create_time as createTime FROM book LIMIT ?,?";
			List<Book> bookList = bookDao.getBookList(connection, sql, Book.class, (pageNo - 1) * pageSize, pageSize);

			for (Book book : bookList) {
				Category category = categoryDao.getCategory(connection,
						"SELECT category_id as categoryId, category_name as categoryName FROM category WHERE category_id = ? ",
						Category.class, book.getCategoryId());
				book.setCategory(category);
			}

			page.setPage(bookList);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);

			// book表总记录数
			int bookCount = bookDao.getBookCount(connection, "SELECT COUNT(*) as count FROM book");

			page.setPageCount(bookCount % pageSize == 0 ? bookCount / pageSize : bookCount / pageSize + 1);

			connection.commit();
			return page;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}

		return page;
	}

	@Override
	public Page searchBookLikeBookName(int pageNo, int pageSize, String bookName) {

		Page page = new Page();
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT book_id as bookId, book_name as bookName, author_name as authorName, price, category_id as categoryId, flag, create_time as createTime FROM book WHERE book_name LIKE ? LIMIT ?,?";
			List<Book> bookList = bookDao.getBookList(connection, sql, Book.class, "%"+bookName+"%", (pageNo-1)*pageSize, pageSize);
			
			for (Book book : bookList) {
				Category category = categoryDao.getCategory(connection, "SELECT category_id as categoryId, category_name as categoryName FROM category WHERE category_id = ? ", Category.class, book.getCategoryId());
				book.setCategory(category);
			}
			page.setPage(bookList);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			int count = bookDao.getBookCount(connection, "SELECT COUNT(*) AS count FROM book WHERE book_name LIKE ?", "%"+bookName+"%");
			
			page.setPageCount(count%pageSize == 0? count/pageSize:count/pageSize+1);
			
			connection.commit();
			return page;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
		
		return page;
	}

}
