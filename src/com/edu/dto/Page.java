package com.edu.dto;

/**
 * 专门用于封装分页信息
 * dto 前后端交互使用的java类模板对象
 * @author ZEYU
 *
 */
public class Page {
	private Integer pageCount;
	private Object page;
	private Integer pageNo;
	private Integer pageSize;

	public Page() {
	}

	public Page(Integer pageCount, Object page, Integer pageNo, Integer pageSize) {
		super();
		this.pageCount = pageCount;
		this.page = page;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Object getPage() {
		return page;
	}

	public void setPage(Object page) {
		this.page = page;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page [pageCount=" + pageCount + ", page=" + page + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ "]";
	}

}
