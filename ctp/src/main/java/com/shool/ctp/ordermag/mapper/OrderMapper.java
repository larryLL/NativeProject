package com.shool.ctp.ordermag.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;

/**
 * 订单sql语句映射接口类
 * @author luolin
 * @date 2019年2月9日
 */
public interface OrderMapper {
	/**
	 * 条件查询订单的总条数
	 * @param order
	 * @return
	 */
	int countOrderBeanReturnPage(@Param("order")OrderBean order);
	
	/**
	 * 分页条件查询的数据
	 * @param page
	 * @param order
	 * @return
	 */
	List<?> findOrderBeanReturnPage(@Param("page")PageBean page,@Param("order")OrderBean order);
	
	/**
	 * 某个用户所有订单的总数
	 * @param userId
	 * @return
	 */
	int countOrderBeanReturnPageByUser(@Param("userId") Long userId,@Param("goodsName") String goodsName);
	
	/**
	 * 查询某个用户的所有订单
	 * @param userId
	 * @param page
	 * @return
	 */
	List<?> findOrderBeanReturnPageByUser(@Param("userId") Long userId,@Param("goodsName") String goodsName,@Param("page")PageBean page);

	/**
	 * 查询某个用户的月销售量和月销售额
	 * @param userId
	 * @return
	 */
	Map<String, Object> getYearSalByUserId(@Param("userId") Long userId);

	/**
	 * 查询某个用户的总销售量和总销售额
	 * @param userIds
	 * @return
	 */
	Map<String, Object> getAllSalByUserId(@Param("userId") Long userId);
}
