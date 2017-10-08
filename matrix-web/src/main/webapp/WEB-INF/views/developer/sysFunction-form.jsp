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
	<script type="text/javascript"
	src="${path }/resource/plugin/bootstrap-3.3.5/js/bootstrapSwitch.js"></script>
<link rel="stylesheet" href="${path }/resource/plugin/bootstrap-3.3.5/css/bootstrapSwitch.css">
<script type="text/javascript"
	src="${path }/resource/js/function/public.js"></script>
<style type="text/css">
table tr td {
	border: none !important;
}

table tr th {
	border: none !important;
}
</style>
</head>
<body>
	<div class="ibox-content">
		<form class="form-horizontal " id="dataform"
			onsubmit="javascripr:return false;">
			<input type="hidden" name="tokenUrl" value="${tokenUrl}"> <input
				type="hidden" name="token" value="${token}">
			<c:if test="${obj ne null }">
				<input type="hidden" name="id" value="${obj.id }">
			</c:if>
			<div class="form-group">
				<label class="col-offset-1 col-sm-2 control-label">父级功能<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<select class="form-control  select2" size="1" name="fnParentId"
						id="parentId">
					</select>
				</div>

				<label class="col-sm-2 control-label">功能名称<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<input name="fnName" type="text" class="form-control "
						nullmsg="功能名称不能为空" datatype="*1-50">
					<div class="Validform_checktip"></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">访问路径</label>
				<div class="col-sm-3">
					<input name="fnPath" type="text" class="form-control">
				</div>
				<label class="col-sm-2 control-label">功能图标</label>
				<div class="col-sm-3">
					<input name="fnIcon" type="text" id="iconText" class="form-control"
						onclick="fnPublci.showIcon()">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">功能CODE<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<input name="fnCode" nullmsg="功能CODE不能为空" datatype="*1-50"
						class="form-control">
				</div>
				<label class="col-sm-2 control-label">排序</label>
				<div class="col-sm-3">
					<input name="fnSequence" type="number" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">是否在菜单栏显示<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<div class="radio radio-success radio-single radio-inline">
					<input name="fnShowMenu" checked="checked" type="radio" id="radio1" value="是" > <label for="radio1">显示</label>
					</div>
					<div class="radio radio-success radio-single radio-inline">
					<input name="fnShowMenu" type="radio" id="radio2" value="否" > <label for="radio2">不显示</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">功能按钮</label>
				<div class="col-sm-9" id="btns">
					<table class="table ">
						<tbody>
							<c:forEach items="${btnList }" var="item" varStatus="count">
								<c:if test="${count.index%4==0 }">
									<tr>
								</c:if>
								<td>
									<div class="checkbox checkbox-inline">
										<input name="fnBtns" type="checkbox"
											id="inlineCheckbox${item.btnValue }"
											value="${item.btnValue }"> <label
											for="inlineCheckbox${item.btnValue }"> ${item.btnKey }
										</label>
									</div>
								</td>
								<c:if test="${count.index-3%4==0 }">
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="form-group ">
				<div class="col-12 text-center">
					<a href="javascript:;" onclick="myForm.submit()"
						class="btn btn-success radius"><i class="fa fa-check"></i> 保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" onclick="MTools.closeForm()"
						class="btn btn-danger radius"><i class="fa fa-close"></i> 关闭</a>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
		$(".select2").select2();
		var myForm = null;
		$(function() {
			myForm = MForm.initForm({
				invokeUrl : "${path}/admin/sysFunction/addOrModify",
				afterSubmit : function() {
					parent.initFunctionList();
				}
			});
			fnPublci.initFunctionSelect("#parentId");
		});
	</script>
</body>
</html>