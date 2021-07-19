package com.shool.ctp.ordermag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;

/**
 * 订单持久层接口类
 * @author luolin
 * @date 2019年2月10日
 */
public interface IOrderDao {
	
	/**
	 * 条件查询订单的总条数
	 * @param order
	 * @return
	 */
	int countOrderBeanReturnPage(OrderBean order);
	
	/**
	 * 分页条件查询的数据
	 * @param page
	 * @param order
	 * @return
	 */
	List<?> findOrderBeanReturnPage(PageBean page,OrderBean order);
	
	/**
	 * 某个用户所有订单的总数
	 * @param userId
	 * @param goodsName
	 * @return
	 */
	int countOrderBeanReturnPageByUser(Long userId,String goodsName);
	
	/**
	 * 查询某个用户的所有订单
	 * @param userId
	 * @param goodsName
	 * @param page
	 * @return
	 */
	List<?> findOrderBeanReturnPageByUser(Long userId,String goodsName,PageBean page);
	
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
