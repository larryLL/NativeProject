package com.shool.ctp.goodsmag.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.dao.IGoodsTypeDao;
import com.shool.ctp.goodsmag.service.IGoodsTypeService;
/**
 * 业务层商品类型接口的实现
 * @author luolin
 * @date 2019年1月13日
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService {

	@Resource
	private IGoodsTypeDao goodsTypeDaoImpl;
	
	@Override
	public int addGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.addGoodsType(goodsType);
	}

	@Override
	public int updateGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.updateGoodsType(goodsType);
	}

	@Override
	public int deleteGoodsType(Integer id) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.deleteGoodsType(id);
	}

	@Override
	public GoodsTypeBean getGoodsTypeById(Integer id) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.getGoodsTypeById(id);
	}
	
	@Override
	public List<?> allGoodsType(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.allGoodsType(goodsType);
	}

	@Override
	public void findGoodsType(PageBean page, GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		int totalRows = goodsTypeDaoImpl.countGoodsTypePage(goodsType);
		List<?> datas = null;
		if(totalRows > 0) {
			datas = goodsTypeDaoImpl.findGoodsTypePage(page, goodsType);
		}
		page.setData(datas);
		page.setTotalRows(totalRows);
	}

	@Override
	public GoodsTypeBean getGoodsTypeByGoodsTypeName(GoodsTypeBean goodsType) {
		// TODO Auto-generated method stub
		return goodsTypeDaoImpl.getGoodsTypeByGoodsTypeName(goodsType);
	}

}
