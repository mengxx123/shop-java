<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员列表</title>

<jsp:include page="../admin/include_head.jsp"></jsp:include>

<style>
.column-id {
	width: 10px;
}
.column-username {
	width: 200px;
}
.column-nickname {
	width: 200px;
}
.column-email {
	width: 300px;
}
.column-register-time {
	width: 100px;
}
</style>
</head>
  
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/user">用户列表</a></li>
        </ul>
	</div>
	<div class="admin-section">
    	<form action="/admin/brand" method="get" accept-charset="utf-8">
        	<img class="search-icon" src="/images/admin/icon_search.gif" alt="搜索图标" />
	        <label class="admin-label">品牌名称</label>
	        <input id="search-input" name="keyword" class="admin-input margin-left-10px" type="text" value="${keyword}" />
	        <input class="admin-btn margin-left-10px" onclick="return searchValid()" type="submit" value="搜索" />
        </form>
    </div> 
    
	<div class="row">
    	<table class="table">
            <thead>
                <tr>
                	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                    <th class="column-username">账号</th>
                    <th class="column-nickname">昵称</th>
                    <th class="">头像</th>
                    <th class="column-email">邮箱</th>
                    <th class="column-register-time">注册时间</th>
                    <th class="column-operate">操作</th>
                </tr>
            </thead>
            <tbody>
				<c:choose>  
				<c:when test="${fn:length(page.result) == 0}"> 
					<tr>
	                	<td class="no-records" colspan="5">没有找到任何记录</td>
	                </tr>
				</c:when>  
				<c:otherwise>  
					<c:forEach var="user" items="${page.result}">
						<tr>
		                	<td><input type="checkbox" name="article-select" value="${user.id}"></td>
		                    <td>${user.name}</td>
		                    <td>${user.nickname}</td>
		                    <td>
		                    	<img class="avatar avatar-sm" src="${user.headPath}" alt="头像"></td>
		                    <td>
		                    	${user.email}
		                    	<c:choose>  
								<c:when test="${user.isEmailValid == 0}"> 
									<span class="label label-warning">未验证</span>
								</c:when>  
								<c:otherwise>
									<span class="label label-success">已验证</span>
								</c:otherwise>
								</c:choose>
		                    </td>
		                    <td><fmt:formatDate value="${user.registerTime}" pattern="yyyy-M-d HH:mm:ss" /></td>
		                    <td>
		                    	<a class="btn btn-info btn-xs" href="users/${user.id}" target="_blank" title="查看"><i class="fa fa-eye"></i></a>
		                    	<a class="btn btn-success btn-xs" action="/admin/users/${user.id}" title="编辑"><i class="fa fa-edit"></i></a>
		                    	<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem(${user.id})" target="_blank" title="删除"><i class="fa fa-trash"></i></a>
		                    </td>
		                </tr>
					</c:forEach>		
				</c:otherwise>     
				</c:choose>      
            </tbody>
        </table>
        
    </div> 
    <jsp:include flush="true" page="../admin/include_page.jsp"/>
	<div class="form-inline pull-left">
    	<select class="form-control" name="selectAge" id="operate-type">   
            <option value="0">请选择...</option>  
            <option value="1">批量删除</option>   
            <option value="2">转移到分类</option>   
        </select>
        <a id="operate-ok" class="btn btn-success" href="javascript:;">确定</a>
    </div>
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>

<script>
var page = ${page.currentPage}; // 当前页
var totalPage = ${page.totalPage}; // 总页数

function operateOk() {

}

/* 删除反馈，带提示 */
function deleteItem(id) {
	
	if (confirm("你确定删除该用户吗？")) {
		$.ajax({ 
			url: "/user_delete?id=" + id, 
			type: 'get', 
			cache: false,
			dataType: 'html', 
			success: function(data) {
				var jsonObj = eval('(' + data + ')');
				var state = jsonObj.state;
				if (state == "success") {
					location.reload(true); 
				} else {
					alert("删除失败");
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
}

/* 删除反馈，不带提示，同步执行 */
function deleteItem2(id) {
	$.ajax({ 
		url: "/user_delete?id=" + id, 
		type: 'get', 
		cache: false,
		async: false,
		dataType: 'html', 
		success: function(data) {
			var jsonObj = eval('(' + data + ')');
			var state = jsonObj.state;
			if (state == "success") {
				//location.reload(true); 
			} else {
				//alert("删除失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrow) {
			//alert("异常！");
			//alert(XMLHttpRequest.status);
            //alert(XMLHttpRequest.readyState);
            //alert(textStatus);
		}
	});
}

/* 搜索表单验证 */
function searchValid() {
	/*
	var keyword = $("#search-input").val();
	if (keyword == "") {
		alert("请输入关键词");
		return false;
	}
	*/
	return true;
}

$(document).ready(function(e) {
	/* 全选操作 */
	$("#select-all").click(function() {
		if ($(this).is(':checked') == true) {
			// 全选
			$("input[name='article-select']").attr("checked",true);	
		} else {
			$("input[name='article-select']").attr("checked",false);
		}
	});
	
	/* 批量操作确定 */
	$("#operate-ok").click(function(){

		var type = $("#operate-type").children('option:selected').val();
		if (type == 0) {
			alert("请选择操作类型");	
		} else if (type == 1) {
			// 批量删除	
			$("input[name='article-select']:checked").each(function() {
				deleteItem2($(this).val());
			});	
			window.location.reload(true);
			
		} else if (type == 2) {
			alert("批量移动");	
		}


	});
	
	$("#search-input").click(function(){
		var type = $("#article-cat").children('option:selected').val();
		if (type == 0) {
			alert("全部");	
		} else if (type == 1) {
			
			
		} else if (type == 2) {
			alert("批量移动");	
		}

	});

	// 选择页数
	$('#select-page').change(function(){ 
		var page = $(this).children('option:selected').val(); // 选中的页数
		changePage(page);
	});
	
	// 第一页
	$('#first-page').click(function() {
		changePage(1);
	});
	
	// 上一页
	$('#prev-page').click(function() {
		changePage(page - 1);
	});
	
	// 下一页
	$('#next-page').click(function() {
		changePage(page + 1);
	});
	
	// 最末页
	$('#last-page').click(function() {
		changePage(totalPage);
	});
	
	
});

/**
 * 改变页数
 * @param page {} 页数
 * @returns {void}
 */
function changePage(page) {
	 var keyword = $('#search-input').val();
	 var href = "admin/users?page=" + page;
	 if (keyword != "") {
		 href += "&keyword=" + encodeURI(keyword);
	 }
	 window.location.href = href;
}

</script>
</body>
</html>

