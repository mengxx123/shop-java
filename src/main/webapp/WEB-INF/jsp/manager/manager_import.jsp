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
<title>导入管理员</title>
<link rel="stylesheet" href="css/admin_common.css" />
</head>

<body>
<div class="admin-content">
	<div class="admin-section">
    	<a href="/admin" target="_blank">Eshop管理平台</a> - <a action="/admin/manager_edit">添加管理员</a>
        <a class="admin-btn float-right" action="/admin/manager">管理员列表</a>
    </div>
    <c:if test="${result != null}">
    	<div class="admin-section">
	    	<p>${result}</p>
	    </div>
    </c:if>
    <div class="admin-section">
    	<form name="excelImportForm" action="importBrandSort" method="post" onsubmit="return checkImportPath();" enctype="multipart/form-data" id="excelImportForm">
			<label><input class="admin-file" id="excel_file" type="file" name="filename"  accept="xls"/></label>
			<input class="admin-btn" id="excel_button" type="submit" value="导入Excel"/>
		</form>      
    </div>
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/common.js"></script>
<script>
function valid() {
	var account = $('#account').val();
	if (account == '') {
		alert('账号不能为空！');
		return false;
	}
	var name = $('#name').val();
	if (name == '') {
		alert('姓名不能为空！');
		return false;
	}
	var password = $('#password').val();
	if (password == '') {
		alert('密码不能为空！');
		return false;
	}
	var password2 = $('#password2').val();
	if (password2 == '') {
		alert('确认密码不能为空！');
		return false;
	}
	if (password != password2) {
		alert('两次密码输入不一致！');
		return false;
	}
	return true;
}
</script>
</body>
</html>
