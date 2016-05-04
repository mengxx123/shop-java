<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--商城公共css --%>
<link rel="stylesheet" href="/asset/lib/eui/eui.min.css">
<link rel="stylesheet" href="/asset/css/eadmin.css">
<link rel="stylesheet" href="/asset/lib/fontAwesome/font-awesome.min.css">
<link rel="stylesheet" href="/asset/eshop/css/common.css">
<link rel="stylesheet" href="/asset/eshop/css/another.css">