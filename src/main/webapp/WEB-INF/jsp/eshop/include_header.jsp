<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 头部 -->
<header class="navbar navbar-light es-header navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">	 	 <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">${websiteName}</a>
        </div>
        <nav id="navbar-collapse" class="collapse navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li class="nav-item active"> <a class="nav-link" href="./">首页</a> </li>
                <li class="nav-item"> <a class="nav-link" href="#">求购</a> </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            	<c:if test="${isLogin == false}" >
	                <li class="nav-item"> <a id="login" class="nav-link" data-link href="javascript:;">登陆</a> </li>
	                <li class="nav-item"><a class="nav-link" href="register">注册</a></li>
            	</c:if>
                <c:if test="${isLogin == true}" >
                	<li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
		                   	${loginUser.nickname} 
		                   <b class="caret"></b>
		                </a>
		                <ul class="dropdown-menu">
		                	<li><a href="users/${userId}" target="_blank">个人中心</a></li>
		                	<li><a href="goods_collection">商品收藏</a></li>
		                	<li><a href="shop_collection">店铺收藏</a></li>
		                	<li class="divider"></li>
		                	<li><a id="loginout" href="javascript:;">退出</a></li>
		                </ul>
		             </li>
				</c:if>
            </ul>
        </nav>
    </div>
</header>
<!-- /头部 -->