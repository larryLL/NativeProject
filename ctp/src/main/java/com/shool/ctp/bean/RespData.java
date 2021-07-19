package com.shool.ctp.bean;

import java.io.Serializable;
import java.util.List;

public class RespData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4535999806747060599L;

	/**
	 * 总条数
	 */
	private int total; 
	
	/**
	 * 具体的多行数据
	 */
	private List<?> rows;//具体的数据
	
	/**
	 * 具体的某一条数据
	 */
	private Object obj;
	
	/**
	 * 成功状态
	 */
	private boolean success;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 数据的id
	 */
	private Integer id;
	
	/**
	 * 状态码
	 */
	private Integer statuCode;
	
	public RespData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RespData(int total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatuCode() {
		return statuCode;
	}
	public void setStatuCode(Integer statuCode) {
		this.statuCode = statuCode;
	}
	@Override
	public String toString() {
		return "RespData [total=" + total + ", rows=" + rows + ", obj=" + obj + ", success=" + success + ", message="
				+ message + ", id=" + id + ", statuCode=" + statuCode + "]";
	}
	
}
