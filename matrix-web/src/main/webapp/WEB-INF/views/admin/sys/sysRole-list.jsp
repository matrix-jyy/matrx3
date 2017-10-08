<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.zkingsoft.com" prefix="matrix"%>
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
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
<!-- 搜索框部分start -->
<matrix:btn value="role:search"> 
<div class="row" >
		<div class="col-sm-12" >
			<form class="form-inline" id="serchform">
				<div class="form-group mr-20">
					<input name="roleName" placeholder="请输入角色名称"
						type="text" class="form-control">
				</div>
				<button onclick="myGrid.serchData()" type="button"
					class="btn btn-info btn-sm">
					<i class="fa fa-search "></i> 搜索
				</button>
				<button type="reset" class="btn btn-info btn-sm">
					<i class="fa fa-refresh "></i> 重置
				</button>
			</form>
	</div>
	</div>
		</matrix:btn>
		<!-- 搜索框部分en -->
	<div class="ibox-content radius-5 mt-5 mpanel">
	<div class="row" >
		<div class="col-sm-12" >
		<div class="option-bar" >
			<matrix:btn value="role:dels"> 
				<button onclick="myGrid.delItems('roleId')" type="button"
					class="btn btn-danger btn-sm">
					<i class="fa fa-trash"></i>批量删除
				</button>
			</matrix:btn>
			<matrix:btn value="role:add"> 
				<button onclick="openAdd()" type="button"
					class="btn btn-success btn-sm">
					<i class="fa fa-plus"></i> 新增
				</button>
			</matrix:btn>
			<span class="alert-warning btn-sm">
				<b>提示：</b> 角色用于控制登录权限，和左侧菜单栏,以及一些不在菜单栏中出现的功能与按钮
			</span>
		</div>
		<table id="mgrid">
			<thead>
				<tr>
					<th data-checkbox="true"></th>
					<th data-formatter="MGrid.indexfn" data-align="center"
						data-width="30px">序号</th>
					<th data-field="roleName" data-sortable="true">角色名称</th>
					<th data-field="roleRemark">备注</th>
					<th data-align="center" data-width="150px" data-field="roleId"
						data-formatter="buidOperate">操作</th>
				</tr>
			</thead>
		</table>
</div>
	</div>
</div>
</div>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
		var myGrid;
		$(function() {
			//有删除权限
			var delUrl="";
			<matrix:btn value="role:dels"> 
			delUrl="${path}/admin/sysRole/del";
			</matrix:btn>
			
			myGrid = MGrid.initGrid({
				url : "${path}/admin/sysRole/showCompanyRole",
				delUrl:delUrl
			});
		});
		function buidOperate(value, row, index) {
			var html = [];
			<matrix:btn value="role:edit"> 
			html [0]= '<a onClick="openEdit('+ value+ ')"  title="编辑" class="fa fa-edit option"></a>'
			</matrix:btn>
			<matrix:btn value="role:del"> 
			html [1]= '<a  onClick="myGrid.delItem('+ value+ ')" title="删除"class="fa fa-close option text-danger"></a>';
			</matrix:btn>
			return html.join("");
		}
		//打开添加界面
		<matrix:btn value="role:add"> 
		function openAdd() {
		layer.full(	layer.open({
				type : 2,
				title : "添加角色",
				area : [ MUI.SIZE_L, '500px' ],
				maxmin : true,
				content : [ '${path}/admin/sysRole/editForm' ]
			}));
		}
		</matrix:btn>
		
		//打开编辑界面
		<matrix:btn value="role:edit"> 
		function openEdit(id) {
			layer.full(	layer.open({
				type : 2,
				title : "编辑角色",
				area : [ MUI.SIZE_L, '500px' ],
				maxmin : true,
				content : [ '${path}/admin/sysRole/editForm?id=' + id ]
			}));
		}
		</matrix:btn>
	</script>
</body>
</html>
