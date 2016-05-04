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
<title>修改资料</title>

<jsp:include flush="true" page="../include_head.jsp"/>

<link rel="stylesheet" href="/asset/css/question.css">
</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/> 
<div class="container">
    <div class="row">
        <div class="col-sm-9">
        	<ul class="nav nav-tabs">
                <div class="nav-item active"><a class="nav-link" href="#tab11" data-toggle="tab">两字</a></div>
                <div class="nav-item"><a class="nav-link" href="#tab12" data-toggle="tab">三个字</a></div>
                <div class="nav-item"><a class="nav-link" href="#tab13" data-toggle="tab">四个字啊</a></div>
            </ul>
            <div class="panel panel-default">
                <header class="panel-heading">${user.name}</header>
                <div class="panel-body">
                	<a href="settings/account">修改密码</a>
                	<a href="settings/profile">基本设置</a>
                </div>
            </div>
            <div class="panel panel-default">
                <header class="panel-heading">提问</header>
                <div class="panel-body">
                    <p>用户名：${user.name}</p>
				 	<p>昵称：<input type="text" value="${user.nickname}" /></p>
				 	<p>邮箱：<input type="text" value="${user.email}" /></p>
				 	<input type="button" value="修改" />
				 	<p><a href="modify_password">修改密码</a></p>
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
