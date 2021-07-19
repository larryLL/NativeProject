package com.shool.ctp.goodsmag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.PageBean;

/**
 * 商品sql语句映射接口
 * @author luolin
 * @date 2019年1月12日
 */
public interface GoodsMapper {
	/**
	 * 发布商品
	 * @param goods
	 * @return
	 */
	int addGoods(@Param("goods")GoodsBean goods);
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	int updateGoods(@Param("goods")GoodsBean goods);
	
	/**
	 * 删除商品（修改标记）
	 * @param id
	 * @return
	 */
	int deleteGoods(@Param("id") Integer id);
	
	/**
	 * 通过id获取商品
	 * @param id
	 * @return
	 */
	GoodsBean getGoodsById(@Param("id") Integer id);
	
	/**
	 * 检查是否有重复名称的商品
	 * @param goodsName
	 * @return
	 */
	int checkGoodsByGoodsName(@Param("goodsName")String goodsName);
	
	/**
	 * 分页条件查询商品的结果总条数
	 * @param goods
	 * @return
	 */
	int countGoodsBeanReturnPage(@Param("goods") GoodsBean goods);
	
	/**
	 * 分页条件查询商品
	 * @param page
	 * @param goods
	 * @return
	 */
	List<?> findGoodsBeanReturnPage(@Param("page") PageBean page,@Param("goods")GoodsBean goods);
	
	/**
	 * 根据商品名称查询商品信息
	 * @param goodsNumber
	 * @return
	 */
	GoodsBean selectGoodsByGoodsNumber(@Param("goodsNumber") String goodsNumber);
	
	/**
	 * 查询用户发布的商品
	 * @param id
	 * @return
	 */
	List<GoodsBean> getGoodsByUserId(@Param("id")Long id);
	
	/**
	 * 获取用户出售的商品数量
	 * @param id
	 * @return
	 */
	int getSaleCountByUserId(@Param("userid") Long id);
	
	/**
	 * 获取用户删除的商品数量
	 * @param id
	 * @return
	 */
	int getDeleteCountByUserId(@Param("userid") Long id);
}
