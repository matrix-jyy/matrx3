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


<!-- 文件上传需要的样式 -->

<link rel="stylesheet"
	href="${path }/resource/plugin/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="${path }/resource/plugin/kindeditor/plugins/code/prettify.css" />
<script src="${path }/resource/plugin/kindeditor/kindeditor.js"></script>
<script src="${path }/resource/plugin/kindeditor/lang/zh_CN.js"></script>
<script
	src="${path }/resource/plugin/kindeditor/plugins/code/prettify.js"></script>




</head>

<body>
	<div class="ibox-content">
		<blockquote class="center-block bg-success">
			<h1>超级管理员账号主页</h1>
		</blockquote>
		<blockquote class="center-block ">
			<p class="center-block">超级管理员登录后可以在本页面中管理系统公司，给每一个公司分配相应的功能以及给公司配置管理员账号。</p>
			<p class="center-block">公司管理员账号默认会拥有公司所有的功能权限</p>
		</blockquote>
	</div>
</body>
</html>