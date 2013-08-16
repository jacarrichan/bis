package com.avicit.framework.web.support.pagination;

public class Pagination {

	private int start;
	
	private int limit;
	
	private String sorter;
	
	private String order = "DESC";
	
	private int total;
	

	public Pagination(int start,int limit){
		this.start = start;
		this.limit = limit;
	}
	
	public Pagination(int start,int limit,String sorter,String order){
		this.start = start;
		this.limit = limit;
		this.sorter = sorter;
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
