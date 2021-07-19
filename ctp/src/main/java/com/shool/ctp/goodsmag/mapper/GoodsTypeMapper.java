package com.shool.ctp.goodsmag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;

/**
 * 商品类型sql映射接口类
 * @author luolin
 * @date 2019年1月13日
 */
public interface GoodsTypeMapper {
	/**
	 * 添加商品类型
	 * @param goodsType
	 * @return
	 */
	int addGoodsType(@Param("goodsType") GoodsTypeBean goodsType);
	
	/**
	 * 修改商品类型信息
	 * @param goodsType
	 * @return
	 */
	int updateGoodsType(@Param("goodsType") GoodsTypeBean goodsType);
	
	/**
	 * 删除商品类型
	 * @param goodsType
	 * @return
	 */
	int deleteGoodsType(@Param("id")Integer id);
	
	/**
	 * 通过id查询商品类型
	 * @param id
	 * @return
	 */
	GoodsTypeBean getGoodsTypeById(@Param("id")Integer id);
	
	/**
	 * 分页条件查询的总条数
	 * @param goodsType
	 * @return
	 */
	int countGoodsTypePage(@Param("goodsType")GoodsTypeBean goodsType);
	
	/**
	 * 获取所有的商品类型
	 * @param goodsType
	 * @return
	 */
	List<?> allGoodsType(@Param("goodsType")GoodsTypeBean goodsType);
	
	/**
	 * 分页条件查询商品类型
	 * @param page
	 * @param goodsType
	 * @return
	 */
	List<?> findGoodsTypePage(@Param("page")PageBean page,@Param("goodsType")GoodsTypeBean goodsType);
	
	/**
	 * 条件查询商品类型信息
	 * @param goodsType
	 * @return
	 */
	GoodsTypeBean getGoodsTypeByGoodsTypeName(@Param("goodsType") GoodsTypeBean goodsType);
}
