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

<!-- 界面单独引入的其他样式和脚本 -->
<link rel="stylesheet"
	href="${path }/resource/plugin/zTree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${path }/resource/plugin/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/plugin/zTree/jquery.ztree.excheck.min.js"></script>
</head>
<body>
	<div class="ibox-content">
		<form class="form-horizontal" id="dataform"
			onsubmit="javascripr:return false;">
			<input type="hidden" name="tokenUrl" value="${tokenUrl}">	 
		<input type="hidden" name="token" value="${token}">
			<c:if test="${obj ne null }">
				<input type="hidden" name="comId" value="${obj.comId }">
			</c:if>
			<div class="form-group">
				<label class="col-sm-2 control-label">公司名称<span class="text-danger">*</span></label>
				<div class="col-sm-4">
					 <input type="text" dataType="*1-100" class="form-control"
						name="comName" value="<c:out value="${obj.comName }" ></c:out >"  datatype="*1-16"
						nullmsg="公司名称不能为空">
					<div class="Validform_checktip"></div>
				</div>
				<label class="col-sm-2 control-label">联系人<span class="text-danger">*</span></label>
				<div class="col-sm-4">
					<input type="text" dataType="*1-50" class="form-control"
						value="<c:out value="${obj.comBoss }" ></c:out >" name="comBoss" nullmsg="联系人不能为空">
					<div class="Validform_checktip"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-4">
					<input type="text" dataType="m" class="form-control" name="comBossTel"
						ignore="ignore" value="<c:out value="${obj.comBossTel }" ></c:out >" datatype="*1-16"
						nullmsg="联系电话不能为空">
					<div class="Validform_checktip"></div>
				</div>
				<label class="col-sm-2 control-label">地址</label>
				<div class="col-sm-4">
					<input type="text" dataType="*1-510" class="form-control"
						ignore="ignore" value="<c:out value="${obj.comAddress }" ></c:out >" name="comAddress"
						nullmsg="地址不能为空">
					<div class="Validform_checktip"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">网站地址</label>
				<div class="col-sm-4">
					<input type="text" dataType="url" class="form-control"
						ignore="ignore" value="<c:out value="${obj.comWebUrl }" ></c:out >" name="comWebUrl"
						nullmsg="网站不能为空">
					<div class="Validform_checktip"></div>
				</div>
				<label class="col-sm-2 control-label">拥有应用<span class="text-danger">*</span></label>
			<div class="col-sm-4">
				<select class="form-control autoFull select2" dataType="*" nullmsg="应用不能为空" 
				multiple="multiple" 
				data-url="${path}/admin/sysPlat/all"	 
				data-value="platId"
				data-filed="platName"
				data-def="${obj.comPlats}"  
				name="comPlats">  
				<option value=''>--选择企业应用--</option>
				</select>
			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">企业功能</label>
				<div class=" ztree col-sm-9" id="treeDemo"></div>
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
	<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
		var myForm = null;
		var tree=null;
		$(function() {
			MTools.autoFullSelect();
			$(".select2").select2({'width':'100%'});
			myForm = MForm.initForm({
				invokeUrl : "${path}/admin/sysCompany/addOrModify",
				beforeSubmit:function(){
					var items=tree.getCheckedNodes();
					var keys="";
					for(var i=0 ; i<items.length ; i++){
							keys+=items[i]["id"]+",";
					}
					myForm.initParam.extendParam={"comFunctions":keys};
				},
				afterSubmit : function() {
					parent.myGrid.serchData();
				},
			});
			initFunctionList();
		});

		function initFunctionList() {
			var zNodes = new Array();
			$.AjaxProxy().invoke(
					"${path}/admin/sysFunction/all",
					function(loj) {
						for (var i = 0; i < loj.getRowCount(); i++) {
							zNodes[i] = createNode(loj.getString(i, "fnId"), loj
									.getString(i, "fnParentId"), loj.getString(i,
									"fnName"));
						}
						initTree(zNodes);
					});
		}

		function initTree(zNodes) {
			var setting = {
				check : {
					enable : true,
					chkStyle: "checkbox",
					radioType: "level",
					chkboxType : { "Y" : "ps", "N" : "ps" }
				},
				view : {
					dblClickExpand : false,
					showLine : true,
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
			tree=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}

		function createNode(id, parentId, name) {
			var o = new Object();
			o.id = id;
			o.pId = parentId;
			o.name = name;
			o.open=true;
			//如果是编辑则设置节点选中
			<c:if test="${obj ne null }">
				var fns="${obj.comFunctions}";
				if(fns.indexOf(id)!=-1){
					o.checked=true; 	
				}
			</c:if>
			return o;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>