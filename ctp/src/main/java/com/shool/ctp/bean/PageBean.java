package com.shool.ctp.bean;

import java.util.List;

/**
 * 分页对象
 * @author luolin
 * @date 2018年12月20日
 */
public class PageBean {
	
	/**
	 * 当前页码
	 */
	private int page;
	/**
	 * 分页显示数据的行数
	 */
	private int rows;
	/**
	 * 数据获取的起始位置(数据库中)
	 */
	private int index;
	/**
	 * 数据总条数
	 */
	private int totalRows;
	/**
	 * 数据总条数
	 */
	private int count;
	
	/**
	 * 总共有多少页
	 */
	private int totalPage;
	
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序方式
	 */
	private String order;
	
	/**
	 * 具体的数据
	 **/
	private List<?> data;

	/**
	 * 接口状态
	 */
	private int code;
	
	/**
	 * 接口信息
	 */
	private String message;
	
	/**
	 * 排序字段
	 */
	private String orderName;
	
	/**
	 * 排序的顺序
	 */
	private String orderType;
	
	/**
	 * 返回的单条数据
	 */
	private Object obj;
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageBean(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
		//计算数据获取的起始位置
		this.index = (page - 1) * rows;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		this.count = totalRows;
		//总页码
		this.totalPage = totalRows % rows == 0 ? totalRows / rows : totalRows / rows + 1;
		
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", index=" + index + ", totalRows=" + totalRows
				+ ", count=" + count + ", totalPage=" + totalPage + ", sort=" + sort + ", order=" + order + ", data="
				+ data + ", code=" + code + ", message=" + message + ", orderName=" + orderName + ", orderType="
				+ orderType + ", obj=" + obj + "]";
	}
}
