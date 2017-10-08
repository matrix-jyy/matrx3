<%@page import="com.zkingsoft.constance.SystemConstance"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="matrix"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title> Matrix-Java </title>
 <link href="${path }/resource/images/" rel="SHORTCUT ICON">
<meta name="keywords" content="Matrix-Java,开发框架，快速开发框架，SpingMvc+Mybatis+Mysql SSHK框架，java学习框架，中小型企业框架"/>
<meta name="description" content="Matrix-Java  简化你的开发，让你专注于业务。Matrix-Java 是姜友瑶开发的一个实用的java快速开发框架，满足中小型项目快速搭建。
	框架自带代码生成器能够快速生成增删改查代码包括列表和表单界面。
	 集成常用的权限、用户、日志、短信验证等N多基础功能" />
<script>
	var isoldIE = false;
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0
				|| navigator.userAgent.indexOf("MSIE 7.0") > 0
				|| avigator.userAgent.indexOf("MSIE 8.0") > 0) {
			isoldIE = true;
		}
	}
	if (isoldIE) {
		window.top.location = "${path}/common/redirect/oldIE";
	}
	if (window.top !== window.self) {
		window.top.location = window.location
	};
</script>
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
	
<link href="${path }/resource/css/styleOne/login.min.css"
	rel="stylesheet">
<style type="text/css">
.layui-layer-content {
	color: #000;
}
</style>
</head>

<body class="signin" onkeypress="dologin(event)">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-7">
				<div class="signin-info">
					<div class="logopanel m-b">
						<h1>[ Matrix-Java 简化你的开发 ]</h1>
					</div>
					<div class="m-b"></div>
					<h4>
						欢迎使用 <strong>Matrix-Java v4.0框架</strong>
					</h4>
					<ul class="m-b">
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 代码，界面一键生成</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 集团化权限控制</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 集成微信、支付宝接口</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 满足App接口开发</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> N多基础功能：短信、邮件、文章、广告、留言...</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 安全</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 易用</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 架构【SpingMvc+Mybatis+Mysql】</li>
					</ul>
				</div>
			</div>
			<div class="col-sm-5">
				<form method="post" method="post" class="dataForm">
					<h2 class="no-margins text-center">登录</h2>
					<input id="account" type="text" placeholder="账户"
						class="form-control uname" /> <input id="password"
						type="password" class="form-control pword m-b" nullmsg="密码不能为空"
						placeholder="密码" /> <a href=""> </a>
					<button type="button" onclick="login()"
						class="btn btn-success btn-block">登录</button>
					<matrix:debug>
						<a type="button" href="${path }/common/debugLogin"
							class="btn btn-primary btn-block">快速体验</a>
					</matrix:debug>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">
				<matrix:copyright></matrix:copyright>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
<script type="text/javascript">
	function login() {
		if ($("#account").val() == "") {
			layer.tips('请输入账号！', '#account');
			return false;
		} else if ($("#password").val() == "") {
			layer.tips('请输入密码！', '#password');
			return false;
		}
		$.AjaxProxy({
			p : {
				suAccount : $("#account").val(),
				suPassword : $("#password").val()
			}
		}).invoke("${path}/common/dologin", function(loj) {
			MTools.redirect("${path}/" + loj.getValue("page"));
		});
	}
	function dologin(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			window.event.returnValue = false;
			login();
			return false;
		}
	}
</script>
</html>