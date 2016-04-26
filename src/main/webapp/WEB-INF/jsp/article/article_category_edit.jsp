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
<title>文章类别编辑</title>
<base href="<%=basePath%>">
<jsp:include page="../admin/include_head.jsp"></jsp:include>
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="admin/article_category_edit">添加文章类别</a></li>
        </ul>
        <a class="admin-btn float-right" href="admin/article_categorys">文章类别列表</a>
        
        <c:if test="${result != null}">
    		<div class="eui-alert eui-alert-info">${result}</div>
    	</c:if>

    	<form class="form-horizontal" action="article_categorys" method="post">
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
    				<select id="article-cat" class="form-control" name="parent.id">
                    	<option value="1">未分类</option>
                    	<c:choose>
                   			<c:when test="${category.parent.id == 0}">
                   				<option value="0" selected="selected">顶级分类</option>
                   			</c:when>
                   			<c:otherwise>
                   				<option value="0">顶级分类</option> 
                   			</c:otherwise>
                   		</c:choose>
                    	
                    	<% 
                    	/*
                    	<c:if test="${category.id != null}">
                    		<option value="${category.parent.id}">${category.parent.name}</option>
                    	</c:if>
                    	*/
                    	%>
                    	<c:forEach var="parent" items="${parents}">
                    		<c:choose>
                    			<c:when test="${category.parent.id == parent.id}">
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
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
</body>
</html>
