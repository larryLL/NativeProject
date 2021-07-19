package com.shool.ctp.goodsmag.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.service.IGoodsService;

/**
 * 商品测试类
 * @author luolin
 * @date 2019年1月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class GoodsServiceTest {
	
	@Resource
	private IGoodsService goodsServiceImpl;
	
	@Test
	public void addGoods(){
		
		GoodsBean goods = new GoodsBean();
		goods.setGoodsName("商品一");
		goods.setGoodsDetail("详情");
		goods.setGoodsDegree(0.2f);
		goods.setGoodsOriginalPrice(30f);
		goods.setGoodsPrice(20f);
		goods.setGoodsStatus(0);
		goods.setGoodsQuantity(2);
		goods.setImage("图片地址");
		
		goods.setCreateTime(new Date());
		
		int result = goodsServiceImpl.addGoods(goods);
		System.out.println(result);
	}
	
	@Test
	public void update() {
		GoodsBean goods = goodsServiceImpl.getGoodsById(3);
		goods.setGoodsName("商品二");
		int result = goodsServiceImpl.updateGoods(goods);
		System.out.println(result);
//		System.out.println(goods);
	}
	
	@Test
	public void delete() {
		int result = goodsServiceImpl.deleteGoods(3);
		System.out.println(result);
		GoodsBean goods = goodsServiceImpl.getGoodsById(3);
		System.out.println(goods);
	}
	
	@Test
	public void getGoodsById() {
		GoodsBean goods = goodsServiceImpl.getGoodsById(7);
		System.out.println(goods);
	}
	
	@Test
	public void findGoodsPage() {
		PageBean page = new PageBean(1,3);
		GoodsBean goods = new GoodsBean();
		goods.setGoodsName("");
		goodsServiceImpl.findGoodsBeanReturnPage(page, goods);
		System.out.println(page);
	}
}
