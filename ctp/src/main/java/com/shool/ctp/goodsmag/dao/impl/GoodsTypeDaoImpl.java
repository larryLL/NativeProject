package com.shool.ctp.goodsmag.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.dao.IGoodsTypeDao;
import com.shool.ctp.goodsmag.mapper.GoodsTypeMapper;
/**
 * 持久层商品类型接口的实现
 * @author luolin
 * @date 2019年1月13日
 */
@Repository
public class GoodsTypeDaoImpl implements IGoodsTypeDao {
	
	@Resource
	private GoodsTypeMapper goodsTypeMapper;
	
	@Override
	public int addGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.addGoodsType(goodsType);
	}

	@Override
	public int updateGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.updateGoodsType(goodsType);
	}

	@Override
	public int deleteGoodsType(Integer id) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.deleteGoodsType(id);
	}

	@Override
	public GoodsTypeBean getGoodsTypeById(Integer id) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.getGoodsTypeById(id);
	}

	@Override
	public int countGoodsTypePage(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.countGoodsTypePage(goodsType);
	}

	@Override
	public List<?> allGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.allGoodsType(goodsType);
	}
	
	@Override
	public List<?> findGoodsTypePage(PageBean page, GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.findGoodsTypePage(page, goodsType);
	}

	@Override
	public GoodsTypeBean getGoodsTypeByGoodsTypeName(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeMapper.getGoodsTypeByGoodsTypeName(goodsType);
	}

	
	
}
