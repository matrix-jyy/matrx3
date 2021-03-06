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
	<matrix:btn value="navType:search">
		<div class="row" >
			<div class="col-sm-12" >
				<form class="form-inline" id="serchform">
					<div class="form-group mr-20">
						<input name="cataName" placeholder="导航栏名称"
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
	<div class="ibox-content radius-5 mt-5 mpanel">
		
	<div class="row" >
		<div class="col-sm-12" >
		<div class="option-bar" >
		<matrix:btn value="navType:dels">
			<button onclick="myGrid.delItems('cataId')" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash" ></i>批量删除</button>
		</matrix:btn>
		<matrix:btn value="navType:add">
			<button onclick="openAdd()" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus" ></i>  新增</button>
		</matrix:btn>
		</div>
	<!-- 搜索框部分en -->
		<table id="mgrid" >
			<thead>
				<tr>
					<th data-checkbox="true"  ></th>
					<th data-formatter="MGrid.indexfn" data-align="center"  data-width="30px" >序号</th>
					<th data-field="cataName" data-sortable="true">导航分类名称</th>
					<th data-field="parentName" data-sortable="true">父级导航分类名称</th>
					<th data-align="center"  data-width="150px" data-field="cataId" data-formatter="buidOperate">操作</th>
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
	
		$(function(){
			var delPath="";
			<matrix:btn value="navType:del">
			delPath="${path}/admin/webTypeNav/del";
			</matrix:btn>
			 myGrid=MGrid.initGrid({
				 url:"${path}/admin/webTypeNav/showList",
				 delUrl:delPath
			 });
		});
		function buidOperate(value, row, index){
			var html=[];
			<matrix:btn value="navType:edit">
			html[0]='<a onClick="openEdit('+value+')"  title="编辑" class="fa fa-edit option"></a>'
			</matrix:btn>
			<matrix:btn value="user:del">
			html[1]='<a onClick="myGrid.delItem('+value+')" title="删除" class="fa fa-close option text-danger"></a>';
			</matrix:btn>
			return html.join(""); 
		}
		<matrix:btn value="navType:add">
		//打开添加界面
		function openAdd() {
			layer.open({
				type : 2,
				title : "添加导航栏分类",
				maxmin : true,
				area : [ MUI.SIZE_L, '400px' ],
				content : [ '${path}/admin/webTypeNav/editForm']
			}); 
		}
		</matrix:btn>
		<matrix:btn value="navType:edit">
		//打开编辑界面
		function openEdit(id) {
			layer.open({
				type : 2,
				title : "编辑导航栏分类",
				area : [ MUI.SIZE_L, '400px' ],
				maxmin : true,
				content : [ '${path}/admin/webTypeNav/editForm?id=' + id]
			});
		}
		</matrix:btn>
	</script>
</body>
</html>
