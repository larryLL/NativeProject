package com.shool.ctp.ordermag.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.goodsmag.dao.IGoodsDao;
import com.shool.ctp.ordermag.dao.IOrderDao;
import com.shool.ctp.ordermag.service.IOrderService;

/**
 * 表现层，订单接口实现类
 * @author luolin
 * @date 2019年2月10日
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Resource
	private IOrderDao orderDaoImpl;
	
	@Resource
	private IGoodsDao goodsDaoImpl;
	
	@Override
	public void findOrderByOrder(PageBean page, OrderBean order) {
		// TODO Auto-generated method stub
		int totalRows = orderDaoImpl.countOrderBeanReturnPage(order);
		
		List<OrderBean> datas = null;
		if(totalRows > 0 ) {
			datas = (List<OrderBean>) orderDaoImpl.findOrderBeanReturnPage(page, order);
			for (OrderBean orderBean : datas) {
				GoodsBean goods = goodsDaoImpl.selectGoodsByGoodsNumber(orderBean.getGoodsNumber());
				orderBean.setGoods(goods);
				System.out.println(orderBean.getGoods());
			}
		}
		
		page.setTotalRows(totalRows);
		page.setData(datas);
	}

	@Override
	public void findOrderByUser(PageBean page, Long userId,String goodsName) {
		// TODO Auto-generated method stub   orderDaoImpl
		int totalRows = orderDaoImpl.countOrderBeanReturnPageByUser(userId,goodsName);
		
		List<OrderBean> datas = null;
		if(totalRows > 0 ) {
			datas = (List<OrderBean>) orderDaoImpl.findOrderBeanReturnPageByUser(userId, goodsName, page);
			for (OrderBean orderBean : datas) {
				GoodsBean goods = goodsDaoImpl.selectGoodsByGoodsNumber(orderBean.getGoodsNumber());
				orderBean.setGoods(goods);
				System.out.println(orderBean.getGoods());
			}
		}
		
		page.setTotalRows(totalRows);
		page.setData(datas);
	}

	@Override
	public Map<String, Object> getYearSalByUserId(Long userId) {
		// TODO Auto-generated method stub
		return orderDaoImpl.getYearSalByUserId(userId);
	}

	@Override
	public Map<String, Object> getAllSalByUserId(Long userId) {
		// TODO Auto-generated method stub
		return orderDaoImpl.getAllSalByUserId(userId);
	}

}
