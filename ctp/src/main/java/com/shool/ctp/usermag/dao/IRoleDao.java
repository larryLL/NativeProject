package com.shool.ctp.usermag.dao;

import java.util.List;

import com.shool.ctp.bean.RoleBean;

/**
 * 持久层角色接口
 * @author luolin
 * @date 2019年2月8日
 */
public interface IRoleDao {
	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 */
	public RoleBean getRoleById(Integer id);
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	public List<RoleBean> allRole();
	
}
