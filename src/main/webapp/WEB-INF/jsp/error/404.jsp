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
<title>页面找不到</title>
<link rel="stylesheet" href="css/admin_common.css" />
<style>
.error-404 {
	float: left;
	font-size: 250px;
	overflow: hidden;
}
.content-right {
	padding-left: 20px;
	padding-top: 65px;
}
.sorry-text {
	font-size: 30px;
}
.back {
	margin-top: 20px;
	color: #f63;
	font-size: 25px;
}
@media only screen and (max-width: 800px) { 
.error-404 {
	font-size: 150px; 
}
.content-right {
	padding-left: 20px;
	padding-top: 35px;
}
}
</style>
</head>

<body>
<div class="admin-content">
	<div class="admin-section">
 		<div>
            <p class="error-404">404</p>
            <div class="content-right">
                <p class="sorry-text">很抱歉，您访问的页面找不到了！</p>
                <div><a class="back" action="/admin/main">返回起始页</a></div>
            </div>
        </div>
    </div> 
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script>
</script>
</body>
</html>
