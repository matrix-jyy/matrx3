<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<c:if test="${status eq 'ok' }">
	<script type="text/javascript">
		eval("parent.${callBack}('${inputId}','${url}')");
	</script>
</c:if>

<c:if test="${status ne 'ok' }">
	<script type="text/javascript">
	parent.layer.msg("${msg}", {icon:"${icon}"});
	</script>
</c:if>