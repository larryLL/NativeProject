package com.shool.ctp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 角色类
 * @author luolin
 * @date 2019年2月1日
 */
@Entity
@Table(name="t_role")
public class RoleBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542152968469522003L;

	/**
	 * 角色id
	 */
	@Id
	@Column(name="id")
	@GenericGenerator(name="roleId",strategy="identity")
	@GeneratedValue(generator="roleId")
	private Integer id;
	
	/**
	 * 角色名称
	 */
	@Column(name="role_name")
	private String roleName;
	
	/**
	 * 角色描述
	 */
	@Column(name="role_detail")
	private String roleDetail;
	
	public RoleBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDetail() {
		return roleDetail;
	}

	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}

	@Override
	public String toString() {
		return "RoleBean [id=" + id + ", roleName=" + roleName + ", roleDetail=" + roleDetail + "]";
	}
	
}
