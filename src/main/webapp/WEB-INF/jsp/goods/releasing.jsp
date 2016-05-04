<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>发布商品</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="/asset/eshop/css/common.css">
<link rel="stylesheet" href="/asset/eshop/css/another.css">
</head>

<body>
<jsp:include flush="true" page="../eshop/include_header.jsp"/>  

<div class="container pad-top35">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
				<a class="btn btn-info" href="/goodses/released">我的商品</a>
			</div>
		</div>
    	<div class="panel panel-default">
    		<div class="panel panel-body">
	            <div class="release-form col-lg-11">
	                <h3>发布闲置</h3>
	                <form id="form" class="form-horizontal" action="goods/releasing" method="post" enctype="multipart/form-data">
	                	<input type="hidden" id="id" name="id" value="${goods.id}">
	                    <div class="form-group">
	                        <label for="firstname" class="col-sm-2 control-label">商品图片</label>
	                        <div class="col-sm-10">
	                        	<div class="img-preview">
	                        		<c:forEach var="image" items="${images}">
	                        			<div class="img-preview-item">
		                                    <img src="/${image.url}">
		                                    <a class="img-preview-delete" href="#" data-id="${image.id}">删除</a>
		                                </div>
	                        		</c:forEach>
	                        		<c:choose>
	                        		<c:when test="${goods.id == null}">
	                        			<input id="file" class="" type="file" name="file">
	                        		</c:when>
	                        		<c:otherwise>
	                        			<div class="img-preview-item">
		                                    <div id="img-add" class="img-preview-add">+</div>
		                                </div>
		                                <input id="file" class="hide" type="file" name="file">
	                        		</c:otherwise>
	                        		</c:choose>
	                        		
	                        		
	                        	</div>
	                        	
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="firstname" class="col-sm-2 control-label">商品名</label>
	                        <div class="col-sm-10">
	                            <input type="text" class="form-control" id="goods-name" name="name" value="${goods.name}" placeholder="请输入名字">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="lastname" class="col-sm-2 control-label">价格</label>
	                        <div class="col-sm-10">
	                            <input type="text" class="form-control" id="price" name="price" value="${goods.price}" placeholder="请输入价格">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="lastname" class="col-sm-2 control-label">商品描述</label>
	                        <div class="col-sm-10">
	                            <textarea type="text" class="form-control" id="description" name="description" rows="3"
	                             placeholder="商品描述以及联系方式（QQ、微信、手机等）">${goods.description}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="lastname" class="col-sm-2 control-label">商品分类</label>
	                        <div class="col-sm-10">
								<select id="article-cat" class="form-control" name="catId">
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
	                        <button id="goods-submit" type="submit" class="btn btn-success col-sm-10 pull-right">确认提交</button>
	                    </div>
	                </form>
	
	            </div>
    		</div>
    	</div>
	</div>
</div>
<jsp:include flush="true" page="../include_footer.jsp"/> 
<script src="/asset/lib/validate/jquery.validate.min.js"></script>
<script src="/asset/js/validate.js"></script>
<script>
<c:choose>
<c:when test="${goods.id == null}">
	var autoUpload = false;
</c:when>
<c:otherwise>
	var autoUpload = true;
</c:otherwise>
</c:choose>

$('#file').on('change', function() {
	if (!autoUpload) {
		return;
	}
	
	var id = $('#id').val();
	var formData = new FormData();
	formData.append('file', $('#file')[0].files[0]);
	formData.append('goods_id', id);
	
	$.ajax({
		url: "/goods_images_add",
		type: 'post',
		dataType: 'json',
		data: formData,
 	    processData: false,
 		contentType: false,
		success: function(json) {
             var code = json.code;
             if (code === 0) {
                 location.reload(true);
             } else {
				alert("上传图片失败，" + json.data);
             }
         },
         error:function(XMLHttpRequest, textStatus, errorThrown){  
             alert('服务器出错');
        }  
     });
});

$('#img-add').on('click', function() {
	var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;

	if(ie){
		document.getElementById('file').click();
	}else{			
		var mouseobj = document.createEvent("MouseEvents");
		mouseobj.initEvent("click", true, true);  
		document.getElementById('file').dispatchEvent(mouseobj);
	}
});

$(document).on('click', '.img-preview-delete', function(e) {
	e.preventDefault();
	$this = $(this);
	var id = $this.data('id');
	$.ajax({
		url: '/goods_image_delete?id=' + id,
		dataType: 'json',
		success: function(json) {
             var code = json.code;
             if (code === 0) {
            	 $this.parent().remove();	
             } else {
				alert("删除失败，" + json.data);
             }
         },
         error:function(XMLHttpRequest, textStatus, errorThrown){  
             $("div").html(textStatus);  
             console.log(XMLHttpRequest.status);
             console.log(XMLHttpRequest.status);
             console.log(XMLHttpRequest.responseText);
           
        }  
     });
	
});

$('#form').validate({
    rules:{
    	file: {
    		required: true
    	},
        name:{
            required: true
        },
        price: {
        	required: true
        },
        description: {
        	required: true
        }
    },
    messages:{
    },
    submitHandler: function(form) {
    	ajaxSubmit();
    }
});

function ajaxSubmit() {
//$('#goods-submit').click(function() {
	
	var name = $('#goods-name').val();
	var id = $('#id').val();
	var price = $('#price').val();
	var description = $('#description').val();
	var categoryId = $('#article-cat').val();
	var formData = new FormData();
	formData.append('file', $('#file')[0].files[0]);
	formData.append('name', name);
	formData.append('id', id);
	formData.append('price', price);
	formData.append('description', description);
	formData.append('catId', categoryId);
	
	$.ajax({
		url: "/goods/releasing",
		type: 'post',
		dataType: 'json',
		data: formData,
 	    //data: new FormData($('#upload-form')[0]),
 	    processData: false,
 		contentType: false,
		success: function(json) {
             var code = json.code;
             if (code === 0) {
            	 var id = json.data;
            	 window.location = 'goodses/' + id + '/edit';
             } else {
              	alert('发布失败，' + json.data);
             }
         },
         error:function(XMLHttpRequest, textStatus, errorThrown) { 
        	 alert('服务器出错');
        }  
     });
 }
</script>
</body>
</html>
