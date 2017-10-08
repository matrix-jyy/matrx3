<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<head>
    <title>Apache Tomcat WebSocket Examples: Multiplayer Snake</title>
    <style type="text/css"> 
        #playground {
            width: 640px;
            height: 480px;
            background-color: #000;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 300px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 480px;
            overflow-y: scroll;
            padding-left: 5px;
            padding-right: 5px;
            width: 100%;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
  12312
    <script type="application/javascript"> 


    var wsPath=getRootPath();
    wsPath="ws://"+wsPath.substring(wsPath.indexOf('//')+2 ,wsPath.length)+"/websocket";
    var WebSoket = function() {

    	return {
    		// 一个ws 表示一个通讯管道
    		connection:{},
    		// 建立连接
    		initConnection : function() {
    			if ('WebSocket' in window) {
    				WebSoket.connection = new WebSocket(wsPath);
    			} else if ('MozWebSocket' in window) {
    				WebSoket.connection = new MozWebSocket(wsPath);
    			} else {
    				alert('WebSocket is not supported by this browser.');
    				return;
    			}
    			WebSoket.connection.onerror = function(event) {
    				WebSoket.onError(event)
    			};
    			WebSoket.connection.onopen = function(event) {
    				WebSoket.onOpen(event)
    			};
    			WebSoket.connection.onmessage = function(event) {
    				WebSoket.onMessage(event)
    			};
    		},

    		// 发送消息
    		sendMessage : function(msg) {
    			WebSoket.connection.send(JSON.stringify(msg));
    		},
    		onMessage : function(event) {
    			layer.msg(event.data);
    		},
    		onOpen : function(event) {
    			layer.msg('管道已连接');
    		},
    		onError : function(event) {
    			layer.msg(event.data);
    		}
    	}
    }()
       </script>
</body>
</html>