<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<div class="ibox-content">
	<form class="form-horizontal" id="dataform"
		onsubmit="javascripr:return false;">
		<input type="hidden" name="tokenUrl" value="${tokenUrl}">	 
		<input type="hidden" name="token" value="${token}">
		<c:if test="${obj ne null }">
			<input type="hidden" name="artId" id="artId" value="${obj.artId}">
			<input type="hidden" id="artTypeId" value="${obj.artTypeId}">
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label">作者名称<span class="text-danger">*</span></label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="artAuthor"
					value="<c:out value="${obj.artAuthor}" /> " datatype="*" nullmsg="作者名称不能为空">
			</div>
			<div class="Validform_checktip"></div>
			<label class="col-sm-2 control-label">文章类型</label>
			<div class="col-sm-3">
				<select class="select2 form-control " size="1" name="artTypeId" id="parentId">
					<option value="0" selected="selected">--请选择文章类型--</option>
				</select>
			</div>
			<div class="Validform_checktip"></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">标题<span class="text-danger">*</span></label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="artTitle"
					value="<c:out value="${obj.artTitle}" />" datatype="*" nullmsg="标题不能为空">
			</div>
			<div class="Validform_checktip"></div>
			<label class="col-sm-2 control-label">排序<span class="text-danger">*</span></label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="artSort"
					value="${obj.artSort}" datatype="n" nullmsg="序号不能为空"
					errormsg="序号只能为数字">
			</div>
			<div class="Validform_checktip"></div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">文章摘要</label>
			<div class="col-sm-3">
				<input value="<c:out value="${obj.artAbstract}" />" datatype="*1-550"
					ignore="ignore" name="artAbstract" class="form-control"
					type="text" id="zy-input" placeholder="自动抓取为文章前面100个字符">
			</div>
			<div class="col-sm-3">
				<input class='btn radius btn-primary' type="button" value="自动抓取"
					onclick="autoZy()"> <input class='btn radius btn-primary'
					type="button" value="清空摘要" onclick="cleanZy()">
			</div>
			<div class="Validform_checktip"></div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">是否立即发布</label>
			<div class="col-sm-3">
				<c:if test="${obj ne null }">
					<div class="row">
						<div class="col-sm-2 radio radio-danger">
							<input type="radio" name="artIsPublish" id="radio1" value="是" <c:if test="${obj ne null && obj.artIsPublish eq '是' }"> checked="checked" </c:if>>
							<label for="radio1">是</label>
						</div>
						<div class="col-sm-2 radio radio-danger">
							<input type="radio" name="artIsPublish" id="radio2" value="否" <c:if test="${obj ne null && obj.artIsPublish eq '否' }"> checked="checked" </c:if>>
							<label for="radio2">否</label>
						</div>
					</div>
				</c:if>
				<c:if test="${obj eq null }">
					<div class="row">
						<div class="col-sm-2 radio radio-danger">
							<input type="radio" name="artIsPublish" id="radio1" value="是" checked="checked">
							<label for="radio1">是</label>
						</div>
						<div class="col-sm-2 radio radio-danger">
							<input type="radio" name="artIsPublish" id="radio2" value="否" >
							<label for="radio2">否</label>
						</div>
					</div>
				</c:if>
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">文章内容</label>
			<div class="col-sm-8">
				<textarea name="artContent" id="artcontent">
					${obj.artContent}
				</textarea>
			</div>
			<div class="Validform_checktip"></div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">上传图片</label>
			<div class="col-sm-9">
				<input onclick="MTools.chooesFile('#fileText')"
					class="input-text upload-url radius form-control" type="text" id="showFileName"
					readonly style="width: 30%;display: inline;"> 
					<a onclick="MTools.chooesFile('#fileText')" class="btn btn-primary radius"> 
					<i class="fa fa-cloud-upload"></i> 浏览文件 </a> 
					<!-- <input type="button" class='btn btn-primary radius'
					onclick="MTools.upFile('#submitBtn')" value="上传"> -->
					 <a id="selectInServices" class="btn btn-primary radius"> 
					 <i class="fa fa-cloud"></i> 远程图库
				</a>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2 control-label"></div>
			<div class="col-sm-9">
				<c:if test="${obj ne null }">
					<input type="hidden" value="${obj.artImage }" name="artImage"
						id="hiddenImg" />
					<img width="150" id='img_1' src="${obj.artImage }" />
				</c:if>
				<c:if test="${obj eq null }">
					<input type="hidden" name=artImage id="hiddenImg" />
					<img width="150" id='img_1' style="display: none" />
				</c:if>
			</div>
		</div>

		<div class="form-group ">
			<div class="col-sm-12 text-center">
				<a href="javascript:;" onclick="myForm.submit()"
					class="btn btn-success radius"><i class="fa fa-check"></i>保存</a>&nbsp;&nbsp;&nbsp;&nbsp; 
				<button type="button" class="btn btn-danger radius" onclick="MTools.closeForm()"><i class="fa fa-close"></i>取消</button>
			</div>
		</div>
	</form>

	<form class="form form-horizontal" method="post" style="display: none;"
		enctype="multipart/form-data" target="blankFrame"
		action="${path }/admin/uploadFile/doUpload?fileType=image&flag=img_1&hiddenId=hiddenImg">

		<input id="fileText" type="file" multiple name="file-1"
			onchange="MTools.selectFile('#showFileName','#fileText','#submitBtn')" /> <input
			type="submit" id="submitBtn"
			onclick="MTools.upolad('img_1','file_1')" />
	</form>
	<iframe src="" name="blankFrame" id="blankFrame"
		style="height: 100px; width: 100px; display: none;"> </iframe>

</div>

</body>
	<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
		var editor = null;
		var myForm=MForm.initForm({
			invokeUrl:"${path}/admin/webArticle/addOrModify",
			beforeSubmit:function(){
				editor.sync();
			},
			afterSubmit:function(){
				parent.myGrid.serchData();
			},
		});
		
		$(function() {		
			$(".select2").select2({'width':'100%'});
			MUI.createEdit("artContent", '100%', '400px', 'editor');
			MUI.openServiceImgSpace(function(url) {
				$("#img_1").attr("src", url).show();
				$("#hiddenImg").val(url);
			}, "#selectInServices");
			initFunctionList();
		});
		
		/**
		 * 初始文章类型列表
		 */
		function initFunctionList() {
			$.AjaxProxy({
				a : false,
				c : false
			}).invoke("${path}/admin/webArticleType/all", function(loj) {
				var id=0;
				if($("#artTypeId").val() != null){
					id=$("#artTypeId").val();
				}
				$("#parentId").createSelectTree(
						loj.attr("result").rows,
						{	id : "artTypeId",		// 选项的值
							parent : "artTypeParentId",	// 父节点值
							value : "artTypeName",	// 要显示的名称
							append : false,
							defaultValue : id,
							defaultHtml : '<option value="0" selected="selected">一级文章类别</option>'
						});
			});
			
		
		}
		
		
		function autoZy() {
			var text = editor.text();
			$("#zy-input").val(text.substring(0, 100));
		}
		function cleanZy() {
			$("#zy-input").val("");
		}
	</script>
</html>