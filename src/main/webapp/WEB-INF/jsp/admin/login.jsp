<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登陆 - Eshop管理平台</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/admin_common.css" />
<link rel="stylesheet" href="css/admin_login.css" />
</head>
<body>
<div class="wrap">
<div class="content">
	<div class="login">
    	<p class="login-text">用户登陆</p>
        <div>
        	<input id="account" class="user-name" type="text" /> 
        </div>
        <div><input id="password" class="password" type="text" /></div>
        <input id="login" type="button" class="login-btn" value="登陆" />
    </div>
</div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery.md5.js"></script>
<script>
/**
 * 获取url中的参数，获取不到返回null 
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	return r == null ? null : unescape(r[2]);
}

$(document).ready(function(e) {
	
	$('#login').click(function() {
		var account = $('#account').val();
		var password = $('#password').val();
		if (account == '') {
			alert('账号不能为空');
			return;
		}
		if (password == '') {
			alert('密码不能为空');
			return;
		}
		$.ajax({ 
			url: "adlogin",
			data:{  
				account : account,  
				password: $.md5(password)
			},  
			type: 'get', 
			cache: false,
			dataType: 'json', 
			success: function(json) {
				if (json.code === 0) {
					//alert(getUrlParam('redict_url'));
					if (getUrlParam('redict_url') == null) {
						window.location.href = "admin";
					} else {
			        	window.location = getUrlParam('redict_url');
					} 
					
				} else {
					alert("账号与密码不匹配");
				}
			},
			error: function() {
				alert("服务器异常");
			}
		});

	});
	
});
</script>
</body>
</html>

