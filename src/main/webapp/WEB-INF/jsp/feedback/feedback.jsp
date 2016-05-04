<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<title>用户反馈</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />

<jsp:include flush="true" page="../include_head.jsp"/>

</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/>
<div class="container">
	<c:choose>  
	<c:when test="${isLogin == true}">     
        	<form class="form-horizontal">
		   		<div class="form-group">
		    		<textarea id="content" class="form-control" name="description" placeholder="请输入反馈内容" rows="4">${section.description}</textarea>
		   		</div>
		   		<div class="form-group">
		    		<input id="btn-submit" class="btn btn-success" type="button" value="提交">
		   		</div>
		    	
			</form>
		</c:when>  
	<c:otherwise> 
	 	<p>请<a href="login">登录</a>后进行反馈。</p>
	</c:otherwise>
	</c:choose>
	
</div>
<jsp:include flush="true" page="../include_footer.jsp"/>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery.md5.js"></script>
<script>

$("#btn-submit").click(function() {
	var content = $("#content").val();
	if (content == "") {
		alert("请输入内容");
		return false;
	}
	
	$.ajax({ 
		url: "/feedbacks",
		data:{  
			content : content
		},  
		type: 'post', 
		dataType: 'json', 
		success: function(obj) {
			if (obj.code === 0) {
				alert('反馈成功');
			} else {
				alert('反馈失败，' + obj.data);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			alert('服务器异常');
		}
	});
});
</script>
</body>
</html>