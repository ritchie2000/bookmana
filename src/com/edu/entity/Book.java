package com.edu.entity;

import java.util.Date;

/**
 * 映射数据库book表
 *
 */
public class Book {

	private Integer bookId; // 编号
	private String bookName; // 书名
	private Double price; // 价格
	private String authorName; // 作者
	private Date createTime; // 创建时间
	private Integer categoryId; // 分类ID
	private Integer flag; // 是否上架
	private Category category; // 多对一

	public Book() {
	}

	public Book(Integer bookId, String bookName, Double price, String authorName, Date createTime, Integer categoryId,
			Integer flag, Category category) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;
		this.authorName = authorName;
		this.createTime = createTime;
		this.categoryId = categoryId;
		this.flag = flag;
		this.category = category;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", price=" + price + ", authorName=" + authorName
				+ ", createTime=" + createTime + ", categoryId=" + categoryId + ", flag=" + flag + ", category="
				+ category + "]";
	}

}
