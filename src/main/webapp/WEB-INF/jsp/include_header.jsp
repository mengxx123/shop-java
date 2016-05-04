<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 头部 -->
<header class="navbar navbar-dark bg-primary navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">	 	 <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="./" class="navbar-brand">${websiteName}</a>
        </div>
        <nav id="navbar-collapse" class="collapse navbar-collapse" role="navigation">
            <form class="navbar-form navbar-left" action="search.html" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="搜索问题、话题或人">
                </div>
                <button type="submit" class="btn btn-primary">搜索</button>
            </form>
            <ul class="nav navbar-nav">
                <c:if test="${isLogin == true}" >
                	<li class="nav-item"> <a class="nav-link" href="users/${userId}/messages" target="_blank">消息</a> </li>
                	<li class="nav-item"> <a class="nav-link" href="/admin">管理平台</a> </li>
				</c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
           		<c:if test="${isLogin == true}" >
           			<li class="nav-item dropdown">
			            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
			            	<img class="avatar avatar-xxs" src="${loginUser.headPath}">
			            	${loginUser.nickname} <i class="caret"></i>
			            </a>
			            <ul class="dropdown-menu">
			                <li><a href="users/${userId}" target="_blank">个人中心</a></li>
			                <li><a href="users/${userId}/friends" target="_blank">好友列表</a></li>
			                <li class="divider"></li>
			                <li><a id="loginout" href="javascript:;">退出登陆</a></li>
			            </ul>
			        </li>
				</c:if>
				<c:if test="${isLogin == false}" >
					<li class="nav-item"> <a class="nav-link" href="login">登陆</a> </li>
					<li class="nav-item"> <a class="nav-link" href="register">注册</a> </li>
				</c:if>
            </ul>
               	
            
        </nav>
    </div>
</header>
<!-- /头部 -->