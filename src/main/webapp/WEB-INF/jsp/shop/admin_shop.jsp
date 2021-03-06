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
<title>店铺列表</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<style>
.column-id {
	width: 10px;
}
.column-name {
	width: 200px;
}
.column-desc {
	width: 100px;
}
.column-seller {
	width: 100px;
}
.column-time {
	width: 100px;
}
.column-operate {
}
</style>
</head>
  
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/shops">店铺列表</a></li>
        </ul>
        <a class="btn btn-primary pull-right" action="/admin/shop_edit">添加店铺</a>

    	<form action="/admin/brand" method="get" accept-charset="utf-8">
        	<img class="search-icon" src="/images/admin/icon_search.gif" alt="搜索图标" />
	        <label class="admin-label">店铺名称</label>
	        <input id="search-input" name="keyword" class="admin-input margin-left-10px" type="text" value="${keyword}" />
	        <input class="admin-btn margin-left-10px" onclick="return searchValid()" type="submit" value="搜索" />
        </form>

    	<table class="table">
            <thead>
                <tr>
                	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                    <th class="column-name">名称</th>
                    <th class="column-desc">描述</th>
                    <th class="column-seller">商家</th>
                    <th class="column-time">创建时间</th>
                    <th class="column-operate">操作</th>
                </tr>
            </thead>
            <tbody>
				<c:choose>  
				<c:when test="${fn:length(page.result) == 0}"> 
					<tr>
	                	<td class="no-records" colspan="4">没有找到任何记录</td>
	                </tr>
				</c:when>  
				<c:otherwise>  
					<c:forEach var="shop" items="${page.result}">
						<tr id="shop-${shop.id}">
		                	<td><input type="checkbox" name="article-select" value="${shop.id}"></td>
		                    <td>${shop.name}</td>
		                    <td>${shop.description}</td>
		                    <td>${shop.user.name}</td>
		                    <td><fmt:formatDate value="${shop.createTime}" pattern="yyyy-M-d HH:mm:ss" /></td>
		                    <td>
	                    		<a class="btn btn-success btn-xs" action="/admin/shops/${shop.id}" title="编辑"><i class="fa fa-edit"></i></a>
	                    		<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem('${shop.id}')" title="删除"><i class="fa fa-trash"></i></a>
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

function deleteItem(id) {
	var text = '你确定删除该店铺吗？';
	var url = '/shop_delete?id=' + id;
	var onSuccess = function() {
		$('#shop-' + id).remove();
	};
	ajaxDelete(text, url, onSuccess);
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
	 var href = "admin/shops?page=" + page;
	 if (keyword != "") {
		 href += "&keyword=" + encodeURI(keyword);
	 }
	 window.location.href = href;
}

</script>
</body>
</html>

