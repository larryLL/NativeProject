package com.shool.ctp.usermag.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.shool.ctp.bean.RoleBean;
import com.shool.ctp.usermag.dao.IRoleDao;
import com.shool.ctp.usermag.mapper.RoleMapper;

/**
 * 持久层角色接口实现类
 * @author luolin
 * @date 2019年2月8日
 */
@Repository
public class RoleDaoImpl implements IRoleDao {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public RoleBean getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(id);
	}

	@Override
	public List<RoleBean> allRole() {
		// TODO Auto-generated method stub
		return roleMapper.allRole();
	}

}
