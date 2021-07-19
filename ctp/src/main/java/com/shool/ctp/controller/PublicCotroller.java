package com.shool.ctp.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shool.ctp.bean.RespData;
import com.shool.ctp.bean.UserBean;
import com.shool.ctp.cudmag.service.ICudOperationService;
import com.shool.ctp.usermag.service.IUserService;

/**
 * 公共controller
 * @author luolin
 * @date 2019年1月25日
 */
@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class PublicCotroller {
	
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private ICudOperationService cudOperationServiceImpl;
	
	private Random r = new Random();
	
	//随机字符集合中不包括0和o，O，1和l，因为这些不易区分 
	private String codes = "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYXZ";  
	private char randomChar() {  
        int index = r.nextInt(codes.length());  
        return codes.charAt(index);  
    }
	
//	public void 
	
	@RequestMapping("/main")
	public String mainPage() {
		System.out.println(1111);
		return "main";
	}
	
	/**
	 * 后台管理员主页
	 * @return
	 */
	@RequestMapping(value="/index",produces= {"text/html;charset=utf-8"})
	public String managePage() {
		System.out.println(22222);
		return "static/managelogin/manage_login_register";
	}
	
	/**
	 * 管理员登陆
	 * @param loginName
	 * @param password
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/manage/login")
	@ResponseBody
	public RespData loginCheck(String loginName,String password,String code,HttpSession session) {
		RespData resp = new RespData();
		System.out.println(!code.equalsIgnoreCase((String) session.getAttribute("randCheckCode")));
		if (code.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
			UserBean u = new UserBean();
			u.setLoginName(loginName);
			u = userServiceImpl.checkManageLoginByUser(u);
			if(u != null) {
				//验证密码
				if(u.getLoginPwd().equals(password)) {
					//验证是否拥有管理员角色
					if((u.getRole() != null) && (u.getRole().getId() == 1) ) {
						u = (UserBean) cudOperationServiceImpl.getObjectById(u.getId(), UserBean.class);
						u.setLoginTime(new Date());
						cudOperationServiceImpl.updateByHibernate(u);
						
						resp.setSuccess(true);
						resp.setStatuCode(1);
						resp.setId(u.getId().intValue());
						resp.setObj(u);
						resp.setMessage("请稍后......");
						return resp;
					}else {
						resp.setSuccess(false);
						resp.setMessage("你没有管理员角色，无权登陆！");
						resp.setStatuCode(4);
						return resp;
					}
					
				}else {
					resp.setSuccess(false);
					resp.setMessage("密码错误！");
					resp.setStatuCode(3);
					return resp;
				}
			}else {
				resp.setSuccess(false);
				resp.setMessage("没有该用户！");
				resp.setStatuCode(2);
				return resp;
			}
			/*System.out.println(session.getAttribute("randCheckCode"));
			return "0";*/
		}else {
			resp.setSuccess(false);
			resp.setMessage("验证码错误");
			resp.setStatuCode(0);
			return resp;
		}
		
	}
	
	/**
	 * 验证码
	 * @param response
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/pictureCheckCode",produces= {"image/jpeg;charset=utf-8"})
	public void pictureCheckCode(HttpServletResponse response,HttpServletRequest request,Model model) {
		// 设置不缓存图片
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setDateHeader("Expires", 0);
				
				// 指定生成的响应图片,一定不能缺少这句话,否则错误.
				response.setContentType("image/jpeg");
				int width = 80, height = 35; // 指定生成验证码的宽度和高度
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage对象,其作用相当于一图片
				Graphics g = image.getGraphics(); // 创建Graphics对象,其作用相当于画笔
				Graphics2D g2d = (Graphics2D) g; // 创建Grapchics2D对象
				
				Random random = new Random();
				Font mfont = new Font("楷体", Font.BOLD, 16); // 定义字体样式
				g.setColor(getRandColor(200, 250));
				g.fillRect(0, 0, width, height); // 绘制背景
				g.setFont(mfont); // 设置字体
				g.setColor(getRandColor(180, 200));
		 
				// 绘制100条颜色和位置全部为随机产生的线条,该线条为2f
				for (int i = 0; i < 100; i++) {
					int x = random.nextInt(width - 1);
					int y = random.nextInt(height - 1);
					int x1 = random.nextInt(6) + 1;
					int y1 = random.nextInt(12) + 1;
					BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 定制线条样式
					Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
					g2d.setStroke(bs);
					g2d.draw(line); // 绘制直线
				}
				
				
				//用来保存验证码字符串文本内容  
		        StringBuilder sb = new StringBuilder();  
		        
				for (int i = 0; i < 4; ++i) {// 随机生成4个字符  
					String sTemp = String.valueOf(randomChar());
		            sb.append(sTemp);  
		            
		            Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
					g.setColor(color);
					// 将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
					
					/* 将文字旋转制定角度 */
					Graphics2D g2d_word = (Graphics2D) g;
					AffineTransform trans = new AffineTransform();
					trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);
					
					/* 缩放文字 */
					float scaleSize = random.nextFloat() + 0.8f;
					if (scaleSize > 1f)
						scaleSize = 1f;
					trans.scale(scaleSize, scaleSize);
					g2d_word.setTransform(trans);
					g.drawString(sTemp, 15 * i + 18, 14);
				}
				
				HttpSession session = request.getSession(true);
				session.setAttribute("randCheckCode", sb.toString());
				System.out.println("sRand="+sb.toString());
				g.dispose(); // 释放g所占用的系统资源
				try {
					ImageIO.write(image, "JPEG", response.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 输出图片

	}
	
	/* 该方法主要作用是获得随机生成的颜色 */
	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
		g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
		b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}
	
	
	/**
	 * 用户登陆
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public RespData userLogin(String loginName,String loginPassword) {
		RespData resp = new RespData();
		UserBean u = new UserBean();
		u.setLoginName(loginName);

		u = userServiceImpl.checkManageLoginByUser(u);
		if(u != null) {
			//验证密码
			if(u.getLoginPwd().equals(loginPassword)) {
				//验证是否拥有管理员角色
				if((u.getRole() != null) && (u.getRole().getId() == 2) ) {
					u = (UserBean) cudOperationServiceImpl.getObjectById(u.getId(), UserBean.class);
					u.setLoginTime(new Date());
					cudOperationServiceImpl.updateByHibernate(u);
					
					resp.setStatuCode(1);
					resp.setObj(u);
					resp.setSuccess(true);
					resp.setTotal(1);
					
					//用户登陆成功
					return resp;
				}else {
					//你没有用户角色
					resp.setStatuCode(3);
					return resp;
				}
				
			}else {
				//密码错误
				resp.setStatuCode(0);
				return resp;
			}
		}else {
			//没有该账号，请注册！
			resp.setStatuCode(2);
			return resp;
		}
	}
}
