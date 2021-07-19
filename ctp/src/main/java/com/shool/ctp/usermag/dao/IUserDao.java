package com.shool.ctp.usermag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.UserBean;

/**
 * 持久层用户接口
 * @author luolin
 * @date 2019年1月13日
 */
public interface IUserDao {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(UserBean user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int updateUser(UserBean user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int deleteUser(Integer id);
	
	/**
	 * 通过用户id查询用户
	 * @param id
	 * @return
	 */
	UserBean getUserById(Integer id);
	
	
	/**
	 * 通过用户查询用户信息
	 * @param user
	 * @return
	 */
	UserBean checkManageLoginByUser(UserBean user);
	
	/**
	 * 分页条件查询的结果总条数
	 * @param user
	 * @return
	 */
	int countUserPage(UserBean user);
	
	/**
	 * 分页条件查询用户
	 * @param page
	 * @param user
	 * @return
	 */
	List<?> findUserPage(PageBean page,UserBean user);
}
