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
<title>校园群 - 为兴趣而生</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<base href="<%=basePath%>">

<jsp:include page="../admin/include_head.jsp"></jsp:include>

<link rel="stylesheet" href="asset/css/question.css">
</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/>  
<!-- 主体内容 -->
<div class="container">
	<div class="row">
		<div class="col-sm-8">
			
			<section class="panel panel-default">
				<div class="panel-body">
					<h1>${group.name}</h1>
					<p>群介绍：${group.description}</p>
					<p>群成员数量：${group.memberCount}</p>
				</div>
			</section>
			
			
			<div class="panel panel-default">
                <div class="panel-heading">群成员</div>
                <div class="panel-body">
                	<c:choose>  
					<c:when test="${fn:length(page.result) == 0}">
						<tr>
		                	<td class="no-records" colspan="6">没有成员</td>
		                </tr> 
					</c:when>  
					<c:otherwise>  
						<c:forEach var="member" items="${page.result}">
							<li>
								<p>成员编号${member.id}</p>
								<p>${member.user.nickname}</p>
								<p>${member.nickname}</p>
								<hr>
							</li>
							
						</c:forEach>
						
						
					</c:otherwise>     
					</c:choose> 
                </div>
            </div>    		
			<div class="panel panel-default">
                <div class="panel-heading">群推荐</div>
                <div class="panel-body">
					<ul>
                   		<li><a href="groups/1" target="_blank">Android开发讨论群</a></li>
                   		<li><a href="groups/2" target="_blank">前端开发小组</a></li>
					</ul>
                </div>
            </div>    
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
                <div class="panel-heading">系统公告</div>
                <div class="panel-body">
                1
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
$('#submit').on('click', function() {
	var title = $('#title').val();
	if (title == '') {
		alert('您尚未输入提问');
		return;
	}
	if (title.length < 10) {
		alert('您的提问过短，请将问题说明清楚，网友才能为您解答');
		return;
	}
	//您的提问包含大量无意义字符，请重新提问
	var content = $('#content').val();
	
	$.ajax({
		url: 'questions',
		data: {
			'title': title,
			'content': content
		},
		type: 'POST',
		dataType: 'json',
		success:  function(obj) {
			if (obj.code === 0) {
				window.location.reload();
			} else {
				alert(obj.data);
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
