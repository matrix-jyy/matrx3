<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<title>文件上传</title>

</head>
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
<body>
	<form class="form-horizontal" id="dataform"  method="post" action="1231"
		>
		<br> <br>
		<div class="form-group">
			<label class="col-sm-2 control-label">上传图片</label>
			<div class="col-sm-6">
				<input name="img" id="a" class="form-control  upload-input" type="text" />
				<a class="btn btn-primary radius upload-a"> <i
					class="fa fa-cloud-upload"></i> 浏览文件
				</a>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上传图片</label>
			<div class="col-sm-6">
				<input name="img2" id="b" class="form-control upload-input" type="text" />
				<a class="btn btn-primary radius upload-a"> <i
					class="fa fa-cloud-upload"></i> 浏览文件
				</a>
			</div>
		</div>
		<button>提交</button>
	</form>


	<script type="text/javascript"
		src="${path }/resource/js/systools/MJsBase.js"></script>
	<script type="text/javascript">
	
		MUI.initImgUpload(".upload-input");
		
	</script>

</body>
</html>