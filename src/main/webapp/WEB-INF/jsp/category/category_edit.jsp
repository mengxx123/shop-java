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
<title>商品类别编辑</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/category_edit">添加商品类别</a></li>
        </ul>
        <a class="admin-btn float-right" action="/admin/category">商品类别列表</a>
        
        <c:if test="${result != null}">
	    	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>

    	<form class="form-horizontal" action="/admin/category" method="post">
    		<input type="hidden" name="id" value="${category.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">类别名称 *</label>
    			<div class="col-sm-4">
    				<input class="form-control" type="text" name="name" value="${category.name}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">所属分类</label>
    			<div class="col-sm-4">
    				<select id="catt" class="form-control" name="parent.id">   
                        <option value="0">顶级分类</option>
                        <c:forEach var="parent" items="${parents}">
                             <c:choose>
                    			<c:when test="${parent.id == category.parent.id}">
                    				<option value="${parent.id}" selected="selected">${parent.name}</option>  
                    			</c:when>
                    			<c:otherwise>
                    				<option value="${parent.id}">${parent.name}</option>  
                    			</c:otherwise>
                    		</c:choose> 
                    	</c:forEach> 
                    </select>
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">分类描述</label>
    			<div class="col-sm-4">
    				<input class="form-control" type="text" name="description" value="${category.description}">
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
    			<input id="btn-ok" class="btn btn-success" type="submit" value="确定">
    		</div>
        </form>
    </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script>
$(document).ready(function(e) {
	$('#catt').change(function() { 
		var id = $(this).children('option:selected').val(); // 商品类别父ID
		$.ajax({ 
			url: "/categories?parent_id=" + id, 
			type: 'get', 
			cache: false,
			dataType: 'html', 
			success: function(data) {
				alert(data);
				/* var jsonObj = eval('(' + data + ')');
				var state = jsonObj.state;
				if (state == "success") {
					location.reload(true); 
				} else {
					alert("删除失败");
				} */
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				//alert("异常！");
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
			}
		});
	});
});

</script>
</body>
</html>
