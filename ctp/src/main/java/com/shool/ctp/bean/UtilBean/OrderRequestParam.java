package com.shool.ctp.bean.UtilBean;

import java.io.Serializable;

public class OrderRequestParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7517418827356554112L;

	/**
	 * 商品编号
	 */
	private String goodsNumber;
	
	/**
	 * 购买数量
	 */
	private Integer bayNumber;
	
	/**
	 * 购买人id
	 */
	private Long buyerId;
	
	/**
	 * 发布人id
	 */
	private Long vendorId;

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getBayNumber() {
		return bayNumber;
	}

	public void setBayNumber(Integer bayNumber) {
		this.bayNumber = bayNumber;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	@Override
	public String toString() {
		return "OrderRequestParam [goodsNumber=" + goodsNumber + ", bayNumber=" + bayNumber + ", buyerId=" + buyerId
				+ ", vendorId=" + vendorId + "]";
	}
	
}
