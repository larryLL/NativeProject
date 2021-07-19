package com.shool.ctp.usermag.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.bean.RoleBean;
import com.shool.ctp.usermag.dao.IRoleDao;
import com.shool.ctp.usermag.service.IRoleService;

/**
 * 表现层角色接口实现类
 * @author luolin
 * @date 2019年2月8日
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDaoImpl;
	
	@Override
	public RoleBean getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleDaoImpl.getRoleById(id);
	}

	@Override
	public List<RoleBean> allRole() {
		// TODO Auto-generated method stub
		return roleDaoImpl.allRole();
	}

}
