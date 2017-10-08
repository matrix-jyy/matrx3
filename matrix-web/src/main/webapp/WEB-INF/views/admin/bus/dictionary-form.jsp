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
			<input type="hidden" name="tokenUrl" value="${tokenUrl}">	 
		<input type="hidden" name="token" value="${token}">
		<c:if test="${obj ne null }">
			<input type="hidden" name="dicId" value="${obj.dicId }">
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label">数据值<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*1-100" class="form-control" name="dicName"
					value="<c:out value="${obj.dicName }"></c:out>" ${obj.dicName } ${obj } datatype="*1-16" nullmsg="数据值不能为空">
				<div class="Validform_checktip"></div>
			</div>
			
			<%--<label class="col-sm-2 control-label">排序值<span class="text-danger">*</span></label>
			 <div class="col-sm-4">
				<input type="text" dataType="*1-100" class="form-control" name="cataSequnce"
					value="<c:out value="${obj.cataSequnce }"></c:out>" datatype="*1-16" nullmsg="排序值不能为空">
				<div class="Validform_checktip"></div>
			</div> --%>
			
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">数据类型<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<select class="form-control autoFull select2" dataType="*" nullmsg="父级分类不能为空" 
				data-url="${path}/admin/dictionaryType/showAll"	 
				data-value="cataId"
				data-filed="cataName"
				data-def="${obj.dicCateId}"  
				name="dicCateId">
				<option value=''>--请选择数据类型--</option>
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
	MTools.autoFullSelect();
	$(".select2").select2();
	var myForm=MForm.initForm({
		invokeUrl:"${path}/admin/sysDataDictionary/addOrModify",
		afterSubmit:function(){
			parent.myGrid.serchData();
		}
	});
	</script>
</body>
</html>