<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>主页</title>
<link rel="shortcut icon" href="favicon.ico">
<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>


</head>

<body>
	<div class="ibox-content">
		<blockquote class="center-block bg-primary">
			<h1>开发者账号主页</h1>
		</blockquote>
		<blockquote class="center-block ">
			<p class="center-block">开发者登录后可以在本页面中管理系统的功能 ，配置功能的按钮。</p>
			<p class="center-block">功能可以分配给不同的企业</p>
			<p class="center-block">每个功能有多个按钮，这些功能和按钮都会被权限管理起来</p>
		</blockquote>
	</div>
</body>
</html>