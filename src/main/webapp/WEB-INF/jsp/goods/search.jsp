<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="e" uri="/WEB-INF/etag.tld"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="Eshop,购物" />
<meta name="description" content="Eshop，致力于打造一个优秀的网上购物商城，为你提供一个更加人性化的购物体验" />
<title>搜索 - ${websiteName}</title>
<jsp:include page="../eshop/include_head.jsp"></jsp:include>

</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>  
<!-- 主体内容 -->
<div class="container">
	<div class="row">
		<div class="col-md-12 col-lg-9">
			<div class="es-box">
				<div class="nav-catagory">
                    <div class="condition-title">分类:</div>
                    <div class="condition-list">
                    	<c:if test="${categoryId == '0'}">
                    		<span class="condition-item active">全部分类</span> 
                    	</c:if>
                    	<c:forEach var="category" items="${categories}">
                    		<c:choose>
                    		<c:when test="${category.id == categoryId}">
                    			<span class="condition-item active">${category.name}</span> 
                    		</c:when>
                    		<c:otherwise>
                    			<a class="condition-item" href="/goodses/search?category_id=${category.id}">${category.name}</a> 
                    		</c:otherwise>
                    		</c:choose>
						</c:forEach>
                    </div>
                </div>
            </div>
			<!-- 商品信息展示 -->
               <div class="panel panel-es">
					<div class="panel-heading">商品信息</div>
                   <div class="panel-body">
                   	<c:if test="${page.totalSize == 0}">
                   		<div class="goods-no-result">搜索不到商品</div>
                   	</c:if>
                    <div class="good-item-box">
                    	<c:forEach var="goods" items="${page.result}">   
                    		<li class="goods-item col-sm-6 col-md-4 col-lg-4">
								<c:choose>  
									<c:when test="${goods.image == null}"> 
										<a class="goods-img-link" href="/goodses/${goods.id}"><img src="/images/goods.jpg" alt="商品"></a>    
									</c:when>  
									<c:otherwise>  
										<a class="goods-img-link" href="/goodses/${goods.id}"><img src="/${goods.image}" alt="商品"></a>    
									</c:otherwise>     
								</c:choose> 
	                            <p class="goods-item-title">${goods.name}</p>
	                            <div class="good-item-price">
	                                <span>${goods.price}</span>
									<!-- 
									<a href="shop/${goods.shop.id}">${goods.shop.name}</a>
									 -->
	                            </div>
	                        </li>
						</c:forEach>
                    </div>
                    <e:page page="${page}" url="goodses/search?keyword=${keyword}&category_id=${categoryId}&page=" />
                </div>
			</div>

		</div>
		<div class="col-md-12 col-lg-3">
			<section class="panel panel-default">
                <div class="text-center right-btn-tools">
                    <a class="btn btn-success" href="/goodses/released">我的二手</a>
                    <a class="btn btn-success" href="#">发布求购</a>
                </div>
                <div class="news-box">
                    <p class="news-title-top"><a href="News.html">广二师二手网站上线啦</a></p>
                    <c:forEach var="article" items="${notices}"> 
                    	<p class="news-title"><a href="/articles/${article.id}">${article.title}</a></p>
					</c:forEach>
                </div>
            </section>
            
            <form class="input-group" action="/goodses/search">
                <input id="prependedInput" class="form-control" type="text" name="keyword" value="${keyword}" placeholder="请输入您要查询的商品">
                <div class="input-group-btn">
                    <input class="btn btn-success" type="submit" value="搜索">
                </div>
            </form>
			<!-- 
			<ul class="hot-keyword">
                    <li><a href="search?keyword=苹果">苹果</a></li>
                    <li><a href="search?keyword=西瓜">西瓜</a></li>
                </ul>
			 -->
			 
            <section class="panel panel-default marginTop-20">
                <p class="qiugou-panel-title">求购信息</p>
                <div class="qiugou-box">
                    <ul>
                        <li>
                            <div class="qiugou-item clearfix">
                                <img class="qiugou-img" src="/asset/eshop/img/33753.png" width="32" height="32">
                                <div class="qiugou-content">
                                    <p class="qiugou-person">小明</p>
                                    <p>谁有2016版的马克思主义到啊，联系我</p>
                                    <p>15小时前(1评论)</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="qiugou-item clearfix">
                                <img class="qiugou-img" src="/asset/eshop/img/33753.png" width="32" height="32">
                                <div class="qiugou-content">
                                    <p class="qiugou-person">小明</p>
                                    <p>谁有2016版的马克思主义到啊，联系我</p>
                                    <p>15小时前(1评论)</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="qiugou-item clearfix">
                                <img class="qiugou-img" src="/asset/eshop/img/33753.png" width="32" height="32">
                                <div class="qiugou-content">
                                    <p class="qiugou-person">小明</p>
                                    <p>谁有2016版的马克思主义到啊，联系我</p>
                                    <p>15小时前(1评论)</p>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
		</div>
	</div>
</div>
<!-- /主体内容 -->
 
<jsp:include flush="true" page="../eshop/include_footer.jsp"/> 
</body>
</html>

