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
<title>店铺收藏 - ${websiteName}</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="../eshop/include_head.jsp"></jsp:include>
</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>
<!-- 主体内容 -->
<div class="container">
    <div class="release-form col-lg-12">
    	<div class="es-box">    
            <div class="user-box clearfix">
                <div class="user-detail">
                    <img class="user-detail-img" src="${seller.headPath}">

                    <div class="user-detail-content">
                        <p class="user-detail-name">英之才 - ${shop.name}</p>

                        <p><span>注册会员</span> <span>注册日期：2015-10-1</span></p>

                        <p>个人描述：这家伙太懒了，还未填写个人描述！</p>
                    </div>
                </div>
                <div class="user-release-num">
                    <ul class="nav user-release-num-ul">
                        <li>
                            <div class="">
                                <h3>${page.totalSize}</h3>
                                <p>商品</p>
                            </div>
                        </li>
                        <li>
                            <div class="">
                                <h3>0</h3>
                                <p>求购</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="user-detail-btn-group">
                    <a class="btn btn-info" href="goods/release">发布商品</a>
                    <a class="btn btn-success">发布求购</a>
                </div>
            </div>

            <ul id="myTab" class="nav nav-tabs">
            	<li class="nav-item"><a class="nav-link" href="goodses/released">我的商品</a></li>
                <li class="nav-item"><a class="nav-link" href="goods_collection">商品收藏</a></li>
                <li class="nav-item active"><a class="nav-link" href="#shop-collection">店铺收藏</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="user-release-goods">
                	<c:choose>  
					<c:when test="${fn:length(page.result) == 0}">
						<div class="collection-empty">收藏夹为空</div>
					</c:when>  
					<c:otherwise>
						<ul class="collection-list">
	                	<c:forEach var="collection" items="${page.result}">  
	                		<li class="collection-item">
                                <img class="collection-item-image" src="images/no-image.png">
                                <div class="collection-item-content">
                                    <a class="collection-item-name" href="shops/${collection.shop.id}">${collection.shop.name}</a>
                                    <p class="collection-item-desc">￥</p>
                                    <a class="collection-cancel btn btn-primary" href="cancel_shop_collection?id=${collection.id}">取消收藏</a>
                                </div>
                            </li> 
						</c:forEach>
			            </ul>
				
					</c:otherwise>     
					</c:choose>    
                </div>
            </div>

        </div>
    </div>
</div>
 <jsp:include flush="true" page="../include_footer.jsp"/> 
</body>
</html>
