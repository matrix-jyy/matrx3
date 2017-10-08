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
</head>
<body>
<div class="ibox-content">
		<form class="form-horizontal" id="dataform"
			onsubmit="javascripr:return false;">
		
		
		<div class="form-group">
			<label class="col-sm-2 control-label">省</label>
			<div class="col-sm-4">
				<select class="form-control" id="a" >
				</select>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">市</label>
			
			<div class="col-sm-4">
				<select class="form-control" id="b" >
				</select>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">区</label>
			<div class="col-sm-4">
				<select class="form-control" id="c" >
				</select>
			</div>
		</div>
		<div class="form-group ">
				<div class="col-sm-12 text-center">
				<a href="javascript:;" onclick="myForm.submit()"
					class="btn btn-success radius"><i class="fa fa-check"></i>  保存</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
					 class="btn btn-danger radius" href="javascript:;" onclick="MTools.closeForm()" ><i class="fa fa-close"></i> 关闭</a>
			   </div>
		</div>
	</form>
	</div>
</body>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
	
	var initParam = {
			params: [{divId:"a",info:"请选择省份"},{divId:"b",info:"请选择市"},{divId:"c",info:"请选择地区"}],//对应的html元素id,以及提示信息
		};
	MUI.initAreaInfo(initParam);
	
	//$(".select2").select2();
	
	</script>
</body>
</html>