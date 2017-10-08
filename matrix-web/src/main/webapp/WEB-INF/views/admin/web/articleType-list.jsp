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
<!-- 界面单独引入的其他样式和脚本 -->
<link rel="stylesheet"
	href="${path }/resource/plugin/zTree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${path }/resource/plugin/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/plugin/bootstrap-3.3.5/js/bootstrapSwitch.js"></script>
<link rel="stylesheet" href="${path }/resource/plugin/bootstrap-3.3.5/css/bootstrapSwitch.css">
	<script type="text/javascript"
	src="${path }/resource/js/function/public.js"></script>
	
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文章类别列表</h5>
                        <matrix:btn value="WebArticleType:add"> 
                        <div class="pull-right ">
                            <a  class="f-r text-success " onclick="openForm()"><i class="fa fa-plus c-success"></i> 添加文章类型</a>
                        </div>
                        </matrix:btn>
                    </div>
                    <div id="treeDemo"  class="ibox-content ztree"  style="min-height: 400px">
							
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑文章类别</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content" style="min-height: 400px">
                        <form style="display: none;" class="form-horizontal dataform "  id="dataform">
						<input type="hidden" name="artTypeId" id="id">
						<div class="form-group">
							<label class="col-sm-2 control-label">父级文章类别</label>
							<div class="col-sm-3">
								<select class="select2 form-control"  name="artTypeParentId" id="parentId" style="width: 100%">
									<option value="0" selected="selected">一级文章类别</option>
								</select>
							</div>
							<label class="col-sm-2 control-label">文章类别名称</label>
							<div class="col-sm-3">
								<input id="articleTypeName" name="artTypeName" type="text" class="form-control " nullmsg="文章类型名称不能为空"
									datatype="*1-50" errormsg="文章类型名称长度不能超过50">									
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">文章类型序号</label>
							<div class="col-sm-3">
								<input id="sort" name="artTypeSort" type="text" class="form-control " nullmsg="文章类型序号不能为空"
									datatype="n1-10" errormsg="文章类型序号不能超过10">					
							</div>
							<div class="Validform_checktip"></div>
							<label class="col-sm-2 control-label">文章类型描述</label>
							<div class="col-sm-3">
								<input id="description" name="artTypeDescription" type="text" class="form-control">					
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">文章类型图标</label>
							<div class="col-sm-3">
								<input id="icon" name="artTypeIcon"
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
			
						<div class="form-group" >
							<div class="col-sm-12 text-center">
								<matrix:btn value="WebArticleType:edit"> 
									<a class="btn btn-success radius" onclick="updateFunction()" ><i class="fa fa-check"></i> 保存</a>
								</matrix:btn>
								<matrix:btn value="WebArticleType:del"> 
									<a class="btn btn-danger  radius" onclick="delFunction()" ><i class="fa fa-close"></i>删除</a>	
								</matrix:btn>			
							</div>
						</div>
					</form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
   <script type="text/javascript">
	
	//做一个标记用来记录功能状态的改变是用户还是系统引起的
	var flag=false;
	
	var dataForm = null;
	$(function() {
		$(".select2").select2();
		initFunctionList();
		dataForm = MValidform.validform("#dataform");
	
	}); 

	/**
	 * 初始化功能列表
	 */
	function initFunctionSelected(id) {
		$.AjaxProxy().invoke("${path}/admin/webArticleType/all",
			function(loj) {
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
	
	
	
	
	// 点击加载节点信息
	function zTreeOnClick(event, treeId, treeNode) {
		flag=false;
		$("#dataform").show();
		dataForm.resetForm();
		$.AjaxProxy({
					p : {
						artTypeId : treeNode.id
					},
					a : false,
					c : true
				})
				.invoke(
						"${path}/admin/webArticleType/all",
						function(loj) {
							// 初始化功能基本信息1
							initFunctionSelected(loj.getString(0,"artTypeParentId"));
							$("#id").val(loj.getString(0, "artTypeId"));
							$("#articleTypeName").val(loj.getString(0, "artTypeName"));
							$("#icon").val(loj.getString(0, "artTypeIcon"));
							$("#sort").val(loj.getString(0, "artTypeSort"));
							$("#description").val(loj.getString(0, "artTypeDescription"));
						});
	};
	
	
	


	function initFunctionList() {
		var zNodes = new Array();
		$.AjaxProxy().invoke(
				"${path}/admin/webArticleType/all",
				function(loj) {
					for (var i = 0; i < loj.getRowCount(); i++) {
						zNodes[i] = createNode(loj.getString(i, "artTypeId"), loj.getString(i, "artTypeParentId"), loj.getString(
								i, "artTypeName"));
					}
					initTree(zNodes);
				});
	}

		//初始化树型控件
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

		function createNode(id, parentId, name) {
			var o = new Object();
			o.id = id;
			o.pId = parentId;
			o.name = name;
			return o;
		}

		function showIcon() {
			layer.open({
				type : 2,
				title : false,
			area : [ '80%', '80%' ],
				fix : true, // 不固定
				shadeClose: true,
				  shade: 0.8,
				maxmin : true,
				content : '${path}/common/redirect/icons'
			});
		}
		
		//删除文章类型
		<matrix:btn value="WebArticleType:del"> 
		function delFunction() {
			var id = $("#id").val();
			var count=0;
			$.AjaxProxy({a :false,p : {artTypeParentId : id}}).invoke("${path}/admin/webArticleType/all", function(loj) {
				count=loj.getRowCount();
			})
			if (count > 0) {
				layer.confirm("该文章类型下有子类型存在，删除类型时会删除该类型的所有子类型！", {icon : 3,title : '提示'}, function(index) {
					layer.close(index);
					$.AjaxProxy({p : {artTypeId : id}}).invoke("${path}/admin/webArticleType/delAll", function(loj) {
								layer.msg('删除成功！', {
									icon : 1,time:1000
								}, function(index) {
									$("#dataform").hide();
									layer.close(index);
									initFunctionList();
								});
								return false;
							});
						});
			} else {
				layer.confirm('确定删除该文章类型！',{icon : 3,title:'提示'},function(index) {
					 $.AjaxProxy({p : {keys : id}}).invoke("${path}/admin/webArticleType/del", function(loj) {
						layer.msg('删除成功！', {
							icon : 1
						}, function(index) {
							$("#dataform").hide();
							layer.close(index);
							initFunctionList();
						});
					});
				});
			}
		}
		</matrix:btn>
		//添加文章类型
		<matrix:btn value="WebArticleType:add"> 
		function openForm() {
			layer.open({
				type : 2,
				title : "添加文章类型",
				anim:1,
				maxmin : true,
				area : [ MUI.SIZE_L, '450px' ],
				content : [ '${path}/admin/webArticleType/editForm' ]
			});
		}
		</matrix:btn>
	
		//更新功能 
		<matrix:btn value="WebArticleType:edit"> 
		function updateFunction() {
			var myForm = MForm.initForm({
				invokeUrl : "${path}/admin/webArticleType/addOrModify",
				afterSubmit : function() {
					initFunctionList();
				},
			});
			myForm.submit();
		}
		</matrix:btn>
	</script>
	
</body>
</html>
