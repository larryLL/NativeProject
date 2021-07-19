package com.shool.ctp.ordermag.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.ordermag.dao.IOrderDao;
import com.shool.ctp.ordermag.mapper.OrderMapper;
/**
 * 持久层，订单接口实现类
 * @author luolin
 * @date 2019年2月10日
 */
@Repository
public class OrderDaoImpl implements IOrderDao {

	@Resource
	private OrderMapper orderMapper;
	
	@Override
	public int countOrderBeanReturnPage(OrderBean order) {
		// TODO Auto-generated method stub
		return orderMapper.countOrderBeanReturnPage(order);
	}

	@Override
	public List<?> findOrderBeanReturnPage(PageBean page, OrderBean order) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderBeanReturnPage(page, order);
	}

	@Override
	public int countOrderBeanReturnPageByUser(Long userId,String goodsName) {
		// TODO Auto-generated method stub
		return orderMapper.countOrderBeanReturnPageByUser(userId,goodsName);
	}

	@Override
	public List<?> findOrderBeanReturnPageByUser(Long userId,String goodsName, PageBean page) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderBeanReturnPageByUser(userId, goodsName, page);
	}

	@Override
	public Map<String, Object> getYearSalByUserId(Long userId) {
		// TODO Auto-generated method stub
		return orderMapper.getYearSalByUserId(userId);
	}

	@Override
	public Map<String, Object> getAllSalByUserId(Long userId) {
		// TODO Auto-generated method stub
		return orderMapper.getAllSalByUserId(userId);
	}

}
