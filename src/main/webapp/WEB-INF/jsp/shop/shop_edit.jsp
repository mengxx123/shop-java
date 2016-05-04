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
<title>编辑店铺</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/shop_edit">添加店铺</a></li>
        </ul>
        <a class="btn btn-primary" action="/admin/shops">店铺列表</a>
        
        <c:if test="${result != null}">
        	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>

    	<form class="form-horizontal" action="shop" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${shop.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">店铺名称 *</label>
    			<div class="col-sm-4">
    				<input id="name" class="form-control" type="text" name="name" value="${shop.name}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">店铺描述 *</label>
    			<div class="col-sm-4">
    				<input id="description" class="form-control" type="text" name="description" value="${shop.description}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">商家编号 *</label>
    			<div class="col-sm-4">
    				<input id="user-id" class="form-control" type="text" name="user.id" value="${shop.user.id}" />
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
    			<input id="btn-ok" class="btn btn-success" type="submit" value="确定" onclick="return valid()">
    		</div>
        </form>
    </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/common.js"></script>
<script>
function valid() {
	var name = $('#name').val();
	if (name == '') {
		alert('名称不能为空！');
		return false;
	}
	var userId = $('#user-id').val();
	if (userId == '') {
		alert('商家编号不能为空！');
		return false;
	}
	return true;
}
</script>
</body>
</html>
