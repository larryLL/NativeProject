package com.shool.ctp.goodsmag.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.RespData;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.goodsmag.service.IGoodsTypeService;
/**
 * 商品类型控制器
 * @author luolin
 * @date 2019年4月21日
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	@Resource
	private IGoodsTypeService goodsTypeServiceImpl;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	/**
	 * 商品类型分页查询
	 * @param goods
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	public PageBean allGoods(GoodsTypeBean goodsType,Integer page,Integer limit){
		PageBean p = new PageBean(page,limit);
		try {
			goodsTypeServiceImpl.findGoodsType(p, goodsType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			p.setCode(2);
			p.setMessage("商品类型列表获取失败！");
			return p;
		}
		p.setCode(0);
		p.setMessage("商品类型列表获取成功！");
		return p;
	}
	
	/**
	 * 商品类型新增
	 * @param goodsType
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.POST, produces = "application/json; charset=utf-8")
	public RespData addGoods(GoodsTypeBean goodsType) {
		RespData resp = new RespData();
		GoodsTypeBean check = goodsTypeServiceImpl.getGoodsTypeByGoodsTypeName(goodsType);
		if(check == null) {
			goodsType.setCreateTime(new Date());
			goodsType.setDeleteFlag(0);
			String typeNumber = "T"+System.currentTimeMillis();
			goodsType.setTypeNumber(typeNumber);
			cudOperationServiceImpl.addByHibernate(goodsType);
			resp.setStatuCode(1);
			resp.setMessage("商品类型新增成功!");
			return resp;
		}else{
			resp.setStatuCode(2);
			resp.setMessage("已有该商品类型，请重新输入！");
			return resp;
		}
	}
	
	/**
	 * 修改商品类型信息
	 * @param id
	 * @param goodsType
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.PUT},produces= {"application/json;charset=utf-8"})
	public RespData updateGoods(@PathVariable("id") Long id,GoodsTypeBean goodsType){
		RespData resp = new RespData();
		GoodsTypeBean realGoodsType = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(id, GoodsTypeBean.class);
		realGoodsType.setUpdateTime(new Date());
		if(goodsType.getTypeName() != realGoodsType.getTypeName()) {
			realGoodsType.setTypeName(goodsType.getTypeName());
		}
		if(goodsType.getTypeDetail() != realGoodsType.getTypeDetail()) {
			realGoodsType.setTypeDetail(goodsType.getTypeDetail());
		}
		try {
			cudOperationServiceImpl.updateByHibernate(realGoodsType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.setMessage("修改出错！");
			resp.setSuccess(false);
			resp.setStatuCode(2);
			return resp;
		}
		resp.setMessage("修改成功");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
	}
	
	/**
	 * 删除商品类型信息
	 * @param id
	 * @param goodsType
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.DELETE},produces= {"application/json;charset=utf-8"})
	public RespData deleteGoods(@PathVariable("id") Long id,GoodsTypeBean goodsType){
		RespData resp = new RespData();
		goodsType = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(id, GoodsTypeBean.class);
		goodsType.setDeleteFlag(1);
		
		try {
			cudOperationServiceImpl.updateByHibernate(goodsType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.setMessage("删除失败");
			resp.setSuccess(false);
			resp.setStatuCode(0);
			return resp;
		}
		resp.setMessage("删除成功");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @param goodsTypeId
	 * @return
	 */
	@RequestMapping(value="/batch/{id}",method= {RequestMethod.DELETE})
	@ResponseBody
	public RespData batchUser(@PathVariable("id") Long id,@RequestParam(value="goodsTypeId") Long[] goodsTypeId) {
		RespData resp = new RespData();
		if(id > 0) {
			GoodsTypeBean goodsType = (GoodsTypeBean)cudOperationServiceImpl.getObjectById(id, GoodsTypeBean.class);
			goodsType.setDeleteFlag(1);
			
			try {
				cudOperationServiceImpl.updateByHibernate(goodsType);
				resp.setMessage("删除成功");
				resp.setStatuCode(1);
				resp.setSuccess(true);
			} catch (Exception e) {
				// TODO: handle exception
				resp.setMessage("系统错误，删除失败！");
				resp.setStatuCode(0);
				resp.setSuccess(false);
			}
		}else if(id == 0) {
			
			System.out.println(id);
			System.out.println(goodsTypeId);
			for (Long i : goodsTypeId) {
				GoodsTypeBean goodsType = (GoodsTypeBean)cudOperationServiceImpl.getObjectById(i, GoodsTypeBean.class);
				goodsType.setDeleteFlag(1);
				try {
					cudOperationServiceImpl.updateByHibernate(goodsType);
					resp.setMessage("删除成功");
					resp.setStatuCode(1);
					resp.setSuccess(true);
				} catch (Exception e) {
					// TODO: handle exception
					resp.setMessage("系统错误，"+goodsType.getTypeName()+"删除失败！");
					resp.setStatuCode(0);
					resp.setSuccess(false);
					return resp;
				}
			}
			
		}
		
		return resp;
	}
}
