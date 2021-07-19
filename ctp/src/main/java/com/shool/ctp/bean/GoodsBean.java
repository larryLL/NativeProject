package com.shool.ctp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 商品类
 * @author luolin
 * @date 2019年1月12日
 */
@Entity
@Table(name="t_goods")
public class GoodsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4607078301321254262L;
	
	/**
	 * 商品id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="goodsId",strategy="identity")
	@GeneratedValue(generator="goodsId")
	private Long id;
	
	/**
	 * 商品名称
	 */
	@Column(name="goods_name")
	private String goodsName;
	
	/**
	 * 商品描述
	 */
	@Column(name="goods_detail")
	private String goodsDetail;
	
	/**
	 * 商品图片
	 */
	@Column(name="goods_image")
	private String image;
	
	/**
	 * 商品新旧程度   0~1
	 */
	@Column(name="goods_degree")
	private Float goodsDegree;
	
	/**
	 * 商品原价
	 */
	@Column(name="goods_original_price")
	private Float goodsOriginalPrice;
	
	/**
	 * 商品二手价
	 */
	@Column(name="goods_price")
	private Float goodsPrice;
	
	/**
	 * 商品总量
	 */
	@Column(name="goods_quantity")
	private	Integer goodsQuantity;
	
	/**
	 * 商品状态：0-待审核  1-已被预订  2-已发布 3-已出售完 4-已下架  5-未通过审核
	 */
	@Column(name="goods_status")
	private Integer goodsStatus;
	
	/**
	 * 上架时间
	 */
	@Column(name="register_time")
	private Date registerTime;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time")
	private Date updateTime;
	
	/**
	 * 删除时间
	 */
	@Column(name="delete_time")
	private Date deleteTime;
	
	/**
	 * 删除标记  0-未删除  1-已删除
	 */
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	/**
	 * 商品编号
	 */
	@Column(name="goods_number")
	private String goodsNumber;
	
	/**
	 * 交易地点
	 */
	@Column(name="trading_place")
	private String tradingPlace;
	
	/**
	 * 商品剩余数量
	 */
	@Column(name="goods_surplus")
	private Integer goodsSurplus;
	
	/**
	 * 商品出售数量
	 */
	@Column(name="sale_quantity")
	private Integer saleQuantity;
	
	/**
	 * 关联用户（fk_user_id）
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.REFRESH)
	@JoinColumn(name="fk_user_id")
	private UserBean user;
	
	/**
	 * 关联商品类型（fk_goodstype_id）
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_goodstype_id")
	private GoodsTypeBean type;

	public GoodsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public String getImage() {
		return image;
	}

	public Float getGoodsDegree() {
		return goodsDegree;
	}

	public Float getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public UserBean getUser() {
		return user;
	}

	public GoodsTypeBean getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setGoodsDegree(Float goodsDegree) {
		this.goodsDegree = goodsDegree;
	}

	public void setGoodsOriginalPrice(Float goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getTradingPlace() {
		return tradingPlace;
	}

	public void setTradingPlace(String tradingPlace) {
		this.tradingPlace = tradingPlace;
	}
	
	public Integer getGoodsSurplus() {
		return goodsSurplus;
	}

	public void setGoodsSurplus(Integer goodsSurplus) {
		this.goodsSurplus = goodsSurplus;
	}

	public Integer getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public void setType(GoodsTypeBean type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GoodsBean [id=" + id + ", goodsName=" + goodsName + ", goodsDetail=" + goodsDetail + ", image=" + image
				+ ", goodsDegree=" + goodsDegree + ", goodsOriginalPrice=" + goodsOriginalPrice + ", goodsPrice="
				+ goodsPrice + ", goodsQuantity=" + goodsQuantity + ", goodsStatus=" + goodsStatus + ", registerTime="
				+ registerTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", deleteTime="
				+ deleteTime + ", deleteFlag=" + deleteFlag + ", goodsNumber=" + goodsNumber + ", tradingPlace="
				+ tradingPlace + ", goodsSurplus=" + goodsSurplus + ", saleQuantity=" + saleQuantity + ", user=" + user
				+ ", type=" + type + "]";
	}
	
}
