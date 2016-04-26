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
<title>店铺收藏</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/shop_collection.css" />
</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>  
<!-- 内容开始 -->
<div class="content">
	<div class="nav">
        <div class="nav-header">我的交易</div>
        <div class="nav-content">
            <ul>
                <li><a href="">我的订单</a></li>
                <li><a href="">我的积分</a></li>
                <li><a href="">我的抵用券</a></li>
                <li><a href="">账户余额</a></li>
            </ul>
        </div>
        <div class="nav-header">我的收藏</div>
        <div class="nav-content">
            <ul>
                <li><a href="">商品收藏</a></li>
                <li><a class="item-active" href="">店铺收藏</a></li>
            </ul>
        </div>
        <div class="nav-header">会员资料</div>
        <div class="nav-content">
            <ul>
                <li><a href="">个人资料</a></li>
                <li><a href="">地址管理</a></li>
                <li><a href="">安全设置</a></li>
            </ul>
        </div>
	</div>
    <div class="content-right">
    	<h2>店铺收藏</h2> 
    	<c:choose>  
			<c:when test="${fn:length(collections) == 0}">     
				<div><p class="no-collection">暂无收藏<p></div>
			</c:when>  
			<c:otherwise>
				<ul class="shop-list">
					<c:forEach var="collection" items="${collections}">   
						<li class="shop">
			            	<span><a href="shop/${collection.shop.id }">${collection.shop.name }</a></span>
			                <a class="cancel-collect" href="cancel_shop_collection?id=${collection.shop.id }">取消收藏</a>
			            </li>
					</c:forEach>
        			
		        </ul>
			
			</c:otherwise>     
		</c:choose>
        
    </div>
</div>
<!-- 内容结束 -->

 <jsp:include flush="true" page="../include_footer.jsp"/> 
</body>
</html>
