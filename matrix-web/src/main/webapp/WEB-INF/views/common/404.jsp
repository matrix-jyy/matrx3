<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE>
<html>
  <head>
    <title>404</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style=" background: url('${path}/resource/images/404.jpg')no-repeat center top ;">
    	<a style="color: blue; text-align:center; line-height:40px; font-family:'Microsoft yahei'; text-decoration:none; display: block; border-radius:10px; width: 150px; height: 40px; background-color: white; 
    	cursor: pointer; margin: 0 auto; margin-top: 300px;" href="${path}">返回首页</a>
  </body>
</html>
