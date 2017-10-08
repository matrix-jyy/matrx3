<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isErrorPage="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="matrix"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>

<head>
<style type="text/css">
.box1 {
	margin: 0 auto;
	text-align: center;
	font-family: 'Microsoft yahei';
}
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Matrix - 500错误</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>
<body
	style=" background: url('${path}/resource/images/500.jpg')no-repeat center top ;">
	<div class="box1">
		<h3>There is a problem with the page you visit</h3>
		<h1>500</h1>
	</div>
	<a
		style="color: white; text-align: center; line-height: 40px; font-family: 'Microsoft yahei'; text-decoration: none; display: block; border-radius: 10px; width: 150px; height: 40px; background-color: rgba(2, 0, 1, 0.77); cursor: pointer; margin: 0 auto; margin-top: 350px;"
		href="${path}">返回首页</a>
</body>
	<c:set var="exception"  value="<%=exception.toString()%>" ></c:set>
	<div style="color: red; width: 600px;" class="box1">
		<br> <br> <br>
		<matrix:debug>${exception}</matrix:debug>
	</div>

</html>