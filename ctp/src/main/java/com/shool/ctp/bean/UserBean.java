package com.shool.ctp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用户类
 * @author luolin
 * @date 2019年1月12日
 */
@Entity
@Table(name="t_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","goods"})
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5613655096654819143L;

	/**
	 * 用户id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="userId",strategy="identity")
	@GeneratedValue(generator="userId")
	private Long id;
	
	/**
	 * 用户名
	 */
	@Column(name="user_name")
	private String userName;
	
	/**
	 * 登陆名
	 */
	@Column(name="login_name")
	private String loginName;
	
	/**
	 * 登陆密码
	 */
	@Column(name="login_pwd")
	private String loginPwd;
	
	/**
	 * 真实姓名
	 */
	@Column(name="real_name")
	private String realName;
	
	/**
	 * 性别
	 */
	@Column(name="gender")
	private Integer gender;
	
	/**
	 * 头像
	 */
	@Column(name="head_portrait")
	private String headPortrait;
	
	/**
	 * 邮箱
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * 地址
	 */
	@Column(name="address")
	private String address;
	
	/**
	 * 电话号码
	 */
	@Column(name="telephone")
	private String telephone;
	
	/**
	 * 我的简介
	 */
	@Column(name="my_introduction")
	private String myIntroduction;
	
	/**
	 * 用户状态
	 */
	@Column(name="user_status")
	private Integer userStatus;
	
	/**
	 * 支付宝账号
	 */
	@Column(name="bay_number")
	private String bayNumber;
	
	/**
	 * 身份证号
	 */
	@Column(name="identity")
	private String identity;
	
	/**
	 * 最近登陆时间
	 */
	@Column(name="login_time")
	private Date loginTime;
	
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
	 * 删除标记   0-未删除，1-已删除
	 */
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	/**
	 * 用户权限
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="fk_role_id")
	private RoleBean role;
	
	/**
	 * 用户发布的商品
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade(value= {CascadeType.SAVE_UPDATE})
	@JoinColumn(name="fk_user_id")
	private List<GoodsBean> goods;
	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMyIntroduction() {
		return myIntroduction;
	}

	public void setMyIntroduction(String myIntroduction) {
		this.myIntroduction = myIntroduction;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getBayNumber() {
		return bayNumber;
	}

	public void setBayNumber(String bayNumber) {
		this.bayNumber = bayNumber;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public List<GoodsBean> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsBean> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", userName=" + userName + ", loginName=" + loginName + ", loginPwd=" + loginPwd
				+ ", realName=" + realName + ", gender=" + gender + ", headPortrait=" + headPortrait + ", email="
				+ email + ", address=" + address + ", telephone=" + telephone + ", myIntroduction=" + myIntroduction
				+ ", userStatus=" + userStatus + ", bayNumber=" + bayNumber + ", identity=" + identity + ", loginTime="
				+ loginTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag
				+ ", role=" + role + "]";
	}
	
}
