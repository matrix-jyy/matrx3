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
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-success">
					<div class="panel-heading">新增数据</div>
					<div class="panel-body">
						<form class="form-horizontal" id="dataform"
							onsubmit="javascripr:return false;">
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名称<span
									class="text-danger">*</span></label>
								<div class="col-sm-4">
									<input type="text" dataType="*1-100" class="form-control"
										name="su_name" datatype="*1-16" nullmsg="用户名称不能为空">
								</div>
								<label class="col-sm-2 control-label">用户角色<span
									class="text-danger">*</span></label>
								<div class="col-sm-4">
									<select class="form-control autoFull select2" dataType="*"
										nullmsg="角色不能为空" multiple="multiple"
										data-url="${path}/admin/sysRole/showCompanyRole"
										data-value="roleId" data-filed="roleName" name="role_ids">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">账号<span
									class="text-danger">*</span></label>
								<div class="col-sm-4">
									<input type="text" dataType="*1-510" class="form-control"
										name="su_account" nullmsg="账号不能为空">
								</div>
								<label class="col-sm-2 control-label">联系电话<span
									class="text-danger">*</span></label>
								<div class="col-sm-4">
									<input type="text" dataType="m" class="form-control"
										name="su_tel" datatype="*1-16" nullmsg="联系电话不能为空">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Email<span
									class="text-danger">*</span></label>
								<div class="col-sm-4">
									<input type="text" dataType="e" class="form-control"
										name="su_email" nullmsg="Email不能为空">
								</div>
								<label class="control-label  col-sm-2">密码<span
									class="text-danger">*</span></label>
								<div class="formControls  col-sm-4">
									<input type="text" name="su_password" value="123"
										class="form-control radius" dataType="*1-50">
								</div>
							</div>
							<div class="form-group ">
								<div class="col-sm-12 text-center">
									<a href="javascript:;" onclick="myForm.submit()"
										class="btn btn-success radius"><i class="fa fa-check"></i>
										保存</a>&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-danger radius"
										href="javascript:;" onclick="MTools.closeForm()"><i
										class="fa fa-close"></i> 关闭</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-success">
					<div class="panel-heading">新增数据</div>
					<div class="panel-body">

						<!-- 搜索框部分start -->
						<matrix:btn value="user:search">
							<div class="row">
								<div class="col-sm-12">
									<form class="form-inline" id="serchform">
										<div class="input-group">
											<div class="btn-group search-list " data-for="search-text">
												<button type="button"
													class="btn btn-default dropdown-toggle searchlist"
													data-toggle="dropdown">
													用户姓名 <span class="caret "></span>
												</button>
												<ul class="dropdown-menu" role="menu">
													<li data-field="su_name"><a>用户姓名</a></li>
													<li data-field="su_account"><a>账号 </a></li>
												</ul>
											</div>
											<div class="form-group mr-20">
												<input id="search-text" name="su_name" placeholder="输入查询关键词"
													type="text" class="form-control">
											</div>
											<div class="form-group mr-20">
												<button onclick="myGrid.serchData()" type="button"
													class="btn btn-info">
													<i class="fa fa-search "></i> 搜索
												</button>
												<button type="reset" class="btn btn-info ">
													<i class="fa fa-refresh "></i> 重置
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</matrix:btn>
						<!-- 搜索框部分en -->
						<div class="ibox-content radius-5 mt-5 mpanel">

							<div class="row">
								<div class="col-sm-12">
									<div class="option-bar">
										<matrix:btn value="user:dels">
											<button type="button" class="btn btn-danger btn-sm">
												<i class="fa fa-trash"></i>批量删除
											</button>
										</matrix:btn>
										<matrix:btn value="user:add">
											<button onclick="openAdd()" type="button"
												class="btn btn-success btn-sm">
												<i class="fa fa-plus"></i> 新增
											</button>
										</matrix:btn>
									</div>
									<table id="mgrid">
										<thead>
											<tr>
												<th data-checkbox="true"></th>
												<th data-formatter="MGrid.indexfn" data-align="center"
													data-width="30px">序号</th>
												<th data-field="su_name" data-sortable="true">姓名</th>
												<th data-field="su_account">账号</th>
												<th data-field="su_tel">电话</th>
												<th data-field="su_register_time"
													data-formatter="MGrid.getTime" data-sortable="true">注册时间</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="${path }/resource/js/systools/MJsBase.js"></script>
<script type="text/javascript">
	MTools.autoFullSelect();
	var myGrid;
	$(".select2").select2();
	var myForm = MForm.initForm({
		invokeUrl : "${path}/simplemvc/add",
		afterSubmit : function() {
			parent.myGrid.serchData();
		},
	});

	var delPath = "";
	delPath = "${path}/admin/del";
	myGrid = MGrid.initGrid({
		url : "${path}/simplemvc/showList",
		delUrl : delPath
	});
</script>
</body>
</html>