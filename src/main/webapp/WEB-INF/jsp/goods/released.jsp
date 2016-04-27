<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="e" uri="/WEB-INF/etag.tld"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<title>我的发布 - ${websiteName}</title>
<base href="<%=basePath%>">
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="asset/eshop/css/common.css">
<link rel="stylesheet" href="asset/eshop/css/another.css">
</head>

<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>  
 
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
                <li class="nav-item active"><a class="nav-link">我的商品</a></li>
                <li class="nav-item"><a class="nav-link" href="goods_collection">商品收藏</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="user-release-goods">
                	<c:choose>
			<c:when test="${fn:length(page.result) == 0}">
				<div class="collection-empty">您没有发布过任何商品</div>
			</c:when>
			<c:otherwise>
				<ul class="good-item-box">
				<c:forEach var="goods" items="${page.result}">
					<li class="goods-item col-sm-6 col-md-4 col-lg-4">

                              <a class="goods-img-link" href="goodses/${goods.id}" target="_blank"><img src="${goods.image}"></a>

                              <p class="goods-item-title">${goods.name}</p>

                              <div class="good-item-price">
                                  <span>${goods.price}</span>
                                  <a class="btn-xs btn btn-default pull-right goods-item-btn" href="goodses/${goods.id}/edit">编辑</button>
                                  <a class="goods-delete btn-xs btn btn-default pull-right goods-item-btn" data-id="${goods.id}">删除</a>

                              </div>
                          </li>
					
				</c:forEach>
				</ul>
				<e:page page="${page}" url="goodses/released?page=" />
			</c:otherwise>
		</c:choose>
                   
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include flush="true" page="../include_footer.jsp"/>
<script>
$(document).on('click', '.goods-delete', function() {
	var id = $(this).data('id');
	deleteItem(id);
});

function deleteItem(id) {
	var text = '你确定删除该商品吗？';
	var url = 'goods_delete2?id=' + id;
	var onSuccess = function() {
		location.reload(true); 
	};
	ajax(text, url, onSuccess);
}
</script>

</body>
</html>
