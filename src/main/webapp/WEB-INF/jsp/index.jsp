<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>eSchool - 校园信息系统</title>
<meta name="keywords" content="Eshop,购物" />
<meta name="description" content="Eshop，致力于打造一个优秀的网上购物商城，为你提供一个更加人性化的购物体验" />

<jsp:include page="include_head.jsp"></jsp:include>

</head>
<body>
<jsp:include flush="true" page="include_header.jsp"/> 
<div class="container">
	<div class="row">
		<div class="col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading">校园论坛</div>
				<div class="panel-body">
					<a href="forum">论坛系统</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">校园问答</div>
				<div class="panel-body">
					<a href="questions">问答系统</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">校园二手</div>
				<div class="panel-body">
					<a href="ershou">二手市场</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">校园云</div>
				<div class="panel-body">
					<p>学习资源分享平台</p>
					<a href="questions">问答系统</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">校园群组</div>
				<div class="panel-body">
					<a href="groups">群首页</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">其他</div>
				<div class="panel-body">
					
					<a href="exam">题库</a>
					<a href="votes/1">投票</a>
					<a href="login">登陆</a>
					<a href="api/v1" target="_blank">API</a>
					<a href="feedbacks" target="_blank">反馈</a>
					<p>在线人数：${online}</p>
				</div>
			</div>
			
			
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">友情链接</div>
				<div class="panel-body">
					<a href="schools/1">广二师</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include flush="true" page="include_footer.jsp"/> 

<script>
$("#hot-keyword").click(function() {
	var keyword = $(this).text();
	keyword = encodeURI(keyword);
	window.location = 'search?keyword=' + keyword; 
	//alert(keyword);
});

</script>
</body>
</html>

