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
<title>网站设置</title>

<jsp:include page="../admin/include_head.jsp"></jsp:include>

</head>
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/settings">网站设置</a></li>
        </ul>
        <c:if test="${result != null}">
	    	<div class="alert alert-info">${result}</div>
	    </c:if>
	
    	<form class="form-horizontal" action="/admin/settings" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="id" value="${manager.id}">
    		<div class="form-group">
    			<label class="control-label col-sm-3">网站名</label>
    			<div class="col-sm-4">
	    			<input id="website-name" class="form-control" type="text" name="website_name" value="${websiteName}">
    			</div>
    		</div>
    		<div class="col-sm-4 col-sm-offset-3">
	    		<input id="btn-ok" class="btn btn-success" type="button" value="确定">
    		</div>
        </form>
    </div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script src="js/common.js"></script>
<script>

$(document).ready(function() {
	$('#btn-ok').on('click', saveSetting);
});

function saveSetting() {
	var websiteName = $('#website-name').val();
	if (websiteName == '') {
		eui.alert('网站名不能为空！');
		return false;
	}
	
	$.ajax({ 
		url: "/admin/settings", 
		data: {
			website_name: websiteName
		},
		type: 'post', 
		dataType: 'json', 
		success: function(obj) {
			if (obj.code === 0) {
				location.reload(true); 
			} else {
				alert("设置失败，" + obj.data);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			//alert("异常！");
			alert(XMLHttpRequest.status);
	        alert(XMLHttpRequest.readyState);
	        alert(textStatus);
		}
	});
}
</script>
</body>
</html>
