package com.shool.ctp.usermag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.UserBean;

/**
 * 用户sql语句映射接口类
 * @author luolin
 * @date 2019年1月13日
 */
public interface UserMapper {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(@Param("user") UserBean user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int updateUser(@Param("user") UserBean user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int deleteUser(@Param("id") Integer id);
	
	/**
	 * 通过用户id查询用户
	 * @param id
	 * @return
	 */
	UserBean getUserById(@Param("id") Integer id);
	
	/**
	 * 通过用户查询用户信息
	 * @param user
	 * @return
	 */
	UserBean checkManageLoginByUser(@Param("user") UserBean user);
	
	/**
	 * 分页条件查询的结果总条数
	 * @param user
	 * @return
	 */
	int countUserPage(@Param("user") UserBean user);
	
	/**
	 * 分页条件查询用户
	 * @param page
	 * @param user
	 * @return
	 */
	List<?> findUserPage(@Param("page") PageBean page,@Param("user") UserBean user);
}
