package com.shool.ctp.ordermag.service;

import java.util.List;
import java.util.Map;

import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;

/**
 * 订单业务层接口类
 * @author luolin
 * @date 2019年2月10日
 */
public interface IOrderService {
	
	/**
	 * 分页选择查询订单
	 * @param page
	 * @param order
	 */
	void findOrderByOrder(PageBean page,OrderBean order);
	
	/**
	 * 分页查询某个用户的所有订单
	 * @param page
	 * @param userId
	 */
	void findOrderByUser(PageBean page,Long userId,String goodsName);
	
	/**
	 * 查询某个用户的月销售量和月销售额
	 * @param userId
	 * @return
	 */
	Map<String, Object> getYearSalByUserId(Long userId);

	/**
	 * 查询某个用户的总销售量和总销售额
	 * @param userIds
	 * @return
	 */
	Map<String, Object> getAllSalByUserId(Long userId);
}
