<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>

<jsp:include flush="true" page="../include_head.jsp"/>

<link rel="stylesheet" href="/asset/css/question.css">
</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/> 
<div class="container">
    <div class="row">
        <div class="col-sm-9">
            <div class="panel panel-default">
                <header class="panel-heading">用户信息</header>
                <div class="panel-body">
                	<img class="avatar avatar-sm" src="${user.headPath}">
                	<h2>${user.nickname}</h2>
                	<p>个人简介：${user.description}</p>
                	<c:if test="${userId == user.id}">
                		<a class="btn btn-success" href="users/edit">编辑资料</a> 
                	</c:if>
                </div>
            </div>
            <div class="panel panel-default">
                <header class="panel-heading">提问</header>
                <div class="panel-body">
                    
                </div>
            </div>
        </div>
        <div class="col-sm-3">
			<div class="panel panel-default">
                <header class="panel-heading">提问</header>
                <div class="panel-body">
                    
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/asset/lib/jquery/jquery-1.10.2.min.js"></script>
<script src="/asset/lib/eui/eui.min.js"></script>
</body>
</html>
