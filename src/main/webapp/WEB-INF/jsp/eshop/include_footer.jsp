<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 页脚 -->
<footer class="es-footer">
    <div class="container">
        <ul class="link">
            <li><a href="articles/1" target="_blank">关于我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="articles/3" target="_blank">团队介绍</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="articles/4" target="_blank">加入我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="articles/2" target="_blank">服务协议</a></li>
        </ul>
        <p class="copyright clearfix">© 2016 <a href="./">${websiteName}</a></p>
    </div>
</footer>
<!-- /页脚  -->
<script src="asset/lib/jquery/jquery-1.10.2.min.js"></script>
<script src="asset/lib/eui/eui.min.js"></script>
<script src="asset/lib/eui/eui.dialog.js"></script>
<script src="asset/eshop/js/common.js"></script>
<script>
$(document).ready(function(e) {
    $("#login").click(function(e) {
        window.location = 'login?redict_url=' + escape(window.location);
    });
});

$("#loginout").click(function() {
	$.ajax({ 
		url: "api/v1.0/loginout", 
		dataType: 'json', 
		success: function(obj) {
			if (obj.code === 0) {
				location.reload(true); 
			} else {
				alert("退出失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			alert('服务器异常');
		}
	});
});

function ajax(text, url, onSuccess) {

	eui.confirm(text, function(){
		$.ajax({ 
			url: url, 
			type: 'get', 
			cache: false,
			dataType: 'json', 
			success: function(obj) {
				if (obj.code === 0) {
					onSuccess(obj);
				} else {
					//eui.alert("删除失败，" + obj.data); // TODO 这里有问题，显示不出来
					alert("操作失败，" + obj.data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				eui.alert("系统出错");
				//alert(XMLHttpRequest.status);
	            //alert(XMLHttpRequest.readyState);
	            //alert(textStatus);
			}
		});
    });
	
	
}
</script>
