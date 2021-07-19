package com.shool.ctp.goodsmag.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.service.IGoodsTypeService;

/**
 * 商品类型测试类
 * @author luolin
 * @date 2019年1月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class GoodsTypeServiceTest {
	@Resource
	private IGoodsTypeService goodsTypeServiceImpl;
	
	@Test
	public void addGoodsType() {
		GoodsTypeBean goodsType = new GoodsTypeBean();
		goodsType.setTypeName("服装");
		goodsType.setTypeNumber("00220");
		goodsType.setCreateTime(new Date());
//		goodsType.setDeleteFlag(0);
		
		int result = goodsTypeServiceImpl.addGoodsType(goodsType);
		System.out.println(result+":"+goodsType);
	}
	
	@Test
	public void updateGoodsType() {
		GoodsTypeBean goodsType = goodsTypeServiceImpl.getGoodsTypeById(1);
//		System.out.println(goodsType);
		goodsType.setTypeName("食品");
		goodsType.setUpdateTime(new Date());
		goodsTypeServiceImpl.updateGoodsType(goodsType);
		
//		System.out.println();
	}
	
	@Test
	public void deletGoodsType() {
		int result = goodsTypeServiceImpl.deleteGoodsType(1);
		System.out.println(result);
	}
	
	@Test
	public void findGoodsTypePage() {
		PageBean page = new PageBean(1,2);
		GoodsTypeBean goodsType = new GoodsTypeBean();
		goodsType.setTypeName("食");
		goodsTypeServiceImpl.findGoodsType(page, goodsType);
		System.out.println(page);
	}
}
