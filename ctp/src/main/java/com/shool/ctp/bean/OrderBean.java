package com.shool.ctp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 订单类
 * @author luolin
 * @date 2019年2月9日
 */
@Entity
@Table(name="t_order")
public class OrderBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4740109755636590731L;

	/**
	 * 订单id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="orderId",strategy="identity")
	@GeneratedValue(generator="orderId")
	private Long id;
	
	/**
	 * 订单编号
	 */
	@Column(name="order_number")
	private String orderNumber;
	
	/**
	 * 商品单价
	 */
	@Column(name="goods_price")
	private Float goodsPrice;
	
	/**
	 * 商品名称
	 */
	@Column(name="goods_name")
	private String goodsName;
	
	/**
	 * 商品编号
	 */
	@Column(name="goods_number")
	private String goodsNumber;
	
	/**
	 * 订单状态
	 */
	@Column(name="order_status")
	private Integer orderStatus;
	
	/**
	 * 购买数量
	 */
	@Column(name="buy_goods_quantity")
	private Integer buyQuantity;
	
	/**
	 * 订单生成时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 删除时间
	 */
	@Column(name="delete_time")
	private Date deleteTime;
	
	/**
	 * 商品总价
	 */
	@Column(name="commodity_price")
	private Float commodityPrice;
	
	/**
	 * 删除标记
	 */
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	/**
	 * 买家名称
	 */
	@Column(name="buyers_name")
	private String buyersName;
	
	/**
	 * 卖家名称
	 */
	@Column(name="vendor_name")
	private String vendorName;
	
	/**
	 * 交易地址
	 */
	@Column(name="consignee_address")
	private String consigneeAddress;
	
	/**
	 * 联系电话
	 */
	@Column(name="telephone_number")
	private String telephoneNumber;

	/**
	 * 商品图片
	 */
	@Column(name="goods_image")
	private String goodsImage;
	
	/**
	 * 购买人
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_buyer_id")
	private UserBean buyer;
	
	/**
	 * 发布人
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_vendor_id")
	private UserBean vendor;
	
	/**
	 * 只用来查询该订单的商品信息
	 */
	 @Transient
	private GoodsBean goods;
	
	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Integer buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Float getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(Float commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getBuyersName() {
		return buyersName;
	}

	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public UserBean getBuyer() {
		return buyer;
	}

	public void setBuyer(UserBean buyer) {
		this.buyer = buyer;
	}

	public UserBean getVendor() {
		return vendor;
	}

	public void setVendor(UserBean vendor) {
		this.vendor = vendor;
	}
	
	public GoodsBean getGoods() {
		return goods;
	}

	public void setGoods(GoodsBean goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", orderNumber=" + orderNumber + ", goodsPrice=" + goodsPrice + ", goodsName="
				+ goodsName + ", goodsNumber=" + goodsNumber + ", orderStatus=" + orderStatus + ", buyQuantity="
				+ buyQuantity + ", createTime=" + createTime + ", deleteTime=" + deleteTime + ", commodityPrice="
				+ commodityPrice + ", deleteFlag=" + deleteFlag + ", buyersName=" + buyersName + ", vendorName="
				+ vendorName + ", consigneeAddress=" + consigneeAddress + ", telephoneNumber=" + telephoneNumber
				+ ", goodsImage=" + goodsImage + ", buyer=" + buyer + ", vendor=" + vendor + ", goods=" + goods + "]";
	}
	
}
