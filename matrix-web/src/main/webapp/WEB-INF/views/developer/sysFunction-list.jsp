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
<link rel="shortcut icon" href="${path }/resource/images/favicon.ico">
<!--[if lt IE 8]>
  <meta http-equiv="refresh" content="0;ie.html" />
  <![endif]-->

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
	<style type="text/css">
	table tr td {
	border: none !important;
}

table tr th {
	border: none !important;
}
	</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content   ">
		<div class="row">
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>功能列表</h5>
						<div class="pull-right ">
							<a class="f-r text-success " onclick="openForm()"><i
								class="fa fa-plus c-success"></i> 添加功能</a>
						</div>
					</div>
					<div id="treeDemo" class="ibox-content ztree"
						style="min-height: 400px"></div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>编辑功能</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content" style="min-height: 400px">
						<form  class="form-horizontal dataform " style="display: none"
							id="dataform">
							<input type="hidden" name="fnId" id="functionId">
							<div class="form-group">
								<label class="col-sm-2 control-label">父级功能</label>
								<div class="col-sm-4">
									<select class="form-control select2 " size="1" name="fnParentId"
										id="fnParentId" style="width: 100%">
									</select>
								</div>
								<label class="col-sm-2 control-label">排序</label>
								<div class="col-sm-4">
									<input name="fnSequence" type="number" class="form-control"
										id="sortIndex">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">功能名称</label>
								<div class="col-sm-4">
									<input id="name" type="text" name="fnName" class="form-control " 
										datatype="*1-50" nullmsg="功能名称不能为空">
									<div class="Validform_checktip"></div>
								</div>
								<label class="col-sm-2 control-label">功能图标</label>
								<div class="col-sm-4">
									<input datatype="*1-50" id="iconText" name="fnIcon" ignore="ignore"
										 type="text" class="form-control "
										onclick="showIcon()">
									<div class="Validform_checktip"></div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">访问路径</label>
								<div class="col-sm-4">
									<input name="fnPath" id="fnPath" type="text"
										class="form-control" value="">
								</div>
								<label class="col-sm-2 control-label">当前状态</label>
								<div class="col-sm-4">
								<div class="switch" data-on="success" id="state-switch"  data-off="warning" data-on-label="启用" data-off-label="禁用">
							    <input type="checkbox" id="fnIsDisable"  />
								</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">功能CODE</label>
								<div class="col-sm-4">
									<input id="fnCode" type="text" name="fnCode" class="form-control " 
										datatype="*1-50" nullmsg="功能code不能为空">
									<div class="Validform_checktip"></div>
								</div>
								<label class="col-sm-2 control-label">是否在菜单栏显示</label>
								<div class="col-sm-4">
										<div class="radio radio-success radio-single radio-inline">
										<input name="fnShowMenu" type="radio" id="radio1" value="是" > <label for="radio1">显示</label>
										</div>
										<div class="radio radio-success radio-single radio-inline">
										<input name="fnShowMenu" type="radio" id="radio2" value="否" > <label for="radio2">不显示</label>
										</div>
								</div>
								
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">功能按钮</label>
								<div class="form-control-static col-sm-10" id="btns">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12 text-center">
									<a class="btn btn-success radius" onclick="updateFunction()"><i class="fa fa-check"></i> 保存</a>
									<a class="btn btn-danger  radius" onclick="delFunction()"><i class="fa fa-close"></i> 删除</a>
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
		
		//设置关闭点击事件
		$('#fnIsDisable').change(function () {
			if(flag){
				console.log("user click");
				var path="";
				var $fnIsDisable=$('#fnIsDisable');
				var checked=document.getElementById("fnIsDisable").checked;
				if(checked){
				//启用
					path="enableFunction";
				}else{
				//禁用
					path="disEnableFunction";
				}
				var id = $("#functionId").val();
				if (id != null && id != '') {
					$.AjaxProxy({
						p : {
							fnId : id
						}
					}).invoke("${path}/admin/sysFunction/"+path,
							function(loj) {
								layer.msg(loj.getValue("info"), {icon : 1,time:1000});
								return false;
							});
				}
			}else{
				console.log("system change");
				flag=true;
			}
		});
	});

	/**
	 * 初始化功能列表
	 */
	function initFunctionSelected(id) {
		console.log("parentid="+id);	
		$.AjaxProxy().invoke("${path}/admin/sysFunction/all",
			function(loj) {
				$("#fnParentId").createSelectTree(
					loj.attr("result").rows,
					{	id : "fnId",		// 选项的值
						parent : "fnParentId",	// 父节点值
						value : "fnName",	// 要显示的名称
						append : false,
						defaultValue : id,
						defaultHtml : '<option value="" >一级功能</option>'
					});
			});

	}
	//更新功能 
	function updateFunction() {
		var myForm = MForm.initForm({
			invokeUrl : "${path}/admin/sysFunction/addOrModify",
			afterSubmit : function() {
				initFunctionList();
			},
		});
		myForm.submit();
	}
	
	
	// 点击加载节点信息
	function zTreeOnClick(event, treeId, treeNode) {
		fnPublci.initFunctionSelect("#fnParentId");
		flag=false;
		$("#dataform").show();
		dataForm.resetForm();
		$.AjaxProxy({
					p : {
						fnId : treeNode.id
					},
					a : false,
					c : true
				})
				.invoke(
						"${path}/admin/sysFunction/findById",
						function(loj) {
							// 初始化功能基本信息1
							initFunctionSelected(loj.getString(0,"fnParentId"));
							$("#functionId").val(loj.getString(0, "fnId"));
							$("#name").val(loj.getString(0, "fnName"));
							$("#fnIcon").val(loj.getString(0, "fnIcon"));
							$("#fnPath").val(loj.getString(0, "fnPath"));
							$("#fnCode").val(loj.getString(0, "fnCode"));
							if(loj.getString(0,"fnShowMenu")=="是"){
								$("#radio1").trigger("click");
							}else{
								$("#radio2").trigger("click");
							}
							
							$("#sortIndex").val(
									loj.getString(0, "fnSequence"));
							if("否"==loj.getString(0,"fnIsDisable")){
								$('#state-switch').bootstrapSwitch('setState', true);
							}else{
								$('#state-switch').bootstrapSwitch('setState', false);
							}
							// 初始话功能按钮
						$.AjaxProxy({
										p : {
											id : loj.getString(0, "id")
										},
										a : false,
										c : false
									})
									.invoke(
											"${path}/admin/sysBtn/all",
											function(loj2) {
												var html=[];
												for (var i = 0; i < loj2
														.getRowCount(); i++) {// 全部功能按钮
													var isbtnselect = false;
													if (loj.getString(0,"btns") != null&&
															MTools.isContainId(loj.getString(0,"fnBtns"),loj2.getString(i,"btnValue"))) {
														isbtnselect = true;
													}
													if (isbtnselect) {
														html[i]= '<div class="checkbox checkbox-inline">  <input  name="fnBtns" type="checkbox" checked="checked" id="inlineCheckbox' 
																+ loj2.getString(i,"btnValue")+ '" value="'+ loj2.getString(i,"btnValue")
																+'" checked>   <label for="inlineCheckbox'+ loj2.getString(i,"btnValue")
																+ '"> '+ loj2.getString(i,"btnKey")+ ' </label> </div>';
													} else {
														html[i]= '<div class="checkbox checkbox-inline">  <input  name="fnBtns" type="checkbox" id="inlineCheckbox'
																+ loj2.getString(i,"btnValue")+ '" value="'+ loj2.getString(i,"btnValue")+ '"> <label for="inlineCheckbox'
																+ loj2.getString(i,"btnValue")+ '"> '+ loj2.getString(i,"btnKey")
																+ ' </label> </div>';
													}
												}
												$("#btns").html(html.join(""));
											});
						});
		
		
	};
	
	
	


	function initFunctionList() {
		var zNodes = new Array();
		$.AjaxProxy().invoke(
				"${path}/admin/sysFunction/all",
				function(loj) {
					for (var i = 0; i < loj.getRowCount(); i++) {
						zNodes[i] = createNode(loj.getString(i, "fnId"), loj.getString(i, "fnParentId"), loj.getString(
								i, "fnName"));
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
			o.open=true;
			o.icon="${path}/resource/images/function2.png"
			o.iconClose="${path}/resource/images/function1.png"
			o.iconOpen="${path}/resource/images/function2.png"
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

		function delFunction() {
			var id = $("#functionId").val();
			if (id != null && id != '') {
				layer.confirm('删除功能时会删除该功能的所有子功能！', {
					icon : 3,
					title : '提示'
				}, function(index) {
					layer.close(index);
					var id = $("#functionId").val();
					$.AjaxProxy({
						p : {
							fnId : id
						}
					}).invoke("${path}/admin/sysFunction/del", function(loj) {
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
				layer.msg("请先选中要删除的功能!");
			}
		}

		function openForm() {
			layer.open({
				type : 2,
				title : "新增功能",
				anim:1,
				maxmin : true,
				area : [ MUI.SIZE_L, '450px' ],
				content : [ '${path}/admin/sysFunction/editForm' ]
			});
		}
	</script>

</body>
</html>
</html>