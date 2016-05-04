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
<title>编辑用户密码</title>

<jsp:include page="../admin/include_head.jsp"></jsp:include>

</head>
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/users">用户列表</a></li>
        </ul>
        <c:if test="${result != null}">
	    	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>
	
    	<form class="form-horizontal" action="/admin/users/password" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${user.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">密码 *</label>
    			<div class="col-sm-4">
	    			<input id="name" class="form-control" type="text" name="password" value="">
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
	    		<input id="btn-ok" class="btn btn-success" type="submit" value="确定" onclick="return valid()" />
    		</div>
        </form>
        
        
    </div>
    <jsp:include flush="true" page="../admin/include_copyright.jsp"/>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script src="js/common.js"></script>
<script>
function valid() {
	var name = $('#name').val();
	if (name == '') {
		alert('板块名称不能为空！');
		return false;
	}
	
	return true;
}
</script>
</body>
</html>
