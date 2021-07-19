package com.shool.ctp.usermag.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.usermag.dao.IUserDao;
import com.shool.ctp.usermag.service.IUserService;
/**
 * 业务层用户接口的实现
 * @author luolin
 * @date 2019年1月13日
 */
@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDaoImpl;
	
	@Override
	public int addUser(UserBean user) {
		// TODO Auto-generated method stub
		return userDaoImpl.addUser(user);
	}

	@Override
	public int updateUser(UserBean user) {
		// TODO Auto-generated method stub
		return userDaoImpl.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return userDaoImpl.deleteUser(id);
	}

	@Override
	public UserBean getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDaoImpl.getUserById(id);
	}

	@Override
	public UserBean checkManageLoginByUser(UserBean user) {
		// TODO Auto-generated method stub		
		return userDaoImpl.checkManageLoginByUser(user);
	}
	
	@Override
	public void findUserPage(PageBean page, UserBean user) {
		// TODO Auto-generated method stub
		int totalRows = userDaoImpl.countUserPage(user);
		List<UserBean> datas = null;
		if(totalRows > 0) {
			datas = (List<UserBean>) userDaoImpl.findUserPage(page, user);
		}
		page.setTotalRows(totalRows);
		page.setData(datas);
	}

	

}
