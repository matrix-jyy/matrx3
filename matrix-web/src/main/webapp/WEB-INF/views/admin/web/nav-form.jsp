<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<!-- 文件上传需要的样式 -->

<link rel="stylesheet" type="text/css"
	href="${path }/resource/plugin/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css"
	href="${path }/resource/plugin/kindeditor/plugins/code/prettify.css" />
<script type="text/javascript"
	src="${path }/resource/plugin/kindeditor/kindeditor.js"></script>
<script type="text/javascript"
	src="${path }/resource/plugin/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="${path }/resource/plugin/kindeditor/plugins/code/prettify.js"></script>

</head>
<body style="height: 100%; overflow: hidden;">
	<div class="ibox-content">
		<form class="form-horizontal " id="dataform"
			onsubmit="javascripr:return false;">
			<c:if test="${obj ne null }">
				<input type="hidden" name="navId" value="${obj.navId }">
			</c:if>
			<div class="form-group">
							<label class="col-sm-2 control-label">父级导航栏</label>
							<div class="col-sm-4">
								<select class="select2 form-control " size="1" name="parentId" id="parentId">
									<option value="0" selected="selected">|ROOT</option>
								</select>
							</div>
							<label class="col-sm-2 control-label">导航名称</label>
							<div class="col-sm-4">
								<input id="navName" name="navName" type="text" class="form-control " nullmsg="导航名称不能为空"
									datatype="*1-50" errormsg="导航名称长度不能超过50">									
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">导航序号</label>
							<div class="col-sm-4">
								<input id="navOrderby" name="navOrderby" type="text" class="form-control " nullmsg="导航序号不能为空"
									datatype="n1-10" errormsg="导航序号不能超过10">					
							</div>
							<div class="Validform_checktip"></div>
							<label class="col-sm-2 control-label">导航描述</label>
							<div class="col-sm-4">
								<input id="navDescription" name="navDescription" type="text" class="form-control">					
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">导航类型</label>
							<div class="col-sm-4">
								<select class="form-control autoFull select2" dataType="*" nullmsg="导航类型不能为空" 
								data-url="${path}/admin/webTypeNav/showParentType"	 
								data-value="cataId"
								data-filed="cataName"
								data-def="${obj.navType}"  
								name="navType">  
								<option value=''>--请选择父级分类--</option>
								</select>
							</div>
							
							<label class="col-sm-2 control-label">导航链接地址</label>
							<div class="col-sm-4">
								<input id="navUrl" name="navUrl" type="text" class="form-control " nullmsg="导航链接地址不能为空"
									datatype="*1-1000" errormsg="导航链接地址不能超过1000">					
							</div>
							<div class="Validform_checktip"></div>
							<!-- <label class="col-sm-1 control-label">文章类型图标</label>
							<div class="col-sm-3">
								<input id="icon" name="icon"
									type="text" class="form-control "  onclick="showIcon()" readonly="readonly">					
							</div>
							<div class="Validform_checktip"></div>
							 -->
						</div>	
						<div class="form-group">
							<label class="col-sm-2 control-label">上传图片</label>
							<div class="col-sm-10">
								<input onclick="MTools.chooesFile('#fileText')"
									class="input-text upload-url radius form-control" style="width: 30%;display: inline;"
									type="text" id="showFileName" readonly="readonly" value="<c:out value="${obj.image}"></c:out>"> 
									<a onclick="MTools.chooesFile('#fileText')" class="btn btn-primary radius"> 
									<i class="fa fa-cloud-upload"></i> 浏览文件 </a> 
									<!-- <input type="button" class='btn btn-primary radius'
										onclick="MTools.upFile('#submitBtn')" value="上传">  -->
									<a id="selectInServices" class="btn btn-primary radius"> 
									<i class="fa fa-cloud"></i> 远程图库
								</a>
							</div>
						</div>
				
						<div class="form-group">
							<div class="col-sm-2 control-label"></div>
							<div class="col-sm-9">
								<c:if test="${obj ne null }">
									<input type="hidden" value="<c:out value="${obj.navInco }"></c:out>" name="navInco"
										id="hiddenImg" />
									<img width="150" id='img_1' src="<c:out value="${obj.navInco }"></c:out>" />
								</c:if>
								<c:if test="${obj eq null }">
									<input type="hidden" name="navInco" id="hiddenImg" />
									<img width="150" id='img_1' style="display: none" />
								</c:if>
							</div>
						</div>
			<div class="form-group ">
				<div class="col-12 text-center">
					<a href="javascript:;" onclick="myForm.submit()"
						class="btn btn-success radius">保存</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<button type="button" class="btn btn-danger radius" onclick="MTools.closeForm()" >取消</button>
				</div>
			</div>
		</form>
		<form class="form form-horizontal" method="post" style="display: none;"
			enctype="multipart/form-data" target="blankFrame"
			action="${path}/admin/uploadFile/doUpload?fileType=image&flag=img_1&hiddenI=hiddenImg">
	
			<input id="fileText" type="file" multiple name="fileName"
				onchange="MTools.selectFile('#showFileName','#fileText','#submitBtn')" /> <input
				type="submit" id="submitBtn"
				onclick="MTools.upolad('img_1','fileText')" />
		</form>
		<iframe src="" name="blankFrame" id="blankFrame"
			style="height: 100px; width: 100px; display: none;"> </iframe>
	</div>
	<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
	var myForm;
		$(function() {
			MTools.autoFullSelect();
			$(".select2").select2({'width':'100%'});
			myForm=MForm.initForm({
				invokeUrl:"${path}/admin/webNav/addOrModify",
				afterSubmit : function() {
					parent.initFunctionList();
				}
			});
			initFunctionList();
		});
		$(function() {
			MUI.openServiceImgSpace(function(url) {
				$("#img_1").attr("src", url).show();
				$("#hiddenImg").val(url);
				//$("#submitBtn").trigger("click");
			}, "#selectInServices");
			
		});
		/**
		 * 初始化产品列表
		 */
		function initFunctionList(id) {
			$.AjaxProxy().invoke("${path}/admin/webNav/all",
					function(loj) {
						$("#parentId").createSelectTree(
							loj.attr("result").rows,
							{	id : "navId",		// 选项的值
								parent : "parentId",	// 父节点值
								value : "navName",	// 要显示的名称
								append : false,
								defaultValue : id,
								defaultHtml : '<option value="0" selected="selected">|ROOT</option>'
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