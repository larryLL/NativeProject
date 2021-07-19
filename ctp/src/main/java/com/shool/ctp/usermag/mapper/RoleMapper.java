package com.shool.ctp.usermag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.RoleBean;

/**
 * 角色sql语句映射接口类
 * @author luolin
 * @date 2019年2月8日
 */
public interface RoleMapper {
	
	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 */
	public RoleBean getRoleById(@Param("id") Integer id);
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	public List<RoleBean> allRole();
}
