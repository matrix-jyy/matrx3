<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>主页</title>
    <link rel="shortcut icon" href="favicon.ico">
    
    <!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>

<!-- 界面单独引入的其他样式和脚本 -->
<link rel="stylesheet"
	href="${path }/resource/plugin/zTree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${path }/resource/plugin/zTree/jquery.ztree.core-3.5.min.js"></script>
	
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

<body class="gray-bg">
<nav class="huiNav">
		<i class="fa fa-home"></i> 首页 <span>&gt;</span>企业网站管理<span>&gt;</span>导航栏管理
		<a  class="btn btn-info  btn-sm pull-right" href="javascript:location.replace(location.href);" title="刷新" ><i class="fa fa-refresh"></i></a>
</nav>
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>导航栏列表</h5>
                        <div class="pull-right ">
                            <a  class="f-r text-success " onclick="openForm()"><i class="fa fa-plus c-success"></i> 添加导航栏</a>
                        </div>
                    </div>
                    <div id="treeDemo"  class="ibox-content ztree"  style="min-height: 400px">
							
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑导航栏</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content" style="min-height: 400px">
                        <form style="display: none;" class="form-horizontal dataform "  id="dataform">
						<input type="hidden" name="navId" id="navId">
						<div class="form-group">
							<label class="col-sm-2 control-label">父级导航栏</label>
							<div class="col-sm-3">
								<select class="select2 form-control " size="1" name="parentId" id="parentId">
									<option value="0" selected="selected">|ROOT</option>
								</select>
							</div>
							<label class="col-sm-2 control-label">导航名称</label>
							<div class="col-sm-3">
								<input id="navName" name="navName" type="text" class="form-control " nullmsg="导航名称不能为空"
									datatype="*1-50" errormsg="导航名称长度不能超过50">									
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">导航序号</label>
							<div class="col-sm-3">
								<input id="navOrderby" name="navOrderby" type="text" class="form-control " nullmsg="导航序号不能为空"
									datatype="n1-10" errormsg="导航序号不能超过10">					
							</div>
							<div class="Validform_checktip"></div>
							<label class="col-sm-2 control-label">导航描述</label>
							<div class="col-sm-3">
								<input id="navDescription" name="navDescription" type="text" class="form-control">					
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">导航类型</label>
							<div class="col-sm-3">
								<select class="select2 form-control" size="1" name="navType" id="navType">
								<option value="0" selected="selected">--请选择导航类型--</option>
								</select>
							</div>
							
							<label class="col-sm-2 control-label">导航链接地址</label>
							<div class="col-sm-3">
								<input id="navUrl" name="navUrl" type="text" class="form-control " nullmsg="导航链接地址不能为空"
									datatype="*1-1000" errormsg="导航链接地址不能超过1000">					
							</div>
							<!-- <div class="Validform_checktip"></div>
							<label class="col-sm-1 control-label">展示图片</label>
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
						<div class="form-group" >
							<div class="col-sm-12 text-center">
								<a class="btn btn-success radius" onclick="updateFunction()" >保存</a>
								<a class="btn btn-danger  radius" onclick="delFunction()" >删除</a>				
							</div>
						</div>
					</form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form class="form form-horizontal" method="post" style="display: none;"
		enctype="multipart/form-data" target="blankFrame"
		action="${path }/resource/plugin/kindeditor/zking_upload_json.jsp?fileType=image&hiddenId=hiddenImg&flag=img_1">

		<input id="fileText" type="file" multiple name="file-1"	onchange="MTools.selectFile('#showFileName','#fileText','#submitBtn')" /> 
		<input type="submit" id="submitBtn"/>
	</form>
	
	<iframe src="" name="blankFrame" id="blankFrame"
		style="height: 100px; width: 100px; display: none;"> </iframe>
  <script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
   <script type="text/javascript">
	
   
   var Huiform = null;
	$(function() {
		MTools.autoFullSelect();
		initFunctionList();
		$(".select2").select2({'width':'100%'});
		Huiform = MValidform.validform("#dataform");
		MUI.openServiceImgSpace(function(url) {
			$("#img_1").attr("src", url).show();
			$("#hiddenImg").val(url);
			//$("#submitBtn").trigger("click");
		}, "#selectInServices");
	});

	/**
	 * 初始化文章类别列表
	 */
	function initFunctionSelected(id) {
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
	
	function initFunctionSelected1(id) {
		$.AjaxProxy().invoke("${path}/admin/webTypeNav/showParentType",
		function(loj) {
			$("#navType").createSelectTree(
				loj.attr("result").rows,
				{	id : "cataId",		// 选项的值
					parent : "parentId",	// 父节点值
					value : "cataName",	// 要显示的名称
					append : false,
					defaultValue : id,
					defaultHtml : '<option value="0" selected="selected">--请选择导航类型--</option>'
				});
		});
	}
	
	function initFunctionList() {
		var zNodes = new Array();
		$.AjaxProxy().invoke(
				"${path}/admin/webNav/all",
				function(loj) {
					for (var i = 0; i < loj.getRowCount(); i++) {
						zNodes[i] = createNode(loj.getString(i, "navId"), loj
								.getString(i, "parentId"), loj.getString(i, "navName"));
					}
					initTree(zNodes);
				});
	}

	function updateFunction() {
		var myForm=MForm.initForm({
			invokeUrl:"${path}/admin/webNav/addOrModify",
			afterSubmit:function(){
				initFunctionList();
			},
		});
		myForm.submit();
	}

	function initTree(zNodes) {
		var setting = {
			view : {
				dblClickExpand : false,
				showLine : false,
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : ""
				}
			},
			callback : {
				onClick : zTreeOnClick
			}
		};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}

	// 点击加载节点信息
	function zTreeOnClick(event, treeId, treeNode) {
		$("#dataform").show();
		Huiform.resetForm();
		$.AjaxProxy({
			p:{id : treeNode.id },
			a : false,
			c : true
		}).invoke(
				"${path}/admin/webNav/findById",
				function(loj) {
					// 初始化功能基本信息
					initFunctionSelected(loj.getString( 0, "parentId"));
					$("#navId").val(loj.getString( 0, "navId"));
					$("#navName").val(loj.getString( 0, "navName"));
					$("#navOrderby").val(loj.getString( 0, "navOrderby"));
					$("#navDescription").val(loj.getString( 0, "navDescription"));
					$("#navIcon").val(loj.getString( 0, "navIcon"));
					initFunctionSelected1(loj.getString( 0, "navType"));
					$("#navUrl").val(loj.getString( 0, "navUrl"));
					
				}); 
	};

	function createNode(navId, parentId, navName) {
		var o = new Object();
		o.id = navId;
		o.pId = parentId;
		o.name = navName;
		return o;
	}
	function showIcon() {
		layer.open({
			type : 2,
			title : "选择图标",
			area : [ '80%', '80%' ],
			fix : true, // 不固定
			maxmin : true,
			content : '${path}/common/redirect/icons'
		});
	}

	function delFunction() {
		var id = $("#navId").val();
		if (id != null && id != '') {
			layer.confirm('删除功能时会删除该功能的所有子功能！', {
				icon : 3,
				title : '提示'
			}, function(index) {
				layer.close(index);
				var id = $("#navId").val();
				$.AjaxProxy({
					p : {keys: id }
				}).invoke("${path}/admin/webNav/del", function(loj) {
					layer.alert('删除成功！', {
						icon : 1
					}, function(index) {
						$("#dataform").hide();
						layer.close(index);
						initFunctionList();
					});
					return false;
				});
			});
		} else {
			layer.alert("请先选中要删除的功能！");
		}
	}

	function openForm() {
		layer.open({
			type : 2,
			title : "添加导航",
			area : [ MUI.SIZE_L, '400px' ],
			content : '${path}/admin/webNav/editForm'
		});
	}

	
	
	
	</script>
	
</body>
</html>