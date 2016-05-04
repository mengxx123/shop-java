<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
</head>
  
<body>
<div class="mycontainer">
	<div class="row">
		<p>欢迎您，${manager.name}。</p>
		<p>欢迎使用Eshop管理平台${settings['test']}</p>
		<p>您上次登录的时间是：<fmt:formatDate value="${manager.latestLoginTime}" pattern="yyyy-M-d HH:mm" />。（不是您登录？点击<a href="#">修改密码</a>）</p>
	</div>
	
</div>
</body>
</html>
