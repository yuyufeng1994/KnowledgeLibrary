package com.lib.dto;

import java.util.List;

public class PageVo<T> {

	private int pageNum ; // 当前页
	
	private int prePage;//前一页
	
	private int totalPage ; // 总页
	
	private int rowCount ; // 总记录数
	
	public static int pageSize = 10; // 页大小
	
	private List<Integer> navigatepageNums;
	
	private List<T> data ; // 数据T

	public List<Integer> getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(List<Integer> navigatepageNums) {
		
		
		this.navigatepageNums=navigatepageNums;
		
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage() {
		this.prePage = this.pageNum-1;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		int totalPage = rowCount / pageSize;
		if (rowCount % pageSize > 0) {
			totalPage += 1;
		}
		setTotalPage(totalPage);
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageVo [pageNum=" + pageNum + ", prePage=" + prePage + ", totalPage=" + totalPage + ", rowCount="
				+ rowCount + ", data=" + data + ", navigatepageNums=" + navigatepageNums + "]";
	}
	
	
	
}
