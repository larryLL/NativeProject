package com.shool.ctp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 商品类型类
 * @author luolin
 * @date 2019年1月12日
 */
@Entity
@Table(name="t_goodstype")
public class GoodsTypeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4504765922617281562L;
	
	/**
	 * 商品类型id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="goodsTypeId",strategy="identity")
	@GeneratedValue(generator="goodsTypeId")
	private Long id;
	
	/**
	 * 商品类型名称
	 */
	@Column(name="type_name")
	private String typeName;
	
	/**
	 * 商品类型介绍
	 */
	@Column(name="type_detail")
	private String typeDetail;
	
	/**
	 * 商品类型编号
	 */
	@Column(name="type_number")
	private String typeNumber;
	
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
	 * 删除标记  0-未删除  1-已删除
	 */
	@Column(name="delete_flag")
	private Integer deleteFlag;

	public GoodsTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(String typeDetail) {
		this.typeDetail = typeDetail;
	}

	public String getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "GoodsTypeBean [id=" + id + ", typeName=" + typeName + ", typeDetail=" + typeDetail + ", typeNumber="
				+ typeNumber + ", createTime=" + createTime + ", updateTime=" + updateTime + ", deleteFlag="
				+ deleteFlag + "]";
	}
	
	
}
