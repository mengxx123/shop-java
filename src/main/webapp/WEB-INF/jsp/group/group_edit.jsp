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
<title>编辑群组</title>
<base href="<%=basePath%>">

<jsp:include page="../admin/include_head.jsp"></jsp:include>

</head>
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="admin/groups">群组列表</a></li>
        </ul>
        <c:if test="${result != null}">
	    	<div class="eui-alert eui-alert-info">${result}</div>
	    </c:if>
	    
	    <form class="form-horizontal" action="admin/groups" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${group.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">群名称 *</label>
    			<div class="col-sm-4">
	    			<input id="name" class="form-control" type="text" name="name" value="${group.name}">
    			</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label col-sm-3">群介绍</label>
    			<div class="col-sm-4">
	    			<textarea id="description" class="form-control" name="description" rows="4">${group.description}</textarea>
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
	    		<input id="btn-ok" class="btn btn-success" type="submit" value="确定" onclick="return valid()" />
    		</div>
        </form>
    </div><!-- /.row -->
   
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script src="js/common.js"></script>
<script>
function valid() {

	var name = $('#name').val();
	if (name == '') {
		alert('群姓名不能为空！');
		return false;
	}
	
	return true;
}
</script>
</body>
</html>
