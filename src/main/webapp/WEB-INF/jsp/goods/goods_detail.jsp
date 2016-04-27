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
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>${goods.name} - ${websiteName}</title>
<base href="<%=basePath%>">
<jsp:include page="../eshop/include_head.jsp"></jsp:include>
</head>
<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>
<!-- 内容 -->
<div class="container">

    <div class="row panel panel-es pad-20">
        <div class="row">
            <div class="col-lg-6 col-md-4 col-sm-4">
                <!-- 商品图片轮播 -->
                <div class="slide-photo-box goods-img">
                    <div class="bigger-slide-photo">
                    	<c:forEach var="image" items="${goodsImages}">  
                    		<div class="bigger-photo hide"><img src="${image.url}" width="540" height="540"/></div>
						</c:forEach>
                    </div>

                    <div class="small-slide-photo">

                        <div class="slide-photo">
                        	<c:forEach var="image" items="${goodsImages}"> 
                        		 <div class="ershou-samll-photo"><img class="small" src="${image.url}" width="60px;"
                                                                 height="60px;"/></div> 
							</c:forEach>
                        </div>
                        <div class="mask"></div>
                    </div>
                    <c:choose>
                    <c:when test="${isLogin == false}">
                    	<div id="tip-login" class="like-box">
		                    <i class="fa fa-heart"></i>
		                    <div id="value">${goods.collectionCount}</div>
		                </div>
                    </c:when>
                    <c:when test="${isCollection}">
                    	<div id="like" class="like-box done">
		                    <i class="fa fa-heart"></i>
		                    <div id="value">${goods.collectionCount}</div>
		                </div>
                    </c:when>
                    <c:otherwise>
                    	<div id="like" class="like-box">
		                    <i class="fa fa-heart"></i>
		                    <div id="value">${goods.collectionCount}</div>
		                </div>
                    </c:otherwise>
                    </c:choose>
                    
                </div>
            </div>


            <div class="col-lg-4 col-md-4 col-sm-4">
                <div class="goods-detail">
                    <p class="goods-title">${goods.name}</p>
                    <div class="goods-price discount">
                        <span>${goods.price}</span>
                    </div>
                    <!-- 
                    <li>商品点击数：${goods.clickCount}</li>
                    <li>商品库存： ${goods.number}</li>
               
                
            </ul>
            <p>本商品由<a class="shop-name" href="shops/${goods.shop.id}">${goods.shop.name}</a>配送并提供售后服务</p>
            <c:if test="${isLogin && isCollection}">     
			    <span>已收藏</span> 
			</c:if>
			<c:if test="${isLogin && !isCollection}">     
				<div><a id="collection" class="collection" href="javascript:;">收藏</a></div>
			</c:if>   
            <div>
            	<span>购买数量：</span><span><a id="subtract-btn" class="subtract-btn" href="javascript:;">-</a></span><input  id="number-input" class="number-input" type="text" value="1" /><span><a id="add-btn" class="add-btn" href="javascript:;">+</a></span>
            </div>
           
                     -->
                    <ul class="goods-detail-meta">
                        <li>
                        	<span class="meta-name">卖家</span>
                        	<a class="meta-value meta-value-link" href="shops/${shop.id}" target="_blank">${seller.nickname}</a>
                        </li>
                        <li>
                        	<span class="meta-name">发布时间</span>
                        	<span class="meta-value"><e:simpleTime value="${goods.modifyTime}" /></span>
                        </li>
                        <li>
                        	<span class="meta-name">商品描述</span>
                        	<span class="meta-value">${goods.description}</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-sm-12 es-box">
    		<c:choose>  
			<c:when test="${fn:length(comments) == 0}">     
			         	<div>没有评论</div> 
			</c:when>  
			<c:otherwise>  
				<ul>
				<c:forEach var="comment" items="${comments}">  
					<li class="comment-item media">
						<a href=""><img class="comment-item-img media-object" src="${comment.user.headPath}"></a>
                        <div class="media-body">
                            <a class="comment-item-user" href="">${comment.user.nickname}</a>
                            <p>${comment.content}</p>
                            <div class="comment-item-time"><e:simpleTime value="${comment.commentTime}" /></div>
                        </div>
                	</li> 
				</c:forEach>
				</ul>
			</c:otherwise>     
			</c:choose>  
		
			<c:choose>
			<c:when test="${isLogin == false}">
				<p>请<a href="login">登录</a>后再评论</p>
			</c:when>
			<c:when test="${isSelf == false}"><%--店家不能评论自己的商品 --%>
				<textarea id="content" class="user-message-textarea form-control" rows="5"></textarea>
            	<button id="comment" class="btn btn-primary btn-message" type="button">提交</button>
			</c:when>
			</c:choose>
    	</div>
    </div>
</div>
	
<jsp:include flush="true" page="../eshop/include_footer.jsp"/>  
<script src="asset/eshop/js/jquery.slides.js"></script>
<script>
var goodsId = '${goods.id}';

$(document).ready(function() {
	$('#comment').on('click', function() {
		var content = $('#content').val();
		$.ajax({ 
			url: 'goods_comments',
			data: {
				content: content,
				goods_id: goodsId
			},
			type: 'post', 
			dataType: 'json', 
			success: function(obj) {
				if (obj.code === 0) {
					location.reload(true); 
				} else {
					alert("评论失败，" + obj.data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				alert("系统出错");
			}
		});
	});
});

function allPrpos(obj) { 
	// 用来保存所有的属性名称和值
	var props = "";
	// 开始遍历
	for (var p in obj) { 
		// 方法
		if(typeof(obj[p])=="function"){ 
			obj[p]();
		} else { 
			// p 为属性名称，obj[p]为对应属性的值
			props += '|' + p + "=" + obj[p] + "\t";
		} 
	} 
	// 最后显示所有的属性
	alert(props);
}
//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
var o = {   
 "M+" : this.getMonth()+1,                 //月份   
 "d+" : this.getDate(),                    //日   
 "h+" : this.getHours(),                   //小时   
 "m+" : this.getMinutes(),                 //分   
 "s+" : this.getSeconds(),                 //秒   
 "q+" : Math.floor((this.getMonth()+3)/3), //季度   
 "S"  : this.getMilliseconds()             //毫秒   
};   
if(/(y+)/.test(fmt))   
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
for(var k in o)   
 if(new RegExp("("+ k +")").test(fmt))   
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
return fmt;   
}  

function test2() {
	$.ajax({ 
		url: "good_comment", 
		type: 'get', 
		data: {
			goodsId: ${goods.id} // 改
		},
		cache: false,
		dataType: 'html', 
		success: function(data) {
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			if (state != undefined) {
				alert("读取失败，" + state.message);
				return;
			}
			
			//alert(jsonObj);
			var length = jsonObj.length;
			for (var i = 0; i < length; i++) {
				var goodsComment = jsonObj[i];
				//allPrpos(goodsComment);
				var content = goodsComment.content;
				var user = goodsComment.user;
				var userName = user.name;
				var time = goodsComment.time;
				var unixTimestamp = new Date(time);
				//var commonTime = unixTimestamp.toLocaleString();
				var commonTime = unixTimestamp.Format("yyyy-MM-dd hh:mm:ss"); 
				//alert(typeof unixTimestamp);
				var node = '<li class="comment"><div class="comment-left">' + 
                '<img class="comment-head" src="images/head_default.png" alt="头像" />'
                + '<p>'+userName+'</p></div>'
                + '<div class="comment-right">'
                + '<div>评分：<span class="star star'+goodsComment.score+'"></span></div>'
                + '<p class="comment-content">内容：'+goodsComment.content+'</p>'
                + '<p class="comment-time">'+commonTime+'</p></div></li>';
           		
                
                //node.replace('USER_NAME', userName);
                //node.replace('SCORE', goodsComment.score);
				$("#good-comment").append(node);
			}
		
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			//alert("异常！");
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
		}
	});
}

</script>
<script>
var count = 1;
var price = ${goods.price};
var totalNum = ${goods.number};

$(document).ready(function(e) {
    $("#add-btn").click(function(e) {
        var value = $("#number-input").val();
		var intValue = parseInt(value);
		intValue++;
		if (intValue > totalNum) {
			intValue = totalNum;
		}
		$("#number-input").val(intValue);
		$("#total-price").text((price * intValue) + '￥');
    });
	
	$("#subtract-btn").click(function(e) {
        var value = $("#number-input").val();
		var intValue = parseInt(value);
		intValue--;
		if (intValue < 1) {
			intValue = 1;
		}
		$("#number-input").val(intValue);
		$("#total-price").text((price * intValue) + '￥');
    });
});
</script>
<script>
var collectionId = '${collectionId}';

$('#tip-login').on('click', function() {
	eui.alert('请登录后再收藏');	
});

$("#like").on('click', function() {
	$this = $(this);
	if ($this.hasClass("done")) {
		// 取消收藏
		$.ajax({ 
			url: "api/v1/goods_collection_cancel", 
			type: 'post', 
			data: {
				collection_id: collectionId
			},
			dataType: 'json', 
			success: function(obj) {
				if (obj.code === 0) {
					
				    $value = $this.find('#value');
				    var likeCount = parseInt($value.text());
				    $this.removeClass("done");
				    $value.text(likeCount - 1);
				} else {
					alert("取消收藏失败，" + obj.data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				//alert("异常！");
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
			}
		});
		//alert('取消收藏暂未实现');
	} else {
		// 收藏
		$.ajax({ 
			url: "api/v1/goods_collection", 
			type: 'post', 
			data: {
				goods_id: '${goods.id}' // 改
			},
			dataType: 'json', 
			success: function(obj) {
				if (obj.code === 0) {
					
				    $value = $this.find('#value');
				    var likeCount = parseInt($value.text());
				    $this.addClass("done");
				    $value.text(likeCount + 1);
				    
				    collectionId = obj.data;
				} else {
					alert("收藏失败，" + obj.data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				//alert("异常！");
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
			}
		});
	}
	
	
});
</script>
</body>
</html>
