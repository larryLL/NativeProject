package com.shool.ctp.usermag.service;

import java.util.List;

import com.shool.ctp.bean.RoleBean;

/**
 * 表现层角色服务接口
 * @author luolin
 * @date 2019年2月8日
 */
public interface IRoleService {
	
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
