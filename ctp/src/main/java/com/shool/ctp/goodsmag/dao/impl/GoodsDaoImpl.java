package com.shool.ctp.goodsmag.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.dao.IGoodsDao;
import com.shool.ctp.goodsmag.mapper.GoodsMapper;
/**
 * 持久层商品接口实现类
 * @author luolin
 * @date 2019年1月12日
 */
@Repository
public class GoodsDaoImpl implements IGoodsDao {

	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	public int addGoods(GoodsBean goods) {
		// TODO Auto-generated method stub
		System.out.println(goods.getGoodsName());
		return goodsMapper.addGoods(goods);
	}

	@Override
	public int updateGoods(GoodsBean goods) {
		// TODO Auto-generated method stub
		return goodsMapper.updateGoods(goods);
	}

	@Override
	public int deleteGoods(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteGoods(id);
	}

	@Override
	public GoodsBean getGoodsById(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.getGoodsById(id);
	}

	@Override
	public int checkGoodsByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		return goodsMapper.checkGoodsByGoodsName(goodsName);
	}
	
	@Override
	public int countGoodsBeanReturnPage(GoodsBean goods) {
		// TODO Auto-generated method stub
		return goodsMapper.countGoodsBeanReturnPage(goods);
	}

	@Override
	public List<?> findGoodsBeanReturnPage(PageBean page, GoodsBean goods) {
		// TODO Auto-generated method stub
		return goodsMapper.findGoodsBeanReturnPage(page, goods);
	}

	@Override
	public GoodsBean selectGoodsByGoodsNumber(String goodsNumber) {
		// TODO Auto-generated method stub
		return goodsMapper.selectGoodsByGoodsNumber(goodsNumber);
	}

	@Override
	public List<GoodsBean> getGoodsByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsMapper.getGoodsByUserId(id);
	}

	@Override
	public int getSaleCountByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsMapper.getSaleCountByUserId(id);
	}

	@Override
	public int getDeleteCountByUserId(Long id) {
		// TODO Auto-generated method stub
		return goodsMapper.getDeleteCountByUserId(id);
	}

	

}
