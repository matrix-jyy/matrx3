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
	<style type="text/css">
.permission-list>dd>dl>dt {width：140px；}

#powerBox{
	max-height: 500px;
	overflow: scroll;
	overflow-x:hidden;
	border: 1px solid #aeaeae;
	border-radius:5px;
	padding:0;
}

</style>
</head>

<body>
	<div class="ibox-content">
		<form class="form-horizontal" id="dataform"
			onsubmit="javascripr:return false;">
			<c:if test="${obj ne null }">
				<input type="hidden" name="roleId" value="${obj.roleId }">
			</c:if>
			<input type="hidden" name="tokenUrl" value="${tokenUrl}">	 
		<input type="hidden" name="token" value="${token}">
			<div class="form-group">
				<label class="col-sm-2 control-label">角色名称<span class="text-danger">*</span></label>
				<div class="col-sm-4">
					<input type="text" dataType="*1-50" class="form-control"
						value="${obj.roleName }" name="roleName" datatype="*4-16"
						nullmsg="角色名称不能为空">
					<div class="Validform_checktip"></div>
				</div>
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" value="${obj.roleRemark }"
						name="roleRemark" maxlength="100">
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-2 control-label">可登录系统</label>
				<div class="col-sm-8">
					<c:forEach var="plat" items="${plats }" varStatus="count">
						<div class="checkbox checkbox-success checkbox-inline">
							<input id="checkbox${count.index }" type="checkbox"
								value="${plat.platCode }" name="plates"
								<c:if test="${fn:contains(obj.plates,plat.platCode)}">  checked="checked"  </c:if>>
							<label for="checkbox${count.index }"> <c:out
									value="${plat.platName }"></c:out>
							</label>
						</div>
					</c:forEach>
				</div>
			</div>
			<c:set value="${5 }" var="count"></c:set>
			<c:set value="${0}" var="index"></c:set>
			<div class="form-group">
				<label class="col-sm-2 control-label">功能权限</label>
				<div class="  col-sm-10" id="powerBox">
					<c:forEach var="oneFn" items="${functions }" varStatus="oneCount">
						<c:set value="${count+1 }" var="count"></c:set>
						<c:set value="${index+1 }" var="index"></c:set>
						<dl class="permission-list">
							<dt>
								<!-- 一级功能 -->
								<div class="checkbox checkbox-success">
									<input id="checkbox${count}" type="checkbox"
									<c:if test="${oneFn.hasThisFn}">  checked="checked"  </c:if>
										name="fnList[${index}].fnId" value="${oneFn.fnId }"> <label
										for="checkbox${count}"> ${oneFn.fnName }</label>
								</div>
							</dt>
							<dd>
								<c:forEach var="towFn" items="${oneFn.childs }">
									<c:set value="${count+1 }" var="count"></c:set>
									<c:set value="${index+1 }" var="index"></c:set>
									<dl class="cl permission-list2">
										<dt>
											<!-- 二级功能 -->
											<div class="checkbox checkbox-primary">
												<input class="secondFunction" type="checkbox" value="${towFn.fnId }"
												<c:if test="${towFn.hasThisFn}">  checked="checked"  </c:if>
													name="fnList[${index}].fnId"  id="checkbox${count}">
												<label for="checkbox${count}"> <c:out
														value="${towFn.fnName }"></c:out></label>
											</div>
										</dt>
										<dd>
											<c:forEach var="btn" items="${towFn.sysBtns}" varStatus="each">
												<c:set value="${count+1 }" var="count"></c:set>
												<!-- 二级功能按钮 -->
												<div class="checkbox checkbox-inline checkbox-info">
													<input type="checkbox" value="${btn.btnValue}" name="fnList[${index}].fnBtns"
														<matrix:findInSet setStr="${towFn.fnBtns}" id="${btn.btnValue}" > checked="checked" </matrix:findInSet>
														id="checkbox${count}"> <label
														for="checkbox${count}"> <c:out
															value="${btn.btnKey}"></c:out>
													</label>
												</div>
											</c:forEach>
										</dd>
									</dl>
								</c:forEach>
							</dd>
						</dl>
					</c:forEach>
				</div>
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
		$(function() {
			myForm = MForm.initForm({
				invokeUrl : "${path}/admin/sysRole/addOrModify",
				afterSubmit : function() {
					parent.myGrid.serchData();
				},
			});
			initEvent();
		});
		// 初始化点击事件
		function initEvent() {
			//点击一级目录
			$(".permission-list>dt input:checkbox").click(
					function() {
						$(this).closest("dl").find("dd input:checkbox").prop(
								"checked", $(this).prop("checked"));
					});
			//点击二级目录
			$(".permission-list2>dt input:checkbox")
					.click(
							function() {
								$(this).closest("dl").find("dd input:checkbox")
										.prop("checked",
												$(this).prop("checked"));
								var l2 = $(this).parents(".permission-list")
										.find(".permission-list2 dd").find(
												"input:checked").length;
								if (l2 == 0) {
									$(this).closest("dd").prev("dt").find(
											"input:checkbox").prop("checked",
											false);
								} else {
									$(this).closest("dd").prev("dt").find(
											"input:checkbox").prop("checked",
											true);
								}

							});

			//点击按钮 判断了按钮为0的情况如果按钮为0则取消当前二级目录选择，同时还判断是否取消一级目录
			$(".permission-list2 dd input:checkbox")
					.click(
							function() {
								var l = $(this).parent().parent().find(
										"input:checked").length;
								var l2 = $(this).parents(".permission-list")
										.find(".permission-list2 dd").find(
												"input:checked").length;
								if ($(this).prop("checked")) {
									$(this).closest("dl").find(
											"dt input:checkbox").prop(
											"checked", true);
									$(this).parents(".permission-list").find(
											"dt").first()
											.find("input:checkbox").prop(
													"checked", true);
								} else {
									if (l == 0) {
										$(this).closest("dl").find(
												"dt input:checkbox").prop(
												"checked", false);
									}
									if (l2 == 0) {
										$(this).parents(".permission-list")
												.find("dt").first().find(
														"input:checkbox").prop(
														"checked", false);
									}
								}

							});
		}
	</script>
</body>
</html>