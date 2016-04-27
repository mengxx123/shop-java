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
<title>用户注册</title>
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="eshop/include_head.jsp"></jsp:include>
<link rel="stylesheet" href="asset/eshop/css/register.css" />
</head>
<body>
<jsp:include flush="true" page="eshop/include_header.jsp"/>
<div class="content clearfix">
	<div class="register-box">
        <form class="form-horizontal" action="register" method="post">
        	<div class="register-item">
                <span class="form-item">
                    <label>用户名：</label>
                    <input id="register-account" type="text" name="account" />
                </span>
                <span id="account-tip" class="tip"></span>
            </div>
            <div class="register-item">
                <span class="form-item">
                    <label>昵称：</label>
                    <input id="register-nickname" type="text" name="nickname" />
                </span>
                <span id="nickname-tip" class="tip"></span>
            </div>
        	<div class="register-item">
                <span class="form-item">
                    <label>密码：</label>
                    <input id="register-password" type="password" name="password" />
                </span>
                <span id="password-tip" class="tip"></span>
            </div>
			<div class="register-item">
                <span class="form-item">
                    <label>确认密码：</label>
                    <input id="register-password2" type="password" name="password2" />
                </span>
                <span id="password2-tip" class="tip"></span>
            </div>
            
          	<div class="register-item">
                <span class="form-item">
                    <label>邮箱：</label>
                    <input id="register-email" type="text" name="email" />
                </span>
                <span id="email-tip" class="tip"></span>
            </div>
            <div class="register-item">
                <span class="form-item2">
                    <label>验证码：</label>
                    <input id="register-ckeckCode" class="register-ckeckCode" type="text" name="checkCode" />
                </span>
                <span id="checkCode-tip"></span>
                <input id="get-checkCode" class="get-checkCode" type="button" value="获取验证码" />
                <span id="ckeckCode-tip" class="tip"></span>
            </div>
            <div class="register-item">
            	<p>点击注册，表示您同意<a class="agreement" href="article?id=2" target="_blank">《服务协议》</a></p>
            </div>
            <div class="register-item">
            	<input id="register-submit" class="register-submit" type="button" value="注册" /> 
            </div>
        </form>
    </div>
</div>



<jsp:include flush="true" page="include_footer.jsp"/> 
<script src="http://lib.sinaapp.com/js/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
<script>
<c:choose>
<c:when test="${needEmail == true}">
	var needMail = true;
</c:when>
<c:otherwise>
	var needMail = false;
</c:otherwise>
</c:choose>
var isButtonEnable = true;
var leftTime;

function updateTime() {
	leftTime--;
	if (leftTime == 0) {
		isButtonEnable = true;
		$("#get-checkCode").val('重新获取验证码');
		$("#get-checkCode").removeClass("get-checkCode-unable");
		$("#get-checkCode").addClass("get-checkCode");
	} else {
		$("#get-checkCode").val('重新获取验证码(' + leftTime + ')');
		setTimeout("updateTime()", 1000);
	}
}

$("#get-checkCode").click(function() {
	
	var email = $("#register-email").val();
	if (email == "") {
		$("#email-tip").text("邮箱不能为空");
		return;
	} else {
 
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (pattern.test(email)) {
			$("#email-tip").text("");
		} else {
			$("#email-tip").text("邮箱格式不正确");
			return;
		}
		
	}
	
	if (isButtonEnable) {
		$("#get-checkCode").removeClass("get-checkCode");
		$("#get-checkCode").addClass("get-checkCode-unable");
		isButtonEnable = false;
		leftTime = 60;
		$("#get-checkCode").val('重新获取验证码(' + leftTime + ')');
		setTimeout("updateTime()", 1000);
		
		$.ajax({ 
		url: "api/v1.0/checkCode", 
		data:{  
			email: email
		},
		type: 'get', 
		cache: false,
		dataType: 'html', 
		success: function(data) {
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			if (state == "success") {
				alert("成功");
			} else {
				alert("退出失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {

		}
	});
	}

	

});
$("#register-account").blur(function() {
	var account = $("#register-account").val().trim();
	if (account == "") {
		$("#account-tip").text("账号不能为空");
	} else {
		$("#account-tip").text("");
	}
});
$("#register-nickname").blur(function() {
	var account = $("#register-nickname").val().trim();
	if (account == "") {
		$("#nickname-tip").text("昵称不能为空");
	} else {
		$("#nickname-tip").text("");
	}
});
$("#register-password").blur(function() {
	var password = $("#register-password").val().trim();
	if (password == "") {
		$("#password-tip").text("密码不能为空");
	} else {
		$("#password-tip").text("");
		if (password == $("#register-password2").val().trim()) {
			$("#password2-tip").text("");
		}
	}
});
$("#register-password2").blur(function() {
	var password2 = $("#register-password2").val().trim();
	if (password2 == "") {
		$("#password2-tip").text("确认密码不能为空");
	} else if (password2 != $("#register-password").val().trim()) {
		$("#password2-tip").text("两次密码输入不一致");
	} else {
		$("#password2-tip").text("");
	}
});
$("#register-email").blur(function() {
	var email = $("#register-email").val().trim();
	if (email == "") {
		$("#email-tip").text("邮箱不能为空");
	} else {
 
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (pattern.test(email)) {
			$("#email-tip").text("");
		} else {
			$("#email-tip").text("邮箱格式不正确");
		}
		
	}
});



$("#register-ckeckCode").blur(function() {
	var ckeckCode = $("#register-ckeckCode").val().trim();
	if (ckeckCode == "") {
		$("#ckeckCode-tip").text("验证码不能为空");
	} else {
		$("#ckeckCode-tip").text("");
	}
});
$("#register-submit").click(function() {
	var account = $("#register-account").val().trim();
	if (account == "") {
		$("#account-tip").text("账号不能为空");
		return;
	}
	var nickname = $("#register-nickname").val().trim();
	if (nickname == "") {
		$("#nickname-tip").text("昵称不能为空");
		return;
	}
	var password = $("#register-password").val().trim();
	if (password == "") {
		$("#password-tip").text("密码不能为空");
		return;
	}
	var password2 = $("#register-password2").val().trim();
	if (password2 == "") {
		$("#password2-tip").text("确认密码不能为空");
		return;
	}
	
	var email = $("#register-email").val().trim();
	if (needMail && email == "") {
		$("#email-tip").text("邮箱不能为空");
		return;
	}
	if (password != password2) {
		$("#password2-tip").text("两次密码输入不一致");
		return;
	}
	var checkCode = $("#register-ckeckCode").val();
	if (needMail && checkCode == "") {
		$("#checkCode-tip").text("验证码不能为空");
		return;
	}

	$.ajax({ 
		url: "api/v1.0/register", 
		data:{  
			account: account, 
			nickname: nickname, 
			password: password,
			password2: password2,
			email: email,
			checkCode: checkCode
		},
		type: 'post', 
		dataType: 'json', 
		success: function(obj) {
			if (obj.code === 0) {
				window.location = "login";
			} else {
				alert("注册失败，" + obj.data);
			}
		},
		error: function() {
			alert("服务器出错");
		}
	});
});
</script>
</body>
</html>