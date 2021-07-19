package com.shool.ctp.socket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 	注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @author luolin
 * @date 2019年1月25日
 */
@ServerEndpoint("/websoket/{userFlag}")
@Component
public class WebSocketTest {
	
	private static Logger logger = LoggerFactory.getLogger(WebSocketTest.class);
	
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
	
	//用来存放每个客户端对应的WebSocketTest对象，适用于同时与多个客户端通信
	private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
	
	//若要实现服务端与指定客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static ConcurrentHashMap<String, WebSocketTest> webSocketMap = new ConcurrentHashMap<String,WebSocketTest>();
	
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	//当前发消息的人员标识（用户的登陆名）
	private static String userFlag = "";
	
	/**
	 * 	连接建立成功调用的方法
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(@PathParam("userFlag") String param, Session session) {
		this.session = session;
		userFlag = param;
		webSocketSet.add(this);     //加入set中
        webSocketMap.put(userFlag,this); //加入map中
        addOnlineCount(); 
		try {
			sendMessageToUser(param,"连接成功");
			System.out.println(webSocketMap.size());
		} catch (Exception e) {
			System.out.println("websocket 异常");
		}
		
	}
	
	
	/**
	 * 连接关闭时调用的方法
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		webSocketSet.remove(this); 
		System.out.println("WebSocket被关闭了");
		if (!userFlag.equals("")) {
            webSocketSet.remove(userFlag);  //从set中删除
            subOnlineCount();           //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
	}
	
	/**
	 * 	收到客户端消息后调用的方法
	 * @param message  客户端发送过来的消息
	 * @param mySession 可选参数
	 * @throws Exception
	 */
	@OnMessage
	public void onMessage(String message) throws Exception {
		System.out.println(message);
		logger.info("来自客户端的消息:" + message);
//		this.session.getBasicRemote().sendText(message);
	}
	
	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session,Throwable error) {
		logger.info("发生错误");
//		error.printStackTrace()   打印错误信息
	}
	
	/**
	 * 给所有客户端发送消息
	 * @param message
	 * @throws Exception
	 */
	/*public void sendAllMessage(String message) throws Exception {
		this.session.getBasicRemote().sendText(message);
		System.out.println("推送成功！");
	}*/
	
	/**
	 * 定向发送消息
	 * @param mySession
	 * @param message
	 * @throws Exception
	 */
	public static void sendMessageToUser(String loginName,String message) throws Exception {
		if(webSocketMap.get(loginName) != null) {
			webSocketMap.get(loginName).session.getBasicRemote().sendText(message);
			System.out.println("推送成功！");
		} else {
			//userFlag
			webSocketMap.get(userFlag).session.getBasicRemote().sendText(loginName+"用户不在线！无法接收到消息通知！请及时联系");
			System.out.println("目标用户不在线！无法接收到消息通知！");
		}
	}
	
	/**
	* 群发自定义消息
	* */
	public static void sendInfo(String message) throws Exception {
		for (WebSocketTest item : webSocketSet) {
			try {
				item.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				System.err.println("推送失败！");
				e.printStackTrace();
			}
		}
	}
	
	
	public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }
	
}
