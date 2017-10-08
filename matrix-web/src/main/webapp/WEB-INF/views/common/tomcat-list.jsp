<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
<!-- 界面单独引入的其他样式和脚本 -->
<script type="text/javascript"
	src="${path }/resource/js/function/public.js"></script>
<style type="text/css">
.console {
	overflow: scroll;
	max-height: 700px;
	background: #fff;
}

#treeDemo li:HOVER {
	background: #efefef;
	color: #339933;
	cursor: pointer;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row text-center">
			<img alt="" class="pd-20"
				src="http://www.zkingsoft.com/resource/images/logo.png">
			<h1>项目发布工具</h1>
		</div>

		<div class="row">
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Tomcat列表</h5>
						<div class="pull-right ">
							<a class="f-r text-success " onclick="addTomcat()"><i
								class="fa fa-plus c-success"></i> 添加Tomcat</a>
						</div>
					</div>
					<div id="treeDemo" class="ibox-content " style="min-height: 400px">
						<ul class="list-group">
							<li class="list-group-item">财务项目-tomcat-8200</li>
							<li class="list-group-item">财务项目-tomcat-8200</li>
							<li class="list-group-item">财务项目-tomcat-8200</li>
							<li class="list-group-item">财务项目-tomcat-8200</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>管理Tomcat</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content" style="min-height: 400px">
						<form style="display:;" class="form-horizontal dataform "
							action="" method="post" enctype="multipart/form-data"
							id="dataform">
							<div class="form-group">
								<input type="hidden" name="artTypeId" id="id"> <label
									class="col-sm-2 control-label">备注</label>
								<div class="col-sm-3">
									<input name="remark" type="text" class="form-control">
								</div>

								<label class="col-sm-2 control-label">端口号</label>
								<div class="col-sm-3">
									<input type="text" class="form-control " readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">上传war包</label>
								<div class="col-sm-8">
									<input id="icon" name="warFile" type="file"
										style="width: 60%; float: left;" class="form-control ">
									<button type="button" style="float: left;"
										class="btn btn-success">上传</button>
								</div>
							</div>

						</form>
					</div>
					<div class="panel-footer">
						<div class="col-sm-12 text-center">
							<button type="button" class="btn btn-primary">
								<i class="fa fa-check"></i>更新信息
							</button>
							<button type="button" class="btn btn-success">
								<i class="fa fa-play"></i>启动
							</button>
							<button type="button" class="btn btn-danger">
								<i class="fa  fa-stop"></i>停止
							</button>
							<button type="button" class="btn btn-warning">
								<i class="fa fa-trash"></i>清空webapps
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="wrapper wrapper-content">
			<h2 class="text-danger">控制台信息</h2>
			<div class="row">
				<div class="col-md-10 ">
					<pre class="console">
				127.0.0.1 - - [18/Nov/2016:16:16:09 +0800] "GET / HTTP/1.1" 200 11418
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:00 +0800] "GET /fzg-web/1.txt HTTP/1.1" 404 965
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:00 +0800] "GET /fzg-web//resource/images/404.jpg HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:02 +0800] "GET /fzg-web/1.txt HTTP/1.1" 404 965
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:02 +0800] "GET /fzg-web//resource/images/404.jpg HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:11 +0800] "GET /fzg-web/1 HTTP/1.1" 404 965
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:14 +0800] "GET /fzg-web HTTP/1.1" 302 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:17:17 +0800] "GET /fzg-web/ HTTP/1.1" 200 3592
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:43 +0800] "GET /fzg-web HTTP/1.1" 302 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/ HTTP/1.1" 200 3592
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/jquery-2.1.4.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/weixin/css/index.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/DDMBase.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/images/logo.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/images/personal.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/images/fabu2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/images/logo-font2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/amazingUI/js/amazeui.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/style.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/weixin/css/common.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/jquery.query.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/Validform_v5.3.2.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/MTools.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/MForm.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/MUI.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/MValidform.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/MGrid.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/Pagination.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/amazingUI/css/amazeui.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/js/systools/AjaxProxyForMobile.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css?2.0 HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:19:44 +0800] "GET /fzg-web/resource/plugin/amazingUI/fonts/fontawesome-webfont.woff2?v=4.6.3 HTTP/1.1" 304 -
127.0.0.1 - - [18/Nov/2016:16:22:06 +0800] "GET / HTTP/1.1" 200 11418
0:0:0:0:0:0:0:1 - - [18/Nov/2016:16:22:29 +0800] "GET /fzg-web/MP_verify_vvC40J7YlYme02Sn.txt HTTP/1.1" 200 16
127.0.0.1 - - [18/Nov/2016:18:01:19 +0800] "GET / HTTP/1.1" 200 11418
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:45 +0800] "GET /fzg-web HTTP/1.1" 302 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/ HTTP/1.1" 200 6868
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/DDMBase.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/jquery-2.1.4.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/weixin/css/index.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/images/logo.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/images/logo-font2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/images/fabu2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/images/personal.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/style.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/weixin/css/common.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/Validform_v5.3.2.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/jquery.query.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/MTools.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/MUI.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/AjaxProxyForMobile.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/amazingUI/css/amazeui.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/amazingUI/js/amazeui.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/MGrid.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/MForm.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/Pagination.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/js/systools/MValidform.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:46 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css?2.0 HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:03:47 +0800] "GET /fzg-web/resource/plugin/amazingUI/fonts/fontawesome-webfont.woff2?v=4.6.3 HTTP/1.1" 304 -
127.0.0.1 - - [18/Nov/2016:18:09:18 +0800] "GET / HTTP/1.1" 200 11418
127.0.0.1 - - [18/Nov/2016:18:11:09 +0800] "GET / HTTP/1.1" 200 11418
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:10 +0800] "GET /fzg-web/common/redirect/login HTTP/1.1" 200 2826
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:10 +0800] "GET /fzg-web/resource/css/login.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:10 +0800] "GET /fzg-web/resource/js/systools/MBase.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:10 +0800] "GET /fzg-web/resource/js/jquery-2.1.4.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/css/awesome-bootstrap-checkbox.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/css/animate.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/font-awesome/css/font-awesome.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/css/style.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/style.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/bootstrap-3.3.5/css/bootstrap.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/datetimrpicker/bootstrap-datetimepicker.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/bootstrap-table/bootstrap-table.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/select2/select2.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/jquery.slimscroll.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/bootstrap-3.3.5/js/bootstrap.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/jquery.metisMenu.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/layer/layer.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/jquery.query.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/bootstrap-table/bootstrap-table.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/layer/skin/layer.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/contabs.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/layer/extend/layer.ext.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/pace.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/select2/select2.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/bootstrap-table/locale/bootstrap-table-zh-CN.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/layer/skin/layer.ext.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/MGrid.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/MTools.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/MValidform.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/MUI.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/MForm.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/hplus.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/js/systools/AjaxProxy.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/Validform_v5.3.2/Validform_v5.3.2.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/datetimrpicker/bootstrap-datetimepicker.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/plugin/font-awesome/fonts/fontawesome-webfont.woff?v=4.2.0 HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/images/login-background.jpg HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/images/locked.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:11 +0800] "GET /fzg-web/resource/images/user.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:19 +0800] "GET /fzg-web/resource/plugin/layer/skin/default/loading-2.gif HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:59 +0800] "POST /fzg-web/common/dologin HTTP/1.1" 200 106
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:59 +0800] "GET /fzg-web/admin/redirect/index HTTP/1.1" 200 7529
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:59 +0800] "GET /fzg-web/resource/images/profile_small.jpg HTTP/1.1" 200 16101
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:53:59 +0800] "GET /fzg-web/resource/images/header-profile.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:00 +0800] "GET /fzg-web/admin/redirect/welcome HTTP/1.1" 200 1253
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:00 +0800] "GET /fzg-web/admin/redirect/favicon.ico HTTP/1.1" 404 965
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:03 +0800] "GET /fzg-web/resource/plugin/layer/skin/default/loading-0.gif HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:03 +0800] "GET /fzg-web/admin/functionRedirect/fy-list/24?v=4.0 HTTP/1.1" 200 8128
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:03 +0800] "GET /fzg-web/resource/plugin/bootstrap-3.3.5/fonts/glyphicons-halflings-regular.woff2 HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 192
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 247
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 298
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 309
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "GET /fzg-web/resource/plugin/layer/skin/default/icon.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14135
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:04 +0800] "GET /fzg-web/resource/images/d.png HTTP/1.1" 200 367
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:55 +0800] "GET /fzg-web/admin/functionRedirect/fy-list/24?v=4.0 HTTP/1.1" 200 8128
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:54:55 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14135
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:13 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 337
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:27 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 298
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:32 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 192
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:35 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:36 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 247
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:41 +0800] "GET /fzg-web/admin/functionRedirect/fy-list/24?v=4.0 HTTP/1.1" 200 8128
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:42 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14135
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:50 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 337
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:55 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 298
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:56 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 192
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:56 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 247
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:55:56 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "GET /fzg-web/admin/functionRedirect/fy-list/24?v=4.0 HTTP/1.1" 200 8128
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 337
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 298
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 192
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 247
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:01 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14135
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/admin/fy/editForm?flag=2&id=179 HTTP/1.1" 200 15058
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/resource/js/fygl.js HTTP/1.1" 200 2964
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/resource/plugin/kindeditor/plugins/code/prettify.css HTTP/1.1" 200 973
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/resource/plugin/kindeditor/kindeditor.js HTTP/1.1" 200 166436
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/resource/plugin/kindeditor/lang/zh_CN.js HTTP/1.1" 200 8488
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:16 +0800] "GET /fzg-web/resource/plugin/kindeditor/themes/default/default.css HTTP/1.1" 200 21967
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "GET /resource/images/sgoods_defualt.jpg HTTP/1.1" 404 1019
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "GET /fzg-web/resource/plugin/kindeditor/plugins/code/prettify.js HTTP/1.1" 200 13660
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "GET /resource/images/sgoods_defualt.jpg HTTP/1.1" 404 1019
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 284
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 349
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 238
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "GET /fzg-web/resource/plugin/kindeditor/themes/default/default.png HTTP/1.1" 200 8299
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:17 +0800] "POST /fzg-web/admin/fyPhoto/showList HTTP/1.1" 200 83
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:21 +0800] "POST /fzg-web/common/dologin HTTP/1.1" 200 106
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:22 +0800] "POST /fzg-web/admin/fy/fyCheckPass?id=179 HTTP/1.1" 200 98
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:22 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14138
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "GET /fzg-web/admin/fy/editForm?flag=2&id=178 HTTP/1.1" 200 15157
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 284
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 349
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 238
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:24 +0800] "POST /fzg-web/admin/fyPhoto/showList HTTP/1.1" 200 545
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:28 +0800] "POST /fzg-web/admin/fy/addOrModify HTTP/1.1" 200 104
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:31 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14138
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "GET /fzg-web/admin/fy/editForm?flag=2&id=178 HTTP/1.1" 200 15157
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 284
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 238
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 349
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:33 +0800] "POST /fzg-web/admin/fyPhoto/showList HTTP/1.1" 200 545
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:38 +0800] "POST /fzg-web/admin/fy/fyCheckPass?id=178 HTTP/1.1" 200 98
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:56:39 +0800] "POST /fzg-web/admin/fy/showList HTTP/1.1" 200 14141
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:57:56 +0800] "GET /fzg-web/ HTTP/1.1" 200 6868
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "GET /fzg-web/admin/fy/editForm?flag=1&id=178 HTTP/1.1" 200 14858
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 284
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 349
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 238
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "POST /fzg-web/admin/dataDictionary/showDataDictionary HTTP/1.1" 200 180
0:0:0:0:0:0:0:1 - - [18/Nov/2016:18:58:41 +0800] "POST /fzg-web/admin/fyPhoto/showList HTTP/1.1" 200 545
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:40 +0800] "GET /fzg-web HTTP/1.1" 302 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/ HTTP/1.1" 200 8047
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/js/systools/DDMBase.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/weixin/css/index.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/images/logo.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /resource/images/sgoods_defualt.jpg HTTP/1.1" 404 1019
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/images/logo-font2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/images/personal.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/images/fabu2.png HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/js/systools/AjaxProxyForMobile.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/weixin/css/common.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/amazingUI/js/amazeui.min.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/js/systools/Pagination.js HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/amazingUI/css/amazeui.min.css HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /resource/images/sgoods_defualt.jpg HTTP/1.1" 404 1019
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/layer_mobile/layer.css?2.0 HTTP/1.1" 304 -
0:0:0:0:0:0:0:1 - - [18/Nov/2016:19:01:41 +0800] "GET /fzg-web/resource/plugin/amazingUI/fonts/fontawesome-webfont.woff2?v=4.6.3 HTTP/1.1" 304 -
				
				</pre>
				</div>
			</div>
		</div>
	</div>

	<script type="text/template" id="bb">
<form class="form-horizontal dataform " style="padding:20px" >
		<div class="form-group">
								<label class="col-sm-3 control-label">请填写备注</label>
								<div class="col-sm-8">
									<input id="name"   class="form-control ">
								</div>
							</div>	
	<div class="form-group">
								<div class="col-sm-12 text-center">
									<button type="button"  class="btn btn-success">创建</button>
								</div>
							</div>	
</form>
	</script>

	<script type="text/javascript"
		src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
		function addTomcat() {
			layer.open({
				type : 1,
				title : "创建Tomcat",
				shadeClose : true,
				area : [ MUI.SIZE_S, '300px' ],
				content : $("#bb").html()
			});
		}
	</script>
</body>
</html>
