<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/admin_common.css" />
</head>
  
  <body>
    关于信息. <br>
  </body>
</html>
