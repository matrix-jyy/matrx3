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
			<input type="hidden" name="suId" value="${obj.suId }">
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label">用户名称<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*1-100" class="form-control" name="suName"
					value="<c:out value="${obj.suName }"></c:out>" datatype="*1-16" nullmsg="用户名称不能为空">
				<div class="Validform_checktip"></div>
			</div>
			<label class="col-sm-2 control-label">所属公司<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<select class="form-control autoFull" dataType="*" nullmsg="公司不能为空" 
				data-url="${path}/admin/sysCompany/all"	 
				data-value="comId"
				data-filed="comName"
				data-def="${obj.companyId}"  
				name="companyId">  
				<option value=''>--请选择公司--</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">账号<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="*1-510" class="form-control"
					value="<c:out value="${obj.suAccount }"></c:out>" name="suAccount" nullmsg="账号不能为空">
				<div class="Validform_checktip"></div>
			</div>
			<label class="col-sm-2 control-label">联系电话<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="m" class="form-control" name="suTel"
					value="<c:out value="${obj.suTel }" ></c:out>" datatype="*1-16" nullmsg="联系电话不能为空">
					<div class="Validform_checktip"></div>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<input type="text" dataType="e" class="form-control"
				value="<c:out value="${obj.suEmail }"></c:out>" name="suEmail" nullmsg="Email不能为空">
			</div>
			<div class="Validform_checktip"></div>
			<c:if test="${obj eq null }">
			<label class="control-label  col-sm-2">密码<span class="text-danger">*</span></label>
			<div class="formControls  col-sm-4">
				<input type="text" name="suPassword"  value="123"
					class="form-control radius" dataType="*1-50">
			</div>
			<div class="Validform_checktip"></div>
			</c:if>
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
	var myForm=MForm.initForm({
		invokeUrl:"${path}/admin/sysUsers/addOrModify",
		afterSubmit:function(){
			parent.myGrid.serchData();
		},
	});
	</script>
</body>
</html>