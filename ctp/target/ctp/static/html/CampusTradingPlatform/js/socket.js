var webSocket = null;
alertify.logPosition("bottom right");
if("WebSocket" in window){
    initSocket();
}else{
    alert('当前浏览器 Not support websocket');
}
console.log(window.location.host);

//将消息显示在网页上
/*function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

function closeWebSocket() {

    webSocket.close();

}


function clear(){
    document.getElementById('message').innerHTML="";
}*/

//发送消息
/*
function send() {
    var message = document.getElementById('text').value;
    webSocket.send(message);
}
*/

function initSocket(){
    var checkLogin = sessionStorage.getItem("userInfo");
    if(checkLogin != null){
        var userInfo = JSON.parse(checkLogin);

        webSocket = new WebSocket("ws://localhost:8080/ctp/websoket/"+userInfo.loginName);
        //连接发生错误的回调方法
        webSocket.onerror = function () {
            console.log("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        webSocket.onopen = function () {
            console.log("WebSocket连接成功");
            webSocket.send("测试消息");
        };

        //接收到消息的回调方法
        webSocket.onmessage = function (event) {
            alertify.success(event.data);
            // console.log(event.data);
        };

        //连接关闭的回调方法
        webSocket.onclose = function () {
            console.log("WebSocket连接关闭");
        };

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }
    }else{
        console.log("用户未登录，无法建立websocket");
    }

}
