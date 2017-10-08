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
<LINK rel="Bookmark" href="../images/favicon.ico">
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
<title>按钮管理</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row" >
		<div class="col-sm-12" >
	<!-- 搜索框部分start -->
		<form class="form-inline" id="serchform">
			<div class="form-group mr-20">
				<label for="exampleInputName2">Key</label>
				<input name="btnKey" type="text" class="form-control">
			</div>
			<div class="form-group mr-20">
				<label for="exampleInputEmail2">Value</label>
				<input name="btnValue" type="text" class="form-control" >
			</div>
			<button  onclick="myGrid.serchData()" type="button" class="btn btn-info btn-sm"><i class="fa fa-search " ></i> 搜索</button>
			<button  type="reset" class="btn btn-info btn-sm"><i class="fa fa-refresh " ></i> 重置</button>
		</form>
			</div>
	</div>
	<div class="ibox-content radius-5 mt-5 mpanel">
		
	<div class="row" >
		<div class="col-sm-12" >
		<div class="option-bar" >
			<button onclick="myGrid.delItems('btnId')" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash" ></i>批量删除</button>
			<button onclick="openAdd()" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus" ></i>  新增</button>
		</div>
	<!-- 搜索框部分en -->
		<table id="mgrid" >
			<thead>
				<tr>
					<th data-checkbox="true"  ></th>
					<th data-formatter="MGrid.indexfn" data-align="center"  data-width="30px" >序号</th>
					<th data-field="btnKey" data-sortable="true">btnKey</th>
					<th data-field="btnValue" data-sortable="true">btnValue</th>
					<th data-align="center"  data-width="150px" data-field="btnId" data-formatter="buidOperate">操作</th>
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
			 myGrid=MGrid.initGrid({
				 url:"${path}/admin/sysBtn/showList",
				 delUrl:"${path}/admin/sysBtn/del"
			 });
		});
		function buidOperate(value, row, index){
			var html=[];
			html[0]='<a  onClick="openEdit('+value+')"  title="编辑" class="fa fa-edit option"></a>'
			html[1]='<a   onClick="myGrid.delItem('+value+')" title="删除" class="fa fa-close option text-danger"></a>';
			return html.join(""); 
		}
		//打开添加界面
		function openAdd() {
			layer.open({
				type : 2,
				title : "添加按钮",
				area : [ MUI.SIZE_S, '300px' ],
				content : [ '${path}/admin/sysBtn/editForm', 'no' ]
			}); 
		}
		//打开编辑界面
		function openEdit(id) {
			layer.open({
				type : 2,
				title : "编辑按钮",
				area : [ MUI.SIZE_S, '300px' ],
				maxmin : true,
				content : [ '${path}/admin/sysBtn/editForm?id=' + id,'no' ]
			});
		}
	</script>
</body>
</html>
