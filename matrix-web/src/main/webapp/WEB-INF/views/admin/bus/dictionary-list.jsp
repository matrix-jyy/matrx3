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
<LINK rel="Bookmark" href="../images/favicon.ico">
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	<!-- 搜索框部分start -->
	<matrix:btn value="user:search">
	<div class="row" >
		<div class="col-sm-12" > 
		<form class="form-inline" id="serchform" onsubmit="javascripr:return false;">
			<div class="form-group mr-20">
				<label for="exampleInputName2">数据值</label>
				<input name="dicName" type="text" class="form-control">
			</div>
			
			<div class="form-group  mr-20">
				<select class="form-control autoFull select2" dataType="*" name="dicCateId" nullmsg="父级分类不能为空" 
				data-url="${path}/admin/dictionaryType/showAll"	 
				data-value="cataId"
				data-filed="cataName"
				>  
				<option value=''>--请选择数据类型--</option>
				</select>
			</div>
			<button  onclick="myGrid.serchData()" type="button" class="btn btn-info btn-sm"><i class="fa fa-search " ></i> 搜索</button>
			<button  onclick="MForm.reset('serchform');" class="btn btn-info btn-sm"><i class="fa fa-refresh " ></i> 重置</button>
		</form>
			</div>
	</div>
		</matrix:btn>
		<div class="ibox-content radius-5 mt-5 mpanel">
	<div class="row" >
		<div class="col-sm-12" >
		<div class="option-bar" >
		<matrix:btn value="user:dels">
			<button onclick="myGrid.delItems('dicId')" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash" ></i>批量删除</button>
		</matrix:btn>
		<matrix:btn value="user:add">
			<button onclick="openAdd()" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus" ></i>  新增</button>
		</matrix:btn>
		</div>
	<!-- 搜索框部分en -->
		<table id="mgrid" >
			<thead>
				<tr>
					<th data-checkbox="true"  ></th>
					<th data-formatter="MGrid.indexfn" data-align="center"  data-width="30px" >序号</th>
					<th data-field="dicCateName" data-sortable="true">数据字典类型</th>
					<th data-field="dicName" data-sortable="true">数据值</th>
					<th data-align="center"  data-width="150px" data-field="dicId" data-formatter="buidOperate">操作</th>
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
			<matrix:btn value="user:del">
			delPath="${path}/admin/sysDataDictionary/del";
			</matrix:btn>
			 myGrid=MGrid.initGrid({
				/*  selectItemName : "dicId", */
				 url:"${path}/admin/sysDataDictionary/showList",
				 delUrl:delPath
			 });
		});
		function buidOperate(value, row, index){
			var html=[];
			<matrix:btn value="user:edit">
			html[0]='<button class="btn btn-default btn-sm mr-5" onClick="openEdit('+value+')"  title="编辑"><i class="fa fa-edit option"></i></buttoun>'
			</matrix:btn>
			<matrix:btn value="user:del">
			html[1]='<button class="btn btn-default btn-sm" onClick="myGrid.delItem('+value+')" title="删除"><i class="fa fa-close option text-danger"></i></buttoun>';
			</matrix:btn>
			return html.join(""); 
		}
		<matrix:btn value="user:add">
		//打开添加界面
		function openAdd() {
			layer.open({
				type : 2,
				title : "添加数据字典",
				maxmin : true,
				area : [ MUI.SIZE_L, '400px' ],
				content : [ '${path}/admin/sysDataDictionary/editForm']
			}); 
		}
		</matrix:btn>
		<matrix:btn value="user:edit">
		//打开编辑界面
		function openEdit(id) {
			layer.open({
				type : 2,
				title : "编辑数据字典",
				area : [ MUI.SIZE_L, '400px' ],
				maxmin : true,
				content : [ '${path}/admin/sysDataDictionary/editForm?dicId=' + id]
			});
		}
		</matrix:btn>
		$(".select2").select2();
		MTools.autoFullSelect();
	</script>
</body>
</html>
