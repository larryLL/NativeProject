package com.shool.ctp.cudmag.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.RoleBean;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.goodsmag.service.IGoodsService;
import com.shool.ctp.goodsmag.service.IGoodsTypeService;
import com.shool.ctp.usermag.service.IUserService;

/**
 * 增删改操作测试类
 * @author luolin
 * @date 2019年1月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class CudOperationTest {
	
	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	@Resource
	private IGoodsService goodsServiceImpl;
	
	@Resource
	private IGoodsTypeService goodsTypeServiceImpl;
	
	@Resource
	private IUserService userServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void add() {
		
		UserBean user = (UserBean) cudOperationServiceImpl.getObjectById(1L, UserBean.class);
		
		GoodsBean goods = new GoodsBean();
		goods.setGoodsName("商品三");
		goods.setGoodsDetail("详情");
		goods.setGoodsDegree(0.2f);
		goods.setGoodsOriginalPrice(30f);
		goods.setGoodsPrice(20f);
		goods.setGoodsStatus(0);
		goods.setGoodsQuantity(2);
		goods.setImage("图片三地址");
		goods.setCreateTime(new Date());
		
		GoodsTypeBean goodsType = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(1L, GoodsTypeBean.class);
		
		goods.setType(goodsType);
		
		List<GoodsBean> goodslist = new ArrayList<>();
		goodslist.add(goods);
		user.setGoods(goodslist);
		System.out.println(goods);
		cudOperationServiceImpl.updateByHibernate(user);
		
	}
	
	@Test
	public void update() {
//		UserBean user = (UserBean) cudOperationServiceImpl.getObjectById(1L, UserBean.class);
		GoodsBean goods = (GoodsBean) cudOperationServiceImpl.getObjectById(2L, GoodsBean.class);
//		GoodsTypeBean user = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(1L, GoodsTypeBean.class);
//		user.setTypeNumber("1213");
//		RoleBean role = (RoleBean) cudOperationServiceImpl.getObjectById(2, RoleBean.class);
//		user.setUserName("12312");
//		user.setRole(role);
//		cudOperationServiceImpl.updateByHibernate(user);
		log.info(goods.toString());
	}
	
	@Test
	public void updateUser() {
//		UserBean user = 
	}
	
	@Test
	public void delete() {}
}
