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
<!-- 本框架基本脚本和样式 -->
<script type="text/javascript"
	src="${path }/resource/js/plugin/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${path }/resource/js/systools/MBase.js"></script>
<style type="text/css">
.hkdg{
	width:150px; 
	margin: 5px;
	border: 1px solid #aeaeae;
	border-radius:5px;
	padding: 3px; 
}
blockquote{
font-size: 14px;
line-height: 25px;
}
.title{
color: #6f5499;
font-size: 32px;
text-shadow:2px 3px 2px #ababab;

}
</style>
</head>

<body>
	<div id="yourElement" class="ibox-content animated rotateInUpLeft">
	
		<h2 class="text-center title" >Zking Matrix V3.0</h2>
		<p class=" text-center">Welcome to use Zking Matrix .</p>
		<br>
		<blockquote class="center-block ">
			<h2 class=" text-primary">Api</h2>
			<p class=" ">&nbsp;&nbsp;提供系统中使用到的一些技术的API参考手册</p>
			<a href="http://geek.zkingsoft.com/api/redirect/index" target="_blank" > Matrix API手册 </a>
		</blockquote>
		<blockquote class="center-block ">
			<h2 class=" text-primary">Introduction</h2>
			<p class=" ">&nbsp;&nbsp;Matrix由zking同事姜友瑶发起，Matrix的目标是建立一个标准化，灵活易用支持快速开发的Java开发框架，满足公司的快速增长的业务需求。</p>
			<img alt=""  src="${path}/resource/images/hkdg.jpg" class="img-responsive hkdg" >
			<code>Matrix</code> 一词灵感来源于电影《黑客帝国》 在电影中，Matrix是一套极其复杂的模拟系统程序是黑客们苦苦找寻的母体，它由具有人工智能的机器建立的，模拟了人类的世界，虽然Matrix在电影中
			是个反派角色但是其实Matrix的本意是子宫、母体、孕育生命的地方。
			取用Matrix这个名字一方面表示希望这个框架能够茁壮成长,能够包罗万象成为一个很强大的框架，另一方面希望这个框架能够孕育出更多优秀的项目为公司添砖加瓦,让框架本身实现价值。
			<br>
			&nbsp;&nbsp;在此公司特别感谢为Matrix添砖加瓦的同事们，姜友瑶、谷春霞、丁川、罗袁弘、罗凯,毛禄广
			Matrix 的完善和成长需要每一位Zking同事的智慧，希望更多的同事愿意付出自己宝贵的时间可以加入到这份有意义的工作中。
			如果你有好idea或者NB的功能你觉得可以加入到Matrix框架中，欢迎到开发者中心Matrix api栏目中进行反馈。也可以与框架维护组联系。
		</blockquote>
		<blockquote class="center-block ">
			<h2 class=" text-primary">Attention</h2>
			<p class=" ">&nbsp;&nbsp;各位同事在使用本框架开发项目的时候请务必尽自己最大的努力遵循代码规范，包括文件命名、变量命名、注释风格等。参见：<a href="http://10.0.0.7:9898/KFC" target="blank">《卓景京Java开发规范》</a><br>
			遵循规范是一种职业素养，是对自己职业的尊重，开发中一方面要对自己的代码负责，另一方面也要对你的队友或者将来会接手你工作的同事负责，
			好的代码能让你名垂青史后人会说这个写的好改起来很方便，差的代码会让你遗臭万年。。。。<code>我*，这TM那个傻*写的代码，看都看不懂，怎么搞嘛！</code>
			所以不想时不时的打喷嚏还是写好你的代码吧。
			<br>
			</p>
		</blockquote>
		<p class="pull-right" >
			Document By 姜友瑶 <br>
			2016-6-26
		</p>
	</div>
</body>
<script type="text/javascript">
$('#yourElement').addClass('animated rotateInUpLeft');
</script>
</html>