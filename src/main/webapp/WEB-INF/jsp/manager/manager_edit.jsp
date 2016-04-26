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
<title>编辑管理员</title>
<base href="<%=basePath%>">

<jsp:include page="../admin/include_head.jsp"></jsp:include>

</head>
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="admin/managers">管理员列表</a></li>
        </ul>
        <c:if test="${result != null}">
	    	<div class="alert alert-info">${result}</div>
	    </c:if>
	
    	<form class="form-horizontal" action="managers" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${manager.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">账号 *</label>
    			<div class="col-sm-4">
	    			<input id="account" class="form-control" type="text" name="account" value="${manager.account}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">管理员姓名 *</label>
    			<div class="col-sm-4">
	    			<input id="name" class="form-control" type="text" name="name" value="${manager.name}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">密码 *</label>
    			<div class="col-sm-4">
	    			<input id="password" class="form-control" type="password" name="password" value="${manager.password}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">确认密码 *</label>
    			<div class="col-sm-4">
	    			<input id="password2" class="form-control" type="password" value="${manager.password}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">备注</label>
    			<div class="col-sm-4">
	    			<input class="form-control" type="text" name="note" value="${manager.note}">
	    			<p class="help-block">管理员备注信息，比如所负责的业务等等</p>
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
	    		<input id="btn-ok" class="btn btn-success" type="submit" value="确定" onclick="return valid()" />
    		</div>
        </form>
    </div>
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
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
