package com.shool.ctp.goodsmag.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;

/**
  * 业务层商品类型接口
 * @author luolin
 * @date 2019年1月13日
 */
public interface IGoodsTypeService {
	
	/**
	 * 添加商品类型
	 * @param goodsType
	 * @return
	 */
	int addGoodsType(GoodsTypeBean goodsType);
	
	/**
	 * 修改商品类型信息
	 * @param goodsType
	 * @return
	 */
	int updateGoodsType(GoodsTypeBean goodsType);
	
	/**
	 * 删除商品类型
	 * @param goodsType
	 * @return
	 */
	int deleteGoodsType(Integer id);
	
	/**
	 * 通过id查询商品
	 * @param id
	 * @return
	 */
	GoodsTypeBean getGoodsTypeById(Integer id);
	
	/**
	 * 获取所有的商品类型
	 * @param goodsType
	 * @return
	 */
	List<?> allGoodsType(GoodsTypeBean goodsType);
	
	/**
	 * 分页条件查询商品
	 * @param page
	 * @param goodsType
	 * @return
	 */
	void findGoodsType(PageBean page,GoodsTypeBean goodsType);
	
	/**
	 * 条件查询商品类型信息
	 * @param goodsType
	 * @return
	 */
	GoodsTypeBean getGoodsTypeByGoodsTypeName(GoodsTypeBean goodsType);
}
