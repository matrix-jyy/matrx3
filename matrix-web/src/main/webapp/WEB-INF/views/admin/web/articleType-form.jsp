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
<body style="height: 100%; overflow: hidden;">
	<div class="ibox-content">
		<form class="form-horizontal " id="dataform"
			onsubmit="javascripr:return false;">
			<%-- <c:if test="${obj ne null }">
				<input type="hidden" name="id" value="${obj.id }">
			</c:if> --%>
			<div class="form-group">
				<label class="col-offset-1 col-sm-2 control-label">父级文章类型</label>
				<div class="col-sm-3">
					<select class="select2 form-control " size="1" name="artTypeParentId"
						id="parentId">
						<option value="0" selected="selected">一级文章类别</option>
					</select>
				</div>

				<label class="col-sm-2 control-label">文章类型名称<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<input name="artTypeName" type="text" class="form-control " nullmsg="文章类型名称不能为空"
						datatype="*1-50" errormsg="文章类型名称长度不能超过50">					
				</div>
				<div class="Validform_checktip"></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">文章类型序号<span class="text-danger">*</span></label>
				<div class="col-sm-3">
					<input name="artTypeSort" type="text" class="form-control " nullmsg="文章类型序号不能为空"
						datatype="n1-10" errormsg="文章类型序号不能超过10">					
				</div>
				<div class="Validform_checktip"></div>
				<label class="col-sm-2 control-label">文章类型描述</label>
				<div class="col-sm-3">
					<input name="artTypeDescription" type="text" class="form-control">					
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">文章类型图标</label>
				<div class="col-sm-3">
					<input id="iconText" name="artTypeIcon"
						type="text" class="form-control "  onclick="showIcon()" readonly="readonly">					
				</div>
				<div class="Validform_checktip"></div>
				<!-- <label class="col-sm-2 control-label">链接地址</label>
				<div class="col-sm-3">
					<input name="url" type="text" class="form-control " nullmsg="产品名称不能为空"
						datatype="*1-20" errormsg="产品名称长度不能超过20">					
				</div>
				<div class="Validform_checktip"></div> -->
			</div>

			<div class="form-group ">
				<div class="col-12 text-center">
					<a href="javascript:;" onclick="myForm.submit()"
						class="btn btn-success radius"><i class="fa fa-check"></i>保存</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<button type="button" class="btn btn-danger radius" onclick="MTools.closeForm()" ><i class="fa fa-close"></i>取消</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
	var myForm;
		$(function() {
			$(".select2").select2({'width':'100%'});
			myForm=MForm.initForm({
				invokeUrl:"${path}/admin/webArticleType/addOrModify",
				afterSubmit : function() {
					parent.initFunctionList();
				}
			});
			initFunctionList();
		});

		/**
		 * 初始化文章类型列表
		 */
		function initFunctionList() {
			$.AjaxProxy({a : false,c : false}).invoke("${path}/admin/webArticleType/all", function(loj) {
				$("#parentId").createSelectTree(
						loj.attr("result").rows,
						{	id : "artTypeId",		// 选项的值
							parent : "artTypeParentId",	// 父节点值
							value : "artTypeName",	// 要显示的名称
							append : false,
							//defaultValue : id,
							defaultHtml : '<option value="0" selected="selected">一级文章类别</option>'
						});
				
			});

		}
		
		
		function showIcon() {
			layer.open({
				type : 2,
				title : "选择图标",
				area : [ '90%', '90%' ],
				fix : true, // 不固定
				maxmin : true,
				content : '${path}/common/redirect/icons'
			});
		}
		
	</script>
</body>
</html>
