<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<html>
<body>
<h2>Hello World!</h2>
<input id="text" type="text"/>
<button onclick="send()">发送消息</button>
<button onclick="closeWebSocket()">关闭WebSocket连接</button>
<button onclick="clear()">清空</button>
<hr/>
<div id="message"></div>
<script type="text/javascript">
var websocket = null;
if("WebSocket" in window){
	initSocket();
}else{
	alert('当前浏览器 Not support websocket');
}
console.log(window.location.host);

//将消息显示在网页上

function setMessageInnerHTML(innerHTML) {

    document.getElementById('message').innerHTML += innerHTML + '<br/>';

}

function closeWebSocket() {

	webSocket.close();

}


function clear(){
    document.getElementById('message').innerHTML="";
}

//发送消息

function send() {

    var message = document.getElementById('text').value;

    webSocket.send(message);
	console.log(message);
}

function initSocket(){
	webSocket = new WebSocket("ws://"+window.location.host+"<%=path%>/websoket/123456789");
	
	//连接发生错误的回调方法
    webSocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };
 
    //连接成功建立的回调方法
    webSocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
        webSocket.send("测试消息");
    }
 
    //接收到消息的回调方法
    webSocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
        console.log("我接收到信息了！");
    }
 
    //连接关闭的回调方法
    webSocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }
 	
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
 
        closeWebSocket();
 
    }
 
}

</script>
</body>
</html>
