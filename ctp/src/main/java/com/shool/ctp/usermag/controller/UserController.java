package com.shool.ctp.usermag.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.RespData;
import com.shool.ctp.bean.RoleBean;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.goodsmag.service.IGoodsService;
import com.shool.ctp.ordermag.service.IOrderService;
import com.shool.ctp.usermag.service.IRoleService;
import com.shool.ctp.usermag.service.IUserService;

/**
 * 用户操作
 * @author luolin
 * @date 2019年2月6日
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {
	
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private IRoleService roleServiceImpl;
	
	@Resource
	private IGoodsService goodsServiceImpl;
	
	@Resource
	private IOrderService orderServiceImpl;
	
	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/{id}",method= {RequestMethod.POST},produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public RespData addUser(@RequestParam(value="file",required = false)MultipartFile file, UserBean user,Integer userGrade) {
		RespData resp = new RespData();
		//新增用户
		if(0 == user.getId()) {
			user.setCreateTime(new Date());
			user.setDeleteFlag(0);
			user.setUserStatus(0);
			if(user.getGender() == 0) {
				user.setHeadPortrait("female_default.jpg");
			}else if(user.getGender() == 1) {
				user.setHeadPortrait("male_default.jpg");
			}else {
				user.setHeadPortrait("default.jpg");
			}
			RoleBean role = null;
			if(userGrade > 0 && userGrade != null) {
				role = roleServiceImpl.getRoleById(userGrade);
				user.setRole(role);
			}
			user.setLoginPwd("123456");
			UserBean checkUser = new UserBean();
			checkUser.setLoginName(user.getLoginName());
			if(userServiceImpl.checkManageLoginByUser(checkUser) == null) {
				try {
					user.setUserName(user.getLoginName());
					cudOperationServiceImpl.addByHibernate(user);
					resp.setSuccess(true);
					resp.setStatuCode(1);
					resp.setMessage("用户添加成功！");
					resp.setObj(user);
					return resp;
				} catch (Exception e) {
					resp.setSuccess(false);
					resp.setStatuCode(0);
					resp.setMessage("系统错误，用户添加失败！");
					return resp;
				}
				
			}
			resp.setSuccess(false);
			resp.setStatuCode(2);
			resp.setMessage("已有该用户，无法添加！");
			return resp;
		}else {			//用户自己修改个人信息，因为可以修改图片所以使用post请求
			System.out.println(user);
			RoleBean role = null;
			UserBean realUser = (UserBean)cudOperationServiceImpl.getObjectById(user.getId(), UserBean.class);
			realUser.setUpdateTime(new Date());
			if(user.getRealName() != null) {
				realUser.setRealName(user.getRealName());
			}
			if(user.getUserName() != null) {
				realUser.setUserName(user.getUserName());
			}
			if(user.getLoginPwd() != null) {
				realUser.setLoginPwd(user.getLoginPwd());
			}
			if(user.getIdentity() != null) {
				realUser.setIdentity(user.getIdentity());
			}
			if(user.getBayNumber() != null) {
				realUser.setBayNumber(user.getBayNumber());
			}
			if(user.getAddress() != null) {
				realUser.setAddress(user.getAddress());
			}
			if(user.getTelephone() != null) {
				realUser.setTelephone(user.getTelephone());
			}
			if(user.getEmail()!=null) {
				realUser.setEmail(user.getEmail());
			}
			if(user.getGender() != null && user.getGender() > 0) {
				realUser.setGender(user.getGender());
			}
			if(user.getLoginName()!=null) {
				realUser.setLoginName(user.getLoginName());
			}
			if(userGrade != null) {
				role = (RoleBean) cudOperationServiceImpl.getObjectById(userGrade, RoleBean.class);
				
				realUser.setRole(role);
			}
			if(user.getUserStatus() != null) {
				realUser.setUserStatus(user.getUserStatus());
			}
			if(user.getMyIntroduction() != null) {
				realUser.setMyIntroduction(user.getMyIntroduction());
			}
			String fileName = file.getOriginalFilename();
			if("" == fileName || null == fileName) {
				cudOperationServiceImpl.updateByHibernate(realUser);
				System.out.println(realUser);
				resp.setStatuCode(1);
				resp.setMessage("保存成功!");
				resp.setObj(realUser);
				System.out.println("没有文件操作");
				return resp;
			}else {
				
				//是否删除上次的图片
				boolean deleteImg = true;		//判断是否删除图片，默认需要删除
				if(null == realUser.getHeadPortrait() || "".equals(realUser.getHeadPortrait())) {	//
					deleteImg = false;
				}else if("male_default.jpg".equals(realUser.getHeadPortrait())) {
					deleteImg = false;
				}else if("female_default.jpg".equals(realUser.getHeadPortrait())) {
					  System.err.println(deleteImg);
					deleteImg = false;
				}else if("default.jpg".equals(realUser.getHeadPortrait())) {
					deleteImg = false;
				}
				//图片的路径
				String path = "E:\\CampusTradingPlatform\\img\\userimg\\";
				System.err.println(deleteImg);
				if(deleteImg) {
					String imgPath = path+realUser.getHeadPortrait();
					File img = new File(imgPath);
					if(img.exists()) {
						img.delete();
						System.out.println("删除成功");
					}
				}
				
				String fileType = fileName.substring(fileName.lastIndexOf("."));
				long currentTime = System.currentTimeMillis();
				System.out.println(fileType);
				fileName = "UI"+currentTime+fileType;
				path = path + fileName;
				try {
					File f = new File(path);
					
					if(!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}
					//上传到指定路径
					file.transferTo(f);
					realUser.setHeadPortrait(fileName);
					cudOperationServiceImpl.updateByHibernate(realUser);
					resp.setStatuCode(1);
					resp.setMessage("保存成功!");
					resp.setObj(realUser);
					System.out.println(path);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println(path);
					resp.setMessage("图片上传出错！");
					resp.setSuccess(false);
					resp.setStatuCode(10);
					return resp;
				}
				
				System.out.println("有文件");
			}
			return resp;
		}
		
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.DELETE})
	@ResponseBody
	public RespData deleteUser(@PathVariable("id") Long id) {
		RespData resp = new RespData();
		UserBean realUser = (UserBean)cudOperationServiceImpl.getObjectById(id, UserBean.class);
		realUser.setDeleteFlag(1);
		realUser.setUpdateTime(new Date());
		try {
			cudOperationServiceImpl.updateByHibernate(realUser);
			resp.setMessage("删除成功");
			resp.setStatuCode(1);
			resp.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resp.setMessage("系统错误，删除失败！");
			resp.setStatuCode(0);
			resp.setSuccess(false);
			return resp;
		}
		
		return resp;
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/batch/{id}",method= {RequestMethod.DELETE})
	@ResponseBody
	public RespData batchUser(@PathVariable("id") Long id,@RequestParam(value="userId") Long[] userId) {
		RespData resp = new RespData();
		if(id > 0) {
			UserBean realUser = (UserBean)cudOperationServiceImpl.getObjectById(id, UserBean.class);
			realUser.setDeleteFlag(1);
			realUser.setUpdateTime(new Date());
			
			try {
				cudOperationServiceImpl.updateByHibernate(realUser);
				resp.setMessage("删除成功");
				resp.setStatuCode(1);
				resp.setSuccess(true);
			} catch (Exception e) {
				// TODO: handle exception
				resp.setMessage("系统错误，删除失败！");
				resp.setStatuCode(0);
				resp.setSuccess(false);
			}
		}else if(id == 0) {

			for (Long i : userId) {
				UserBean realUser = (UserBean)cudOperationServiceImpl.getObjectById(i, UserBean.class);
				realUser.setDeleteFlag(1);
				realUser.setUpdateTime(new Date());
				try {
					cudOperationServiceImpl.updateByHibernate(realUser);
					resp.setMessage("删除成功");
					resp.setStatuCode(1);
					resp.setSuccess(true);
				} catch (Exception e) {
					// TODO: handle exception
					resp.setMessage("系统错误，"+realUser.getUserName()+"删除失败！");
					resp.setStatuCode(0);
					resp.setSuccess(false);
					return resp;
				}
			}
			
		}
		
		return resp;
	}
	
	
	/**
	 * 修改用户信息
	 * @param id
	 * @param user
	 * @param userGrade
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.PUT},produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public RespData updateUser(@PathVariable("id") Long id,UserBean user,Integer userGrade) {
		RespData resp = new RespData();
		
		
		RoleBean role = null;
		UserBean realUser = (UserBean)cudOperationServiceImpl.getObjectById(id, UserBean.class);
		realUser.setUpdateTime(new Date());
		if(user.getRealName() != null) {
			realUser.setRealName(user.getRealName());
		}
		if(user.getUserName() != null) {
			realUser.setUserName(user.getUserName());
		}
		if(user.getLoginPwd() != null) {
			realUser.setLoginPwd(user.getLoginPwd());
		}
		if(user.getIdentity() != null) {
			realUser.setIdentity(user.getIdentity());
		}
		if(user.getBayNumber() != null) {
			realUser.setBayNumber(user.getBayNumber());
		}
		if(user.getAddress() != null) {
			realUser.setAddress(user.getAddress());
		}
		if(user.getTelephone() != null) {
			realUser.setTelephone(user.getTelephone());
		}
		if(user.getEmail()!=null) {
			realUser.setEmail(user.getEmail());
		}
		if(user.getGender() != null && user.getGender() > 0) {
			realUser.setGender(user.getGender());
		}
		if(user.getLoginName()!=null) {
			realUser.setLoginName(user.getLoginName());
		}
		if(userGrade != null) {
			role = (RoleBean) cudOperationServiceImpl.getObjectById(userGrade, RoleBean.class);
			realUser.setRole(role);
		}
		if(user.getUserStatus() != null) {
			realUser.setUserStatus(user.getUserStatus());
		}
		if(user.getMyIntroduction() != null) {
			realUser.setMyIntroduction(user.getMyIntroduction());
		}
		cudOperationServiceImpl.updateByHibernate(realUser);
		resp.setStatuCode(1);
		resp.setMessage("保存成功!");
		return resp;
	}
	
	/**
	 * 获取所有用户
	 * @param user
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public PageBean getAllUser(UserBean user,@RequestParam("page") Integer currentPage,Integer limit) {
		PageBean page = new PageBean(currentPage,limit);
		userServiceImpl.findUserPage(page, user);
		page.setCode(0);
		page.setMessage("正在获取数据......");
		System.out.println(page.getData());
		return page;
	}
	
	/**
	 * 获取用户的商品
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/goods/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public PageBean userGoods(@PathVariable("id") Long id,Integer page,Integer limit,GoodsBean goods) {
		System.out.println(id);
		PageBean p = new PageBean(page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		UserBean user = (UserBean) cudOperationServiceImpl.getObjectById(id, UserBean.class);
		goods.setUser(user);
		int saleQuantity = goodsServiceImpl.getSaleCountByUserId(id);
		int deleteQuantity = goodsServiceImpl.getDeleteCountByUserId(id);
		map.put("saleQuantity", saleQuantity);
		map.put("deleteQuantity", deleteQuantity);
		System.err.println(goods);
		goodsServiceImpl.findGoodsBeanReturnPage(p, goods);
		
		if(null!=p.getData() && p.getData().size()>0){
			p.setCode(1);
			p.setCount(p.getData().size());
			p.setMessage("获取成功！");
			p.setObj(map);
			return p;
		}
		p.setCode(0);
		p.setMessage("无数据");
		p.setObj(map);
		return p;
	}
	
	/**
	 * 获取不同信息的订单
	 * @param userId
	 * @param page
	 * @param limit
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/order/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public PageBean userOrder(@PathVariable("id") Long userId,Integer page,Integer limit,String goodsName,String type) {
		PageBean p = new PageBean(page,limit);
		UserBean user = new UserBean();
		user.setId(userId);
		OrderBean order = new OrderBean();
		if("1".equals(type)) {
			orderServiceImpl.findOrderByUser(p, userId,goodsName);
			Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
			Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
			allSal.put("monthNumber", monthSal.get("yearNumber"));
			allSal.put("monthSal", monthSal.get("yearSal"));
			p.setObj(allSal);
			p.setCode(1);
			p.setMessage("用户的所有订单信息，获取成功！");
			if(null == p.getData()) {
				p.setData(new ArrayList<>());
			}
			return p;
		}else if("2".equals(type)) {
			UserBean buyer = (UserBean) cudOperationServiceImpl.getObjectById(userId, UserBean.class);
			order.setBuyer(buyer);
			order.setOrderStatus(12);
			order.setGoodsName(goodsName);
			orderServiceImpl.findOrderByOrder(p, order);
			Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
			Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
			allSal.put("monthNumber", monthSal.get("yearNumber"));
			allSal.put("monthSal", monthSal.get("yearSal"));
			p.setObj(allSal);
			p.setCode(1);
			p.setMessage("用户的购买订单信息，获取成功！");
			if(null == p.getData()) {
				p.setData(new ArrayList<>());
			}
			return p;
		}else if("3".equals(type)) {
			UserBean vendor = (UserBean) cudOperationServiceImpl.getObjectById(userId, UserBean.class);
			order.setVendor(vendor);
			order.setOrderStatus(12);
			order.setGoodsName(goodsName);
			orderServiceImpl.findOrderByOrder(p, order);
			Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
			Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
			allSal.put("monthNumber", monthSal.get("yearNumber"));
			allSal.put("monthSal", monthSal.get("yearSal"));
			p.setObj(allSal);
			p.setCode(1);
			p.setMessage("用户的出售订单信息，获取成功！");
			if(null == p.getData()) {
				p.setData(new ArrayList<>());
			}
			return p;
		}else if("4".equals(type)) {
			UserBean buyer = (UserBean) cudOperationServiceImpl.getObjectById(userId, UserBean.class);
			order.setBuyer(buyer);
			order.setOrderStatus(4);
			order.setGoodsName(goodsName);
			orderServiceImpl.findOrderByOrder(p, order);
			Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
			Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
			allSal.put("monthNumber", monthSal.get("yearNumber"));
			allSal.put("monthSal", monthSal.get("yearSal"));
			p.setObj(allSal);
			p.setCode(1);
			p.setMessage("用户购买成功的订单信息，获取成功！");
			if(null == p.getData()) {
				p.setData(new ArrayList<>());
			}
			return p;
		}else if("5".equals(type)) {
			UserBean vendor = (UserBean) cudOperationServiceImpl.getObjectById(userId, UserBean.class);
			order.setVendor(vendor);
			order.setOrderStatus(4);
			order.setGoodsName(goodsName);
			orderServiceImpl.findOrderByOrder(p, order);
			Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
			Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
			allSal.put("monthNumber", monthSal.get("yearNumber"));
			allSal.put("monthSal", monthSal.get("yearSal"));
			p.setObj(allSal);
			p.setCode(1);
			p.setMessage("用户出售成功的订单信息，获取成功！");
			if(null == p.getData()) {
				p.setData(new ArrayList<>());
			}
			return p;
		}
		
		Map<String,Object> monthSal = orderServiceImpl.getYearSalByUserId(userId);
		Map<String,Object> allSal = orderServiceImpl.getAllSalByUserId(userId);
		allSal.put("monthNumber", monthSal.get("yearNumber"));
		allSal.put("monthSal", monthSal.get("yearSal"));
		p.setObj(allSal);
		p.setCode(0);
		p.setMessage("无数据");
		return p;
	}
}
