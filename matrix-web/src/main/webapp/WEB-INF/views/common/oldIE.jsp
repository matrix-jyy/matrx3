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
<title>抱歉无法兼容你的IE浏览器</title>
</head>
<body>
	<div class="box1">
		<h3>本站点不支持IE8以下的浏览器版本 </h3>
		<hr>
		<p>为了获得更好的浏览体验，我们强烈建议您<a href="http://browsehappy.com/">升级</a>到最新版本的IE浏览器，或者使用较新版本的
		<a href="http://browsehappy.com/"> Google Chrome、 Firefox、 Safari</a> 等。 如果您使用的是 IE 10 或以上版本，请关闭“兼容性视图”。</p>
	</div>
</body>
</html>