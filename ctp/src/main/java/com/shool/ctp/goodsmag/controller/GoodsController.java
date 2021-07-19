package com.shool.ctp.goodsmag.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.GoodsTypeBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.RespData;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.goodsmag.service.IGoodsService;
import com.shool.ctp.goodsmag.service.IGoodsTypeService;
import com.shool.ctp.socket.WebSocketTest;

/**
 * 商品控制类
 * @author luolin
 * @date 2019年2月9日
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	@Resource
	private IGoodsService goodsServiceImpl;
	
	@Resource
	private IGoodsTypeService goodsTypeServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 新增商品
	 * @param goods
	 * @param goodsTypeId
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.POST, produces = "application/json; charset=utf-8")
	public RespData addGoods(@RequestParam(value="file",required = false)MultipartFile file,@PathVariable Long id,GoodsBean goods,Long goodsTypeId,Long userId,String save) {
		RespData resp = new RespData();
		System.out.println(save);
		if(!"save".equals(save)) {
			System.out.println(111);
			if(goodsServiceImpl.checkGoodsByGoodsName(goods.getGoodsName()) > 0) {
				resp.setMessage("已有该商品名称！");
				resp.setSuccess(false);
				resp.setStatuCode(2);
				return resp;
			}
			long times = System.currentTimeMillis();
			goods.setGoodsNumber(String.valueOf("G"+times));
			goods.setGoodsStatus(1);
			goods.setCreateTime(new Date());
			goods.setDeleteFlag(0);
			goods.setSaleQuantity(0);
			goods.setGoodsSurplus(goods.getGoodsQuantity());
			
			GoodsTypeBean goodsType = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(goodsTypeId, GoodsTypeBean.class);
			UserBean user = (UserBean) cudOperationServiceImpl.getObjectById(userId, UserBean.class);
			
			goods.setType(goodsType);
			goods.setUser(user);
			
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			
			fileName = "GI"+times+fileType;
			String path = "E:\\CampusTradingPlatform\\img\\items\\"+fileName;
			
			try {
				File f = new File(path);
				
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				
				goods.setImage(fileName);
				cudOperationServiceImpl.addByHibernate(goods);
				//上传到指定路径
				file.transferTo(f);
				System.out.println(path);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(path);
				resp.setMessage("上传文件出错！");
				resp.setSuccess(false);
				resp.setStatuCode(10);
				return resp;
			}
			
			System.out.println(goods);
			System.out.println(fileName);
			System.out.println("goodsTypeId:"+goodsTypeId);
			System.out.println("userId:"+userId);
			
			resp.setMessage("等待审核！");
			resp.setSuccess(true);
			resp.setStatuCode(1);
			return resp;
		}else {
			System.out.println(222);
			GoodsBean realGoods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
			realGoods.setUpdateTime(new Date());
			
			if(realGoods.getGoodsName() != goods.getGoodsName()) {
				realGoods.setGoodsName(goods.getGoodsName());
			}
			if(realGoods.getGoodsDetail() != goods.getGoodsDetail()) {
				realGoods.setGoodsDetail(goods.getGoodsDetail());
			}
			if(realGoods.getGoodsDegree() != goods.getGoodsDegree()) {
				realGoods.setGoodsDegree(goods.getGoodsDegree());
			}
			if(realGoods.getGoodsOriginalPrice() != goods.getGoodsOriginalPrice()) {
				realGoods.setGoodsOriginalPrice(goods.getGoodsOriginalPrice());
			}
			if(realGoods.getGoodsPrice() != goods.getGoodsPrice()) {
				realGoods.setGoodsPrice(goods.getGoodsPrice());
			}
			if(realGoods.getGoodsQuantity() != goods.getGoodsQuantity()) {
				realGoods.setGoodsQuantity(goods.getGoodsQuantity());
			}
			if(realGoods.getTradingPlace() != goods.getTradingPlace()) {
				realGoods.setTradingPlace(goods.getTradingPlace());
			}
			if(realGoods.getType().getId() != goodsTypeId) {
				GoodsTypeBean goodsType = (GoodsTypeBean) cudOperationServiceImpl.getObjectById(goodsTypeId, GoodsTypeBean.class);
				realGoods.setType(goodsType);
			}
			
			realGoods.setUpdateTime(new Date());
			
			String fileName = file.getOriginalFilename();
			String path = "E:\\CampusTradingPlatform\\img\\items\\";
			System.out.println(fileName);
			if("" == fileName || null == fileName) {
				cudOperationServiceImpl.updateByHibernate(realGoods);
				System.out.println(realGoods);
				resp.setStatuCode(1);
				resp.setMessage("保存成功!");
				resp.setObj(realGoods);
				System.out.println("没有文件操作");
				return resp;
			}else {
				String imgPath = path+realGoods.getImage();
				File img = new File(imgPath);
				if(img.exists()) {
					img.delete();
					System.out.println("删除成功");
				}
			}
			
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			long currentTime = System.currentTimeMillis();
			System.out.println(fileType);
			fileName = "GI"+currentTime+fileType;
			path = path + fileName;
			try {
				File f = new File(path);
				
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				System.out.println(realGoods);
				
				realGoods.setImage(fileName);
				
				cudOperationServiceImpl.updateByHibernate(realGoods);
				//上传到指定路径
				file.transferTo(f);
				resp.setStatuCode(1);
				resp.setMessage("保存成功!");
				resp.setSuccess(true);
				resp.setObj(realGoods);
				System.out.println(path);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(path);
				resp.setMessage("图片上传出错！");
				resp.setSuccess(false);
				resp.setStatuCode(10);
				return resp;
			}
			
			System.out.println("有文件");
			
		}
		return resp;
		
	}
	
	/**
	 * 获取所有的商品
	 * @param goods
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	public PageBean allGoods(GoodsBean goods,Integer page,Integer limit,String orderName,String orderType,Long typeId){
		PageBean p = null;
		if(0 == goods.getId()) {
			if(null!=typeId || "".equals(typeId)) {
				GoodsTypeBean type = new GoodsTypeBean();
				type.setId(typeId);
				goods.setType(type);
			}
			try {
				p = new PageBean(page,limit);
				if(orderName!=null&&orderName != "") {
					p.setOrderName(orderName);
					p.setOrderType(orderType);
				}
				goodsServiceImpl.findGoodsBeanReturnPage(p, goods);
				System.out.println(p);
				System.out.println(goods);
			} catch (Exception e) {
				// TODO: handle exception
				p.setCode(2);
				p.setMessage("数据获取失败！");
				return p;
			}
			p.setCode(0);
			p.setMessage("数据获取成功！");
			System.out.println(p);
			return p;
		}else {
			p = new PageBean();
			try {
				GoodsBean g = (GoodsBean) cudOperationServiceImpl.getObjectById(goods.getId(), GoodsBean.class);
				p.setObj(g);
			} catch (Exception e) {
				// TODO: handle exception
				p.setCode(0);
				p.setMessage("获取失败！");
				return p;
			}
			p.setCode(1);
			p.setMessage("获取成功！");
		}
		return p;
	}
	
	/**
	 * 删除商品
	 * @param id
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.DELETE},produces= {"application/json;charset=utf-8"})
	public RespData deleteGoods(@PathVariable("id") Long id,GoodsBean goods){
		RespData resp = new RespData();
		goods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
		goods.setDeleteTime(new Date());
		goods.setDeleteFlag(1);
		
		try {
			cudOperationServiceImpl.updateByHibernate(goods);
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
	 * 修改商品信息
	 * @param id
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.PUT},produces= {"application/json;charset=utf-8"})
	public RespData updateGoods(@PathVariable("id") Long id,GoodsBean goods,String userFlag){
		RespData resp = new RespData();
		GoodsBean realGoods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
		realGoods.setUpdateTime(new Date());
		if(goods.getGoodsName() != null) {
			realGoods.setGoodsName(goods.getGoodsName());
		}
		if(goods.getGoodsQuantity() != null) {
			realGoods.setGoodsQuantity(goods.getGoodsQuantity());
		}
		if(goods.getGoodsPrice() != null) {
			realGoods.setGoodsPrice(goods.getGoodsPrice());
		}
		if(goods.getGoodsDetail() != null) {
			realGoods.setGoodsDetail(goods.getGoodsDetail());
		}
		if(goods.getGoodsDegree() != null ) {
			realGoods.setGoodsDegree(goods.getGoodsDegree());
		}
		//商品状态
		if(goods.getGoodsStatus() != null) {
			realGoods.setGoodsStatus(goods.getGoodsStatus());
			realGoods.setRegisterTime(new Date());
			cudOperationServiceImpl.updateByHibernate(realGoods);
			if(goods.getGoodsStatus() == 2) {
				try {
					System.err.println(realGoods);
					WebSocketTest.sendMessageToUser(userFlag,"你的申请的“"+realGoods.getGoodsName()+"”商品已经审核成功！并已经发布！");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("消息发送失败！");
				}
				resp.setMessage("已审核");
				resp.setSuccess(true);
				resp.setStatuCode(2);
				return resp;
			}else if(goods.getGoodsStatus() == 4) {
				resp.setMessage("商品已下架");
				resp.setSuccess(true);
				resp.setStatuCode(4);
				return resp;
			}else {
				resp.setMessage("其他商品操作");
				resp.setSuccess(true);
				resp.setStatuCode(10);
				return resp;
			}
			
		}
		cudOperationServiceImpl.updateByHibernate(realGoods);
		resp.setMessage("修改成功");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
	}
	
	/**
	 * 获取所有的商品类型
	 * @param goodsType
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/type/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	public RespData allGoodsType(GoodsTypeBean goodsType){
//		PageBean page = new PageBean(currentPage,limit);
		
		RespData resp = new RespData();
		List<?> datas = goodsTypeServiceImpl.allGoodsType(goodsType);
		if(datas.size()>0) {
			resp.setRows(datas);
			resp.setStatuCode(1);
			resp.setSuccess(true);
			resp.setTotal(datas.size());
			return resp;
		}
		resp.setStatuCode(0);
		resp.setSuccess(false);
		resp.setTotal(0);
		resp.setMessage("无数据");
		return resp;
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value="/batch/{id}",method= {RequestMethod.DELETE})
	@ResponseBody
	public RespData batchUser(@PathVariable("id") Long id,@RequestParam(value="goodsId") Long[] goodsId) {
		RespData resp = new RespData();
		if(id > 0) {
			GoodsBean goods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
			goods.setDeleteTime(new Date());
			goods.setDeleteFlag(1);
			try {
				cudOperationServiceImpl.updateByHibernate(goods);
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
			System.out.println(goodsId);
			for (Long i : goodsId) {
				GoodsBean goods = (GoodsBean) cudOperationServiceImpl.getObjectById(i, GoodsBean.class);
				goods.setDeleteTime(new Date());
				goods.setDeleteFlag(1);
				try {
					cudOperationServiceImpl.updateByHibernate(goods);
					resp.setMessage("删除成功");
					resp.setStatuCode(1);
					resp.setSuccess(true);
				} catch (Exception e) {
					// TODO: handle exception
					resp.setMessage("系统错误，"+goods.getGoodsName()+"删除失败！");
					resp.setStatuCode(0);
					resp.setSuccess(false);
					return resp;
				}
			}
		}
		
		return resp;
	}
}
