package com.shool.ctp.goodsmag.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.dao.IGoodsDao;
import com.shool.ctp.goodsmag.service.IGoodsService;
/**
 * 业务层商品接口实现类
 * @author luolin
 * @date 2019年1月12日
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

	@Resource
	private IGoodsDao goodsDaoImpl; 
	
	@Override
	public int addGoods(GoodsBean goods) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.addGoods(goods);
	}

	@Override
	public int updateGoods(GoodsBean goods) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.updateGoods(goods);
	}

	@Override
	public int deleteGoods(Integer id) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.deleteGoods(id);
	}

	@Override
	public GoodsBean getGoodsById(Integer id) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.getGoodsById(id);
	}

	@Override
	public int checkGoodsByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.checkGoodsByGoodsName(goodsName);
	}

	@Override
	public void findGoodsBeanReturnPage(PageBean page, GoodsBean goods) {
		// TODO Auto-generated method stub
		int totalRows = goodsDaoImpl.countGoodsBeanReturnPage(goods);
		List<?> datas = null;
		if(totalRows > 0) {
			datas = goodsDaoImpl.findGoodsBeanReturnPage(page, goods);
			System.out.println(datas);
		}
		page.setTotalRows(totalRows);
		page.setData(datas);
	}

	@Override
	public GoodsBean selectGoodsByGoodsNumber(String goodsNumber) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.selectGoodsByGoodsNumber(goodsNumber);
	}

	@Override
	public List<GoodsBean> getGoodsByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.getGoodsByUserId(id);
	}

	@Override
	public int getSaleCountByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.getSaleCountByUserId(id);
	}

	@Override
	public int getDeleteCountByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsDaoImpl.getDeleteCountByUserId(id);
	}
	
}
