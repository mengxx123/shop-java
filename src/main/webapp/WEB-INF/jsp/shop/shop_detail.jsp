<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="e" uri="/WEB-INF/etag.tld"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<title>${shop.name} - ${websiteName}</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<base href="<%=basePath%>">
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="asset/eshop/css/common.css">
<link rel="stylesheet" href="asset/eshop/css/another.css">
</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>  
<!-- 主体内容 -->
<div class="container pad-top35">
	<div class="row">
		<div class="col-md-12 col-lg-9">
			<!-- 
			商店
<p>${shop.name }</p>
<c:if test="${isLogin && isCollection}">     
    <div>已收藏</div> 
</c:if>
<c:if test="${isLogin && !isCollection}">     
    <a id="collection" href="javascript:;">收藏该商店</a>
</c:if>  

<p>${shop.description }</p>
			 -->
			
			<!-- 商品信息展示 -->
            <div class="panel panel-es">
            	<div class="panel-body">
                 <div class="goods-box-title">
                     <p>${seller.nickname}发布的所有商品</p>
                 </div>
                 <c:if test="${fn:length(page.result) == 0}">     
  				<p>没有商品</p>
		</c:if>  
                 <div class="good-item-box">
                 	<c:forEach var="goods" items="${page.result}">   
                 		<li class="goods-item col-sm-6 col-md-4 col-lg-4">
					<c:choose>  
						<c:when test="${goods.image == null}"> 
							<a class="goods-img-link" href="goodses/${goods.id}"><img src="images/goods.jpg" alt="商品"></a>    
						</c:when>  
						<c:otherwise>  
							<a class="goods-img-link" href="goodses/${goods.id}"><img src="${goods.image}" alt="商品"></a>    
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
                 <e:page page="${page}" url="shops/${shop.id}?page=" />
             </div>
            </div>
		</div>
		<div class="col-md-12 col-lg-3">
			<section class="panel panel-default">
                <div class="text-center right-btn-tools">
                    <a class="btn btn-success" href="goodses/released">我的二手</a>
                    <a class="btn btn-success" href="#">发布求购</a>
                </div>
                <div class="news-box">
                    <p class="news-title-top"><a href="News.html">广二师二手网站上线啦</a></p>
                    <c:forEach var="article" items="${notices}"> 
                    	<p class="news-title"><a href="articles/${article.id}">${article.title}</a></p>
					</c:forEach>
                </div>
            </section>
            
            <form class="input-group" action="goodses/search">
                <input id="prependedInput" class="form-control" type="text" name="keyword" placeholder="请输入您要查询的商品">
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
                                <img class="qiugou-img" src="asset/eshop/img/33753.png" width="32" height="32">
                                <div class="qiugou-content">
                                    <p class="qiugou-person">小明</p>
                                    <p>谁有2016版的马克思主义到啊，联系我</p>
                                    <p>15小时前(1评论)</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="qiugou-item clearfix">
                                <img class="qiugou-img" src="asset/eshop/img/33753.png" width="32" height="32">
                                <div class="qiugou-content">
                                    <p class="qiugou-person">小明</p>
                                    <p>谁有2016版的马克思主义到啊，联系我</p>
                                    <p>15小时前(1评论)</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="qiugou-item clearfix">
                                <img class="qiugou-img" src="asset/eshop/img/33753.png" width="32" height="32">
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

<jsp:include flush="true" page="../include_footer.jsp"/> 
<script>
$("#collection").click(function() {
	$.ajax({ 
		url: "api/v1/addShopCollection", 
		type: 'post', 
		data: {
			shopId: ${shop.id} // 改
		},
		cache: false,
		dataType: 'html', 
		success: function(data) {
			//alert("成功！" + data);
			//由JSON字符串转换为JSON对象
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			//alert(state);
			if (state == "success") {
				//location.reload(true); 
				alert("收藏成功");
			} else {
				alert("收藏失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			//alert("异常！");
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
		}
	});
});
</script>
</body>
</html>
