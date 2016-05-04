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
<title>聊天</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />

<jsp:include page="../admin/include_head.jsp"></jsp:include>

<link rel="stylesheet" href="/asset/css/question.css">
</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/>  
<!-- 主体内容 -->
<div class="container">
	<div class="row">
		<div class="col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading">全部消息<a class="pull-right" href="messages/unread">未读消息</a></div>
				<div class="panel-body">
						
				</div>
			</div>
			
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
                <div class="panel-heading">系统公告</div>
                <div class="panel-body">
                	<a href="eknow/guideline">提问规范</a>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">排行榜</div>
                <div class="panel-body">
                1
                </div>
            </div>
		</div>
	</div>
			
</div>
<!-- 内容结束 -->
	
<jsp:include flush="true" page="../include_footer.jsp"/>  
<script>

$(document).on('click', '.mark-read', function(e) {
	e.preventDefault();
	
	var id = $(this).data('id');
	
	$.ajax({
		url: '/messages/' + id + '/read',
		dataType: 'json',
		success:  function(obj) {
			if (obj.code === 0) {
				window.location.reload();
			} else {
				alert('标记失败，' + obj.data);
			}
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	       alert(textStatus);  
    	}
	});
});

$(document).on('click', '.delete-message', function(e) {
	e.preventDefault();
	
	var id = $(this).data('id');
	
	$.ajax({
		url: '/messages/' + id + '/delete',
		dataType: 'json',
		success:  function(obj) {
			if (obj.code === 0) {
				window.location.reload();
			} else {
				alert('删除失败，' + obj.data);
			}
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	       alert(textStatus);  
    	}
	});
});
</script>
</body>
</html>
