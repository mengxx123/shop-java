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
<title>文章列表</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>

<style>
.column-id {
	width: 10px;
}
.column-title {
	width: 150px;
}
.column-content {
}
.column-category {
	width: 100px;
}
.column-time {
	width: 150px;
}
.column-operate {
	width: 100px;
}
</style>
</head>
  
<body> 
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/articles">文章列表</a></li>
        </ul>
        <a class="btn btn-primary pull-right" action="/admin/article_edit">添加文章</a>
        
        <c:if test="${result != null}">
	    	<div class="admin-section">
		    	<p>${result}</p>
		    </div>
	    </c:if>

		<form class="form-inline" action="/admin/articles" method="get" accept-charset="utf-8">
        	<select class="form-control input-sm" id="article-cat" name="selectAge" id="selectAge">   
	            <option value="0">全部分类</option>  
	            <option value="1">水果</option>   
	            <option value="2">  香蕉</option>   
	            <option value="3">  苹果</option>   
	            <option value="4">    红富士</option>   
	            <option value="5">    青苹果</option>
	            <option value="6">家具</option>
	            <option value="7">  椅子</option>
	        </select>
	        <div class="input-group input-group-sm">
		        <input id="search-input" class="form-control" name="keyword" type="text" value="${keyword}" placeholder="文章标题">
	        	<div class="input-group-btn">
			        <input class="btn btn-primary" onclick="return searchValid()" type="submit" value="搜索">
	        	</div>
	        </div>
        </form>
    
    	<table class="table">
            <thead>
                <tr>
                	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                    <th class="column-category">文章分类</th>
                    <th class="column-title">文章标题</th>
                    <th class="column-content">文章内容</th>
                    <th class="column-time">修改日期</th>
                    <th class="column-operate">操作</th>
                </tr>
            </thead>
            <tbody>
				<c:choose>  
				<c:when test="${fn:length(page.result) == 0}">
					<tr>
	                	<td class="no-records" colspan="6">没有找到任何记录</td>
	                </tr> 
				</c:when>  
				<c:otherwise>  
					<c:forEach var="article" items="${page.result}">
						<tr>
		                	<td><input type="checkbox" name="article-select" value="${article.id}" /></td>
		                    <td><a href="">${article.category.name}</a></td>
		                    <td><a action="/admin/articles/${article.id}">${article.title}</a></td>
		                    <td>${article.content}</td>
		                    <td><fmt:formatDate value="${article.addTime}" pattern="yyyy-M-d HH:mm:ss" /></td>
		                    <td>
		                    	<a class="btn btn-info btn-xs" href="/articles/${article.id}" target="_blank" title="查看"><i class="fa fa-eye"></i></a>
	                    		<a class="btn btn-success btn-xs" action="/admin/articles/${article.id}" target="_blank" title="编辑"><i class="fa fa-edit"></i></a>
	                    		<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem(${article.id})" target="_blank" title="删除"><i class="fa fa-trash"></i></a>
		                    </td>
		                </tr>
					</c:forEach>
					
					
				</c:otherwise>     
				</c:choose> 

                
            </tbody>
        </table>
        
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
    
</div>
<jsp:include flush="true" page="../admin/admin_footer.jsp"/>
<script src="js/admin.js"></script>
<script>
var page = ${page.currentPage}; // 当前页
var totalPage = ${page.totalPage}; // 总页数

function deleteItem(id) {
	var text = '你确定删除该文章吗？';
	var url = 'article_delete?id=' + id;
	var onSuccess = function() {
		location.reload(true); 
	};
	ajaxDelete(text, url, onSuccess);
}

/* 删除反馈，不带提示，同步执行 */
function deleteItem2(id) {
	$.ajax({ 
		url: "/article_delete?id=" + id, 
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
	/*
	$("#search-input").click(function(){
		var type = $("#article-cat").children('option:selected').val();
		if (type == 0) {
			alert("全部");	
		} else if (type == 1) {
			
			
		} else if (type == 2) {
			alert("批量移动");	
		}

	});
	*/

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
	 var href = "admin/articles?page=" + page;
	 if (keyword != "") {
		 href += "&keyword=" + encodeURI(keyword);
	 }
	 window.location.href = href;
}
</script>
</body>
</html>


