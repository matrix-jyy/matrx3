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
		<form class="form-horizontal" id="dataform" onsubmit="javascripr:return false;">
			<input type="hidden" name="tokenUrl" value="${tokenUrl}">	 
			<input type="hidden" name="token" value="${token}">
		<c:if test="${obj ne null }">
			<input type="hidden" name="fnId" value="${obj.fnId }">
		</c:if>
		
		
			
			<label class="col-sm-2 control-label"><span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnIcon"
					value="<c:out value="${obj.fnIcon }"></c:out>" datatype="*1-16" nullmsg="不能为空">
			</div>
			
				<div class="form-group">
			
			<label class="col-sm-2 control-label"><span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnPath"
					value="<c:out value="${obj.fnPath }"></c:out>" datatype="*1-16" nullmsg="不能为空">
			</div>
			
				</div>
			
			<label class="col-sm-2 control-label">是禁用吗 是，否<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnIsDisable"
					value="<c:out value="${obj.fnIsDisable }"></c:out>" datatype="*1-16" nullmsg="是禁用吗 是，否不能为空">
			</div>
			
				<div class="form-group">
			
			<label class="col-sm-2 control-label">功能等级<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnGrade"
					value="<c:out value="${obj.fnGrade }"></c:out>" datatype="*1-16" nullmsg="功能等级不能为空">
			</div>
			
				</div>
			
			<label class="col-sm-2 control-label">父级功能<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnParentId"
					value="<c:out value="${obj.fnParentId }"></c:out>" datatype="*1-16" nullmsg="父级功能不能为空">
			</div>
			
				<div class="form-group">
			
			<label class="col-sm-2 control-label"><span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnName"
					value="<c:out value="${obj.fnName }"></c:out>" datatype="*1-16" nullmsg="不能为空">
			</div>
			
				</div>
			
			<label class="col-sm-2 control-label">排序字段<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnSequence"
					value="<c:out value="${obj.fnSequence }"></c:out>" datatype="*1-16" nullmsg="排序字段不能为空">
			</div>
			
				<div class="form-group">
			
			<label class="col-sm-2 control-label">功能code<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnCode"
					value="<c:out value="${obj.fnCode }"></c:out>" datatype="*1-16" nullmsg="功能code不能为空">
			</div>
			
				</div>
			
			<label class="col-sm-2 control-label">功能所具有的按钮<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnBtns"
					value="<c:out value="${obj.fnBtns }"></c:out>" datatype="*1-16" nullmsg="功能所具有的按钮不能为空">
			</div>
			
				<div class="form-group">
			
			<label class="col-sm-2 control-label">是否在菜单栏显示<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*" class="form-control" name="fnShowMenu"
					value="<c:out value="${obj.fnShowMenu }"></c:out>" datatype="*1-16" nullmsg="是否在菜单栏显示不能为空">
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
<script type="text/javascript" src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
	var myForm=MForm.initForm({
		invokeUrl:"${path}/admin/sysFunction/addOrModify",
		afterSubmit:function(){
			parent.myGrid.serchData();
		},
	});
	</script>
</body>
</html>