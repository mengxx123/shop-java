<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>    
<title>基本设置</title>
<meta name="keywords" content="">
<meta name="description" content="">

<base href="<%=basePath%>">
<jsp:include flush="true" page="../include_head.jsp"/>

</head>
<body>
<jsp:include flush="true" page="../include_header.jsp"/> 
  
 	<p>原密码：<input id="old-password" type="text" /></p>
 	<p>新密码：<input id="new-password" type="text" /></p>
 	<p>确认密码：<input id="new-password2" type="text" /></p>
 	<input id="modify" type="button" value="修改" />
<jsp:include flush="true" page="../include_footer.jsp"/>
<script>
$("#modify").click(function() {
	var oldPassword = $("#old-password").val();
	var newPassword = $("#new-password").val();
	var newPassword2 = $("#new-password").val();
	if (oldPassword == "") {
		alert("原密码不能为空");
		return;
	}
	if (newPassword == "") {
		alert("新密码不能为空");
		return;
	}
	if (newPassword2 == "") {
		alert("确认密码不能为空");
		return;
	}
	if (newPassword != newPassword2) {
		alert("两次密码输入不一致，请重新输入");
		return;
	}
	$.ajax({ 
		url: "api/v1/login",
		data:{  
			account : account,  
			password: password
		},  
		type: 'post', 
		cache: false,
		dataType: 'html', 
		success: function(data) {
			//alert("成功！" + data);
			//由JSON字符串转换为JSON对象
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			//alert(state);
			if (state == "success") {
				//alert("登陆成功");
				if (getUrlParam('redict_url') != "") {
		        	window.location = getUrlParam('redict_url');
				}
			} else {
				alert("登陆失败，账号不存在或密码错误");
			}
		},
		error: function() {
			alert("异常！");
		}
	});
});
</script>  
</body>
</html>
