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
<title>用户登陆</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />

<jsp:include page="include_head.jsp"></jsp:include>

<link rel="stylesheet" href="css/login.css" />
</head>
<body>
<jsp:include flush="true" page="include_header.jsp"/>
<div class="container">
	<div class="ad"><a href=""><img src="images/login-ad.jpg" alt="广告" /></a></div>
    <div class="login">
    	<div id="error-tip" class="tip">请输入账号</div>
    	<form action="login" method="post">
    		<div><input id="login-account" name="password" class="login-account" type="text" placeholder="手机号/用户名/邮箱" /></div>
        	<div><input id="login-password" name="password" class="login-password" type="password" placeholder="输入密码" /></div>
			<input id="login-submit" class="login-submit" type="button" value="登陆" />
		</form>
        <div>
        	<a class="forget-password" href="find_password">忘记密码</a>
        	<a class="register" href="register">免费注册</a>
        </div>
    </div>
</div>
<jsp:include flush="true" page="include_footer.jsp"/>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery.md5.js"></script>
<script>
/**
 * 获取url中的参数，获取不到返回null 
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r) {
		return unescape(r[2]);
	}
	
	return null;
}

$("#login-submit").click(function() {
	var account = $("#login-account").val();
	var password = $("#login-password").val();
	if (account == "") {
		$("#error-tip").text("请输入账号");
		$("#error-tip").show();
		return;
	}
	if (password == "") {
		$("#error-tip").text("请输入密码");
		$("#error-tip").show();
		return;
	}
	$.ajax({ 
		url: "api/v1.0/login",
		data:{  
			account : account,  
			password: $.md5(password)
		},  
		type: 'post', 
		dataType: 'json', 
		success: function(obj) {
			if (obj.code === 0) {
				if (getUrlParam('redict_url') == null) {
					window.location = './';
				} else {
		        	window.location = getUrlParam('redict_url');
				} 
			} else {
				$("#error-tip").text(obj.data);
				$("#error-tip").show();
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