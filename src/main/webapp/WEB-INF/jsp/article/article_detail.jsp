<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<title>${article.title}</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include flush="true" page="../include_head.jsp"/>
<link rel="stylesheet" href="/asset/eshop/css/common.css">
<link rel="stylesheet" href="/asset/eshop/css/another.css">
</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>
<!-- 内容开始 -->
<div class="container pad-top35">
	<!-- 
	${article.category.name}
	 -->
	 <div class="row">
        <div class="col-md-12 col-lg-9">
            <div class="panel panel-default pad-25">
                <h3 class="article-detail-title">${article.title}</h3>
                <div class="article-detail-info">
                    <span>管理员</span> 发表于 <span><fmt:formatDate value="${article.addTime}" pattern="yyyy-M-d HH:mm:ss" /></span>
                </div>
                ${article.content }
            </div>
         </div>
	</div>
</div>
<!-- 内容结束 -->
<jsp:include flush="true" page="../eshop/include_footer.jsp"/>
</body>
</html>
