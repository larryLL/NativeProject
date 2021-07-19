package com.shool.ctp.goodsmag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.PageBean;

/**
 * 持久层商品接口类
 * @author luolin
 * @date 2019年1月12日
 */
public interface IGoodsDao {
	/**
	 * 发布商品
	 * @param goods
	 * @return
	 */
	int addGoods(GoodsBean goods);
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	int updateGoods(GoodsBean goods);
	
	/**
	 * 删除商品（修改标记）
	 * @param id
	 * @return
	 */
	int deleteGoods(Integer id);
	
	/**
	 * 通过id获取商品
	 * @param id
	 * @return
	 */
	GoodsBean getGoodsById(Integer id);
	
	/**
	 * 检查是否有重复名称的商品
	 * @param goodsName
	 * @return
	 */
	int checkGoodsByGoodsName(String goodsName);
	
	/**
	 * 分页条件查询商品的结果总条数
	 * @param goods
	 * @return
	 */
	int countGoodsBeanReturnPage(GoodsBean goods);
	
	/**
	 * 分页条件查询商品
	 * @param page
	 * @param goods
	 * @return
	 */
	List<?> findGoodsBeanReturnPage(PageBean page,GoodsBean goods);
	
	/**
	 * 根据商品名称查询商品信息
	 * @param goodsNumber
	 * @return
	 */
	GoodsBean selectGoodsByGoodsNumber(String goodsNumber);
	
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
	int getSaleCountByUserId(Long id);
	
	/**
	 * 获取用户删除的商品数量
	 * @param id
	 * @return
	 */
	int getDeleteCountByUserId(Long id);
}
