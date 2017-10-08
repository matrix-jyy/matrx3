<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" >
<title>Testing websockets</title> 
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script></head>
<body style="text-align: center;">
		<table>
			<tr>
				<td>webSocketUrl</td>
				<td><input id="url" value="ws://127.0.0.1:8080/matrix-web/websocket" ></td>
				<td><input type="button" value="开启连接" onclick="connect()" /></td>
			</tr>
			<tr>
				<td>发送内容</td>
				<td><input id="msg" value="{'type':'newOrder'}" ></td>
				<td><input type="button" value="发送" onclick="sendMessage()" /></td>
			</tr>
		</table>
	<div id="messages">
	
	</div>
	<script type="text/javascript">
	
		//	一个ws 表示一个通讯管道
		var webSocket;
		
		function connect(){
			var url=$("#url").val();
			 if ('WebSocket' in window) {
				 webSocket = new WebSocket(url);
	         } else if ('MozWebSocket' in window) {
	        	 webSocket = new MozWebSocket(url);
	         } else {
	             alert('WebSocket is not supported by this browser.');
	             return;
	         }
				webSocket.onerror = function(event) {
					onError(event)
				};
				webSocket.onopen = function(event) {
					onOpen(event)
				};
				webSocket.onmessage = function(event) {
					onMessage(event)
				};
		}
		//发送消息
		function  sendMessage(){
			var value= $("#msg").val();
			webSocket.send(value);
		}
		function onMessage(event) {
			document.getElementById('messages').innerHTML 
				+= '<br />' + event.data;
		}
		function onOpen(event) {
			document.getElementById('messages').innerHTML 
				= '管道已连接。。。。';
		}
		function onError(event) {
			console.error("websoket链接错误");
			console.error(event);
		}
	</script>
</body>
</html>