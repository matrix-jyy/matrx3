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
	
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>科室与咨询方向</h5>
						<matrix:btn value="BusCatalog:add">
							<div class="pull-right ">
								<a class="f-r text-success " onclick="openForm()"><i
									class="fa fa-plus c-success"></i>添加</a>
							</div>
						</matrix:btn>
					</div>
					<div id="treeDemo" class="ibox-content ztree"
						style="min-height: 400px"></div>
				</div>
			</div>
		</div>
	</div>
    <script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
   <script type="text/javascript">
	
	
	var dataForm 
				= null;
				$(function() {
					initFunctionList();

				});


				function initFunctionList() {
					var zNodes = new Array();
					$.AjaxProxy().invoke(
							"${path}/admin/catalog/all",
							function(loj) {
								for (var i = 0; i < loj.getRowCount(); i++) {
									zNodes[i] = createNode(loj.getString(i,
											"cataId"), loj.getString(i,
											"parentId"), loj.getString(
											i, "cataName"));
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

				 

				//删除文章类型
				<matrix:btn value="BusCatalog:del">
				function delFunction() {
					var id = $("#id").val();
					var count = 0;
					$.AjaxProxy({
						a : false,
						p : {
							artTypeParentId : id
						}
					}).invoke("${path}/admin/webArticleType/all",
							function(loj) {
								count = loj.getRowCount();
							})
					if (count > 0) {
						layer.confirm("该文章类型下有子类型存在，删除类型时会删除该类型的所有子类型！", {
							icon : 3,
							title : '提示'
						}, function(index) {
							layer.close(index);
							$.AjaxProxy({
								p : {
									artTypeId : id
								}
							}).invoke("${path}/admin/webArticleType/delAll",
									function(loj) {
										layer.msg('删除成功！', {
											icon : 1,
											time : 1000
										}, function(index) {
											$("#dataform").hide();
											layer.close(index);
											initFunctionList();
										});
										return false;
									});
						});
					} else {
						layer.confirm('确定删除该文章类型！', {
							icon : 3,
							title : '提示'
						}, function(index) {
							$.AjaxProxy({
								p : {
									keys : id
								}
							}).invoke("${path}/admin/webArticleType/del",
									function(loj) {
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
				<matrix:btn value="BusCatalog:add">
				function openForm() {
					layer.open({
						type : 2,
						title : "添加文章类型",
						anim : 1,
						maxmin : true,
						area : [ MUI.SIZE_L, '450px' ],
						content : [ '${path}/admin/webArticleType/editForm' ]
					});
				}
				</matrix:btn>

				//更新功能 
				<matrix:btn value="BusCatalog:edit">
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
