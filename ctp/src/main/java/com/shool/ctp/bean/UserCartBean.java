package com.shool.ctp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户购物车
 * @author luolin
 * @date 2019年2月9日
 */
@Entity
@Table(name="t_user_cart")
public class UserCartBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4405423333513756044L;

	/**
	 * 购物车id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="cartId",strategy="identity")
	@GeneratedValue(generator="cartId")
	private Integer id;
	
	/**
	 * 物品
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="fk_goods_id")
	private GoodsBean goods;
	
	/**
	 * 用户id
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="fk_user_id")
	private UserBean user;

	public UserCartBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GoodsBean getGoods() {
		return goods;
	}

	public void setGoods(GoodsBean goods) {
		this.goods = goods;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserCartBean [id=" + id + ", goods=" + goods + ", user=" + user + "]";
	}
	
	
}
