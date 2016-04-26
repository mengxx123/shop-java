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
<meta charset="utf-8">
<title>编辑商品</title>
<base href="<%=basePath%>">
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="asset/css/eshop/goods-edit.css" />
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="admin/goods_edit">添加商品</a></li>
        </ul>
        <a class="admin-btn float-right" href="admin/goodses">商品列表</a>
        
        <c:if test="${result != null}">
	    	<div class="admin-section">
		    	<p>${result}</p>
		    </div>
	    </c:if>
	    
		<ul class="nav nav-tabs">
            <div class="nav-item active"><a class="nav-link" href="#tab11" data-toggle="tab">通用信息</a></div>
            <div class="nav-item"><a class="nav-link" href="#tab12" data-toggle="tab">详细描述</a></div>
            <div class="nav-item"><a class="nav-link" href="#tab13" data-toggle="tab">其他信息</a></div>
            <div class="nav-item"><a class="nav-link" href="#tab14" data-toggle="tab">商品属性</a></div>
            <div class="nav-item"><a class="nav-link" href="#tab15" data-toggle="tab">商品图片</a></div>
        </ul>
        <div class="tab-content">
        	<!-- 通用信息 -->
            <div id="tab11" class="tab-pane fade in active">
                <form class="form-horizontal" action="goods" method="post">
               		<input type="hidden" name="id" value="${goods.id}" />
         			<input type="hidden" name="what" value="1" />
         			<c:forEach var="category" items="${guide}">
          					<div>${category.id}${category.name}</div>
			                            
			        </c:forEach> 
          			<div class="form-group">
		    			<label class="control-label col-sm-3">商品名称</label>
		    			<div class="col-sm-4">
		    				<input class="form-control" type="text" name="name" value="${goods.name}">
		    			</div>
		    		</div>
		    		<div class="form-group">
		    			<label class="control-label col-sm-3">商品分类</label>
		    			<div class="col-sm-4">
		    				<select id="article-cat" class="form-control" name="category.id">
                               	<c:forEach var="category" items="${categories}">
		                             <c:choose>
		                    			<c:when test="${category.id == goods.category.id}">
		                    				<option value="${category.id}" selected="selected">${category.name}</option>  
		                    			</c:when>
		                    			<c:otherwise>
		                    				<option value="${category.id}">${category.name}</option>  
		                    			</c:otherwise>
		                    		</c:choose> 
		                    	</c:forEach> 
                           	</select>
		    			</div>
		    		</div>
		    		<div class="form-group">
		    			<label class="control-label col-sm-3">价格</label>
		    			<div class="col-sm-4">
		    				<input class="form-control" type="number" name="price" value="${goods.price}">
		    			</div>
		    		</div>
		    		<div class="form-group">
		    			<label class="control-label col-sm-3">库存</label>
		    			<div class="col-sm-4">
		    				<input class="form-control" type="number" name="number" value="${goods.number}">
		    			</div>
		    		</div>
		    		<div class="col-sm-4 col-sm-offset-3">
		    			<input id="btn-ok" class="btn btn-success" type="submit" value="确定">
		    		</div>
              	</form>
            </div>
            <!-- 详细描述 -->
            <div id="tab12" class="tab-pane fade">
                <textarea class="admin-textarea detail-desc">${goods.description}</textarea>
                    <p><input class="admin-btn margin-top-10px" type="button" value="确定" /></p>
            </div>
            <!-- 其他信息 -->
            <div id="tab13" class="tab-pane fade">
                <table class="data-table">
                        <tr>
                            <td><label>上架</label></td>
                            <td><input class="ttt" type="checkbox" /></td>
                        </tr>
                    </table>
            </div>
            <!-- 商品属性 -->
            <div id="tab14" class="tab-pane fade">
                <div>
                    <h3>标题3</h3>
                    <p>这是一段文字。</p>
                </div>
            </div>
            <!-- 商品图片 -->
            <div id="tab15" class="tab-pane fade">
                <ul class="images">
                		<c:forEach var="image" items="${images}">
                			<li class="image-item">
	                			<img src="goods_image/${image.url}" />
	                			<c:if test="${image.url == goods.image}">
	                				<span>默认</span>
	                			</c:if>
	                			<a href="javascript:;" onclick="deleteImage(${image.id});">删除</a>
	                			<a href="javascript:;" onclick="setImage(${goods.id},${image.id});">设置</a>
                			</li>
                		</c:forEach>
                    </ul>
                    <form action="admin/goods_image" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${goods.id}" />
                	<ul>
                    	<li>
                        	<a class="add-photo" href="javascript:;">[+]</a>图片描述<input class="admin-input" type="text" />上传文件<input class="admin-file" type="file" name="myfile" />
                        </li>
                    </ul>
                    <div>
                    	<input class="admin-btn" type="submit" value="保存" />
                    </div>
                    </form>
            </div>
        </div>
	</div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script>
function deleteImage(id) {
	$.ajax({ 
		url: "goods_image_delete?id=" + id, 
		type: 'get', 
		cache: false,
		dataType: 'html', 
		success: function(data) {
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			if (state == "success") {
				location.reload(true); 
			} else {
				alert("删除失败");
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
/* 删除反馈，带提示 */
function setImage(goodsId, imageId) {
	$.ajax({ 
		url: "set_goods_image", 
		data: {
			image_id: imageId,
			goods_id: goodsId,
		},
		type: 'get', 
		cache: false,
		dataType: 'html', 
		success: function(data) {
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			if (state == "success") {
				location.reload(true); 
			} else {
				alert("设置失败"+jsonObj.message);
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

function removePhoto(elem) {
	$(elem).parent().remove();
}

$(document).ready(function(e) {
	/* 添加图片 */
	$(".add-photo").click(function() {
		var li = $('<li></li>');
		var elem = $('<a class="remove-photo" onclick="removePhoto(this) href="javascript:;">[+]</a>图片描述<input class="admin-input" type="text" />上传文件<input class="admin-file" type="file" />');
		li.append(elem);
		$(this).parent().parent().append(li);
	});
	
	$("#btn-ok").click(function() {
		
	});
	
});

</script>
</body>
</html>
