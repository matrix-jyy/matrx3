<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib tagdir="/WEB-INF/tags" prefix="matrix"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>Matrix</title>
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <c:if
									test="${user.suPhoto eq null or user.suPhoto eq ''}">
									<img src="${path}/resource/images/profile_small.jpg" width="64" height="64"
										alt="image" class="img-circle" />
								</c:if> <c:if test="${user.suPhoto ne null and user.suPhoto ne ''}">
									<img width="64" height="64" src="${user.suPhoto}" alt="image" class="img-circle" />
								</c:if> </span> <a
								data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
								class="clear" style="height: auto;"> <span
									class="block m-t-xs"><strong class="font-bold">${ user.suName }</strong></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li class="divider"></li>
								<li><a href="${path }/common/loginOut">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">Matrix</div>
					</li>
					<li><c:forEach var="item" items="${MATRIX.menusFunction[user.suId] }">
							<li><a href="#"> <i class="${item.fnIcon }"></i> <span
									class="nav-label">${item.fnName}</span> <span class="fa arrow"></span>
							</a>
								<ul class="nav nav-second-level">
									<c:forEach items="${item.childs}" var="childen">
										<li><a class="J_menuItem"
											href="${path }/admin/redirect/${childen.fnPath}"><i
												class="${childen.fnIcon}"></i> ${childen.fnName}</a></li>
									</c:forEach>
								</ul></li>
						</c:forEach></li>
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2  "
							href="#"><i class="fa fa-bars"></i></a> <span
							class="welcomtitle">欢迎使用Matrix</span>
					</div>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"
							data-id="index_v1.html">首页</a>
					</div>
				</nav>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${path }/common/loginOut"
					class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${path }/admin/redirect/welcome" frameborder="0"
					data-id="index_v1.html" seamless></iframe>
			</div>
			<div class="footer">
				<div style="color: #676a6c;" class="pull-right"><matrix:copyright></matrix:copyright></div>
			</div>
		</div>
		<!--右侧部分结束-->
		<!--右侧边栏开始-->
		<div id="right-sidebar">
			<div class="sidebar-container"></div>
		</div>
		<!--右侧边栏结束-->
	</div>
</body>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
</html>