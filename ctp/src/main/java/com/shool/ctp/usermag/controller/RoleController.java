package com.shool.ctp.usermag.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shool.ctp.bean.RoleBean;
import com.shool.ctp.usermag.service.IRoleService;

/**
 * 权限控制类
 * @author luolin
 * @date 2019年2月18日
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	private IRoleService roleServiceImpl;
	
	/**
	 * 所有权限
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.GET})
	@ResponseBody
	public List<RoleBean> allRole(){
		
		List<RoleBean> list = new ArrayList<RoleBean>();
		list = roleServiceImpl.allRole();
		return list;
		
	}
}
