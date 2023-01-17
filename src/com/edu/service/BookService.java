package com.edu.service;

import java.util.List;

import com.edu.dto.Page;
import com.edu.entity.Book;

public interface BookService {

	/**
	 * 图书搜索
	 * @param pageNo
	 * @param pageSize
	 * @param bookName
	 * @return
	 */
	Page searchBookLikeBookName(int pageNo, int pageSize, String bookName);
	
	/**
	 * 获取分页数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page getBookPage(int pageNo, int pageSize);
	
	/**
	 * 查询全部
	 * @return
	 */
	List<Book> getBookList();
	
	/**
	 * 添加图书
	 * @param book
	 */
	void addBook(Book book);
	
	/**
	 * 更新图书
	 * @param book
	 */
	void updateBook(Book book);
	
	/**
	 * 根据图书id删除图书
	 * @param bookId
	 */
	void deleteBookByBookId(Integer bookId);
	
	/**
	 * 根据图书id查询图书
	 * @param bookId
	 * @return
	 */
	Book getBookByBookId(Integer bookId); 
}
