package com.shool.ctp.ordermag.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shool.ctp.bean.GoodsBean;
import com.shool.ctp.bean.OrderBean;
import com.shool.ctp.bean.PageBean;
import com.shool.ctp.bean.RespData;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.bean.UtilBean.OrderRequestParam;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.goodsmag.service.IGoodsService;
import com.shool.ctp.ordermag.service.IOrderService;
import com.shool.ctp.socket.WebSocketTest;
import com.shool.ctp.usermag.service.IUserService;

/**
 * 订单控制类
 * @author luolin
 * @date 2019年2月10日
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/order")
public class OrderController {
	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	@Resource
	private IOrderService orderServiceImpl;
	
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private IGoodsService goodsServicImpl;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.POST},produces= {"application/json;charset=utf-8"})
	private RespData addOrder(OrderRequestParam orderParam) {
		OrderBean order = new OrderBean();
		GoodsBean goods = null;
		UserBean buyer = null;
		RespData resp = new RespData();
		Long id = goodsServicImpl.selectGoodsByGoodsNumber(orderParam.getGoodsNumber()).getId();
		goods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
		buyer = (UserBean) cudOperationServiceImpl.getObjectById(orderParam.getBuyerId(), UserBean.class);
		if(0 == goods.getGoodsQuantity()) {
			resp.setSuccess(false);
			resp.setStatuCode(1);
			resp.setMessage("“"+goods.getGoodsName()+"”商品下单失败！您来晚了，已经出售完了！");
			return resp;
		}
		
		if(null != goods && null != buyer) {
			order.setBuyersName(buyer.getUserName());
			order.setBuyQuantity(orderParam.getBayNumber());
			order.setGoodsPrice(goods.getGoodsPrice());
			order.setGoodsName(goods.getGoodsName());
			order.setGoodsNumber(goods.getGoodsNumber());
			order.setCreateTime(new Date());
			order.setConsigneeAddress(goods.getTradingPlace());
			order.setCommodityPrice(goods.getGoodsPrice()*orderParam.getBayNumber());
			order.setDeleteFlag(0);
			order.setBuyersName(buyer.getUserName());
			order.setVendorName(goods.getUser().getUserName());
			order.setTelephoneNumber(buyer.getTelephone());
			order.setOrderNumber(String.valueOf("O"+System.currentTimeMillis()+"-"+goods.getGoodsNumber()));
			order.setOrderStatus(1);
			order.setGoodsImage(goods.getImage());
			order.setBuyer(buyer);
			order.setVendor(goods.getUser());
			System.out.println(order);
			try {
				cudOperationServiceImpl.addByHibernate(order);
				goods.setGoodsSurplus(goods.getGoodsSurplus()-1);
				cudOperationServiceImpl.updateByHibernate(goods);
				WebSocketTest.sendMessageToUser(goods.getUser().getLoginName(), "您的商品已经有人下单了，快去看看吧！");
			} catch (Exception e) {
				e.printStackTrace();
				resp.setMessage("系统错误！“"+buyer.getUserName()+"”用户,对“"+goods.getGoodsName()+"”商品下单失败！请联系管理员！");
				resp.setSuccess(false);
				resp.setStatuCode(0);
				return resp;
				// TODO: handle exception
			}
		}
		resp.setMessage("下单成功！");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
	}
	
	/**
	 * 批量添加订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/{id}/batch",method= {RequestMethod.POST},produces= {"application/json;charset=utf-8"})
	private RespData addBatchOrder(@RequestBody List<OrderRequestParam> orderParam) {
		OrderBean order = new OrderBean();
		GoodsBean goods = null;
		UserBean buyer = null;
		RespData resp = new RespData();
		
			for (OrderRequestParam orderRequestParam : orderParam) {
				Long id = goodsServicImpl.selectGoodsByGoodsNumber(orderRequestParam.getGoodsNumber()).getId();
				goods = (GoodsBean) cudOperationServiceImpl.getObjectById(id, GoodsBean.class);
				buyer = (UserBean) cudOperationServiceImpl.getObjectById(orderRequestParam.getBuyerId(), UserBean.class);
				if(0 == goods.getGoodsQuantity()) {
					resp.setSuccess(false);
					resp.setStatuCode(1);
					resp.setMessage("“"+goods.getGoodsName()+"”商品下单失败！您来晚了，已经出售完了！");
					return resp;
				}
				
				if(null != goods && null != buyer) {
					order.setBuyersName(buyer.getUserName());
					order.setBuyQuantity(orderRequestParam.getBayNumber());
					order.setGoodsPrice(goods.getGoodsPrice());
					order.setGoodsName(goods.getGoodsName());
					order.setGoodsNumber(goods.getGoodsNumber());
					order.setCreateTime(new Date());
					order.setConsigneeAddress(goods.getTradingPlace());
					order.setCommodityPrice(goods.getGoodsPrice()*orderRequestParam.getBayNumber());
					order.setDeleteFlag(0);
					order.setBuyersName(buyer.getUserName());
					order.setVendorName(goods.getUser().getUserName());
					order.setTelephoneNumber(buyer.getTelephone());
					order.setOrderNumber(String.valueOf("O"+System.currentTimeMillis()+"-"+goods.getGoodsNumber()));
					order.setOrderStatus(1);
					order.setGoodsImage(goods.getImage());
					order.setBuyer(buyer);
					order.setVendor(goods.getUser());
					System.out.println(order);
					try {
						cudOperationServiceImpl.addByHibernate(order);
						goods.setGoodsSurplus(goods.getGoodsSurplus()-1);
						cudOperationServiceImpl.updateByHibernate(goods);
						WebSocketTest.sendMessageToUser(goods.getUser().getLoginName(), "您的商品已经有人下单了，快去看看吧！");
					} catch (Exception e) {
						e.printStackTrace();
						resp.setMessage("系统错误！“"+buyer.getUserName()+"”用户,对“"+goods.getGoodsName()+"”商品下单失败！请联系管理员！");
						resp.setSuccess(false);
						resp.setStatuCode(0);
						return resp;
						// TODO: handle exception
					}
				}
				
			}
		
		resp.setMessage("下单成功！");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
		
	}
	
	/**
	 * 获取订单信息
	 * @param order
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.GET},produces= {"application/json;charset=utf-8"})
	public PageBean allOrder(OrderBean order,@RequestParam("page") Integer currentPage,Integer limit){
		PageBean page = new PageBean(currentPage,limit);
		try {
			orderServiceImpl.findOrderByOrder(page, order);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			page.setCode(0);
			page.setMessage("数据获取失败！");
			return page;
		}
		page.setCode(1);
		page.setMessage("正在获取数据......");
//		Map all = orderServiceImpl
		return page;
	}
	
	/**
	 * 删除订单
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.DELETE},produces= {"application/json;charset=utf-8"})
	public RespData deleteOrder(@PathVariable("id") Long id,OrderBean order){
		RespData resp = new RespData();
		order = (OrderBean) cudOperationServiceImpl.getObjectById(id, OrderBean.class);
		order.setDeleteTime(new Date());
		order.setDeleteFlag(1);
		cudOperationServiceImpl.updateByHibernate(order);
		resp.setMessage("删除成功");
		resp.setSuccess(true);
		resp.setStatuCode(1);
		return resp;
	}
	
	/**
	 * 修改订单
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/{id}",method= {RequestMethod.PUT},produces= {"application/json;charset=utf-8"})
	public RespData updateOrder(@PathVariable("id") Long id,OrderBean order){
		RespData resp = new RespData();
		OrderBean realOrder = (OrderBean) cudOperationServiceImpl.getObjectById(id, OrderBean.class);
		//订单状态
		if(order.getOrderStatus() != null) {
			realOrder.setOrderStatus(order.getOrderStatus());
		}
		try {
			Long goodsId = goodsServicImpl.selectGoodsByGoodsNumber(realOrder.getGoodsNumber()).getId();
			GoodsBean goods = (GoodsBean) cudOperationServiceImpl.getObjectById(goodsId, GoodsBean.class);
			cudOperationServiceImpl.updateByHibernate(realOrder);
			if(4 == order.getOrderStatus()) {
				if(goods.getGoodsQuantity() == (goods.getSaleQuantity()+realOrder.getBuyQuantity())) {
					goods.setGoodsStatus(3);
				}
				goods.setSaleQuantity(goods.getSaleQuantity()+realOrder.getBuyQuantity());
				cudOperationServiceImpl.updateByHibernate(goods);
				goods = (GoodsBean) cudOperationServiceImpl.getObjectById(goodsId, GoodsBean.class);
				resp.setMessage("商品：“"+realOrder.getGoodsName()+"”，交易成功！");
			}else if(3 == order.getOrderStatus()) {
				goods.setGoodsSurplus(goods.getGoodsSurplus()+1);
				cudOperationServiceImpl.updateByHibernate(goods);
				resp.setMessage("商品：“"+realOrder.getGoodsName()+"”，订单取消成功！");
			}else if(2 == order.getOrderStatus()) {
				resp.setMessage("商品：“"+realOrder.getGoodsName()+"”，进入交易过程！");
			}else if(1 == order.getOrderStatus()){
				resp.setMessage("商品：“"+realOrder.getGoodsName()+"”，稍后进行交易！");
			}else {
				resp.setMessage("商品：“"+realOrder.getGoodsName()+"”，状态操作成功！");
			}
			resp.setSuccess(true);
			resp.setStatuCode(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.setMessage("操作出错！请联系管理员！");
			resp.setStatuCode(0);
			return resp;
		}
		return resp;
	}
	
	
}
