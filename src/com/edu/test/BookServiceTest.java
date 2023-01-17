package com.edu.test;

import java.util.List;

import org.junit.Test;

import com.edu.dto.Page;
import com.edu.entity.Book;
import com.edu.service.BookService;
import com.edu.service.Impl.BookServiceImpl;

public class BookServiceTest {
	private BookService bookService = new BookServiceImpl();
	
	@Test
	public void searchBookLikeBookName() {
		Page page = bookService.searchBookLikeBookName(2, 3, "书");
		System.out.println(page);
	}
	
	@Test
	public void getBookList() {
		List<Book> bookList = bookService.getBookList();
		System.out.println(bookList);
	}
}
