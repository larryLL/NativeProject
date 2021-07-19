package com.shool.ctp.usermag.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.usermag.service.IUserService;

/**
 * 用户测试类
 * @author luolin
 * @date 2019年1月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class UserServiceTest {
	
	@Resource
	private IUserService userServiceImpl;
	
	@Test
	public void addUser() {
		UserBean user = new UserBean();
		user.setUserName("黑白年代");
		user.setLoginName("1213811");
		user.setRealName("小白");
		user.setCreateTime(new Date());
		user.setTelephone("123456789099");
		
		int result = userServiceImpl.addUser(user);
		System.out.println(result);
		
	}
	
	@Test
	public void updateUser() {
		UserBean user = userServiceImpl.getUserById(1);
		System.out.println(user);
		user.setUserName("黑白年");
		user.setLoginName("1213811");
		user.setRealName("小黑");
		
		userServiceImpl.updateUser(user);
	}

	@Test
	public void deleteUser() {
		int result = userServiceImpl.deleteUser(1);
		System.out.println(result);
	}

	@Test
	public void getUserById() {
		UserBean user = userServiceImpl.getUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void findUserPage() {
		PageBean page = new PageBean(1,2);
		UserBean user = new UserBean();
		user.setUserName("黑");
		userServiceImpl.findUserPage(page, user);
		System.out.println(page);
		
	}
	
}
