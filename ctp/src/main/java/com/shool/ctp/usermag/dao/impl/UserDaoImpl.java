package com.shool.ctp.usermag.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.usermag.dao.IUserDao;
import com.shool.ctp.usermag.mapper.UserMapper;
/**
 * 持久层用户接口的实现
 * @author luolin
 * @date 2019年1月13日
 */
@Repository
public class UserDaoImpl implements IUserDao {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public int addUser(UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	@Override
	public int updateUser(UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteUser(id);
	}

	@Override
	public UserBean getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

	@Override
	public UserBean checkManageLoginByUser(UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.checkManageLoginByUser(user);
	}
	
	@Override
	public int countUserPage(UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.countUserPage(user);
	}

	@Override
	public List<?> findUserPage(PageBean page, UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.findUserPage(page, user);
	}

	

}
