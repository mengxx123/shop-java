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
<title>编辑品牌</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<style>
.brand-icon {
	width: 100px;
	height: 40px;
}
</style>
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="/admin/brand_edit">添加品牌</a></li>
        </ul>
        <a class="admin-btn float-right" href="/admin/brands">商品品牌列表</a>
        
        <c:if test="${result != null}">
        	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>

    	<form class="form-horizontal" action="brands" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${brand.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">品牌名称 *</label>
    			<div class="col-sm-4">
	    			<input class="form-control" type="text" name="name" value="${brand.name}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">品牌图标</label>
    			<div class="col-sm-4">
    				<c:if test="${brand.icon != null && brand.icon != ''}">
                    	<img class="brand-icon" src="/images/brand/${brand.icon}" alt="${brand.name}"/>         	
                    </c:if> 
	    			<input class="form-control" type="file" name="myfile">
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
    			<input id="btn-ok" class="btn btn-success" type="submit" value="确定" />
    		</div>
        </form>
    </div>
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
</body>
</html>
