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
<title>文章编辑</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="/css/admin_article_edit.css">
<link rel="stylesheet" href="/asset/lib/umeditor/themes/default/css/umeditor.css">
</head>

<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="/admin/article_edit">添加文章</a></li>
        </ul>
        <a class="admin-btn float-right" href="/admin/articles">文章列表</a>
        
        <c:if test="${result != null}">
	    	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>

		<form class="form-horizontal" action="/admin/articles" method="post">
        	<input type="hidden" name="id" value="${article.id}">
        	<div class="form-group">
    			<label class="control-label col-sm-3">文章标题 *</label>
    			<div class="col-sm-4">
    				<input id="title" class="form-control" name="title" type="text" value="${article.title}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">文章分类</label>
    			<div class="col-sm-4">
    				<select id="article-cat" class="form-control" name="category_id">   
                        <option value="1" selected = "selected">未分类</option>
	                    <c:forEach var="category" items="${categories}">
	                               	<c:choose>
	                  			<c:when test="${category.id == article.category.id}">
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
    		<textarea id="myEditor" name="content">这里写你的初始化内容</textarea>
    		<div class="col-sm-4 col-sm-offset-3">
    			<input id="btn-ok" class="btn btn-success" type="submit" value="确定" onclick="return valid()" />
    		</div>
		</form>

    </div> 
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script src="/asset/lib/umeditor/umeditor.config.js"></script>
<script src="/asset/lib/umeditor/umeditor.min.js"></script>
<script src="/asset/lib/umeditor/lang/zh-cn/zh-cn.js"></script>
<script>
function valid() {
	var title = $('#title').val();
	if (title == '') {
		eui.alert("标题不能为空");
		return false;
	}
	
	if (!UM.getEditor('myEditor').hasContents()) {
		eui.alert("请输入文章内容");
		return false;
	}
	
	return true;
}

/* 适配当前屏幕大小 */
function resize() {
	UM.getEditor('myEditor').setWidth($(this).width() - 100);
}

$(document).ready(function(e) {
	//实例化编辑器
    UM.getEditor('myEditor').setContent('${article.content}', false);
	
	// 适配当前屏幕大小
	resize();
	
	// 窗口大小改变时，网页重新适配窗口
	$(window).resize(function() {
		resize();
	});
});

</script>

</body>
</html>