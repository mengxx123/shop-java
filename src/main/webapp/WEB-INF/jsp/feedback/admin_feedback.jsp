<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>反馈管理</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>
<style>
.column-id {
	width: 10px;
}
.column-type {
	width: 100px;
}
.column-user {
	width: 100px;
}
.column-is-read {
	width: 50px;
}
.column-time {
	width: 200px;
}
</style>
</head>
  
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a action="/admin/feedbacks">反馈列表</a></li>
        </ul>
		
		<div class="">
	    	<img class="search-icon" src="/images/admin/icon_search.gif" alt="搜索图标" />
	        <select id="article-cat" name="selectAge" id="selectAge">   
	            <option value="0">全部分类</option>  
	            <option value="1">水果</option>   
	            <option value="2">  香蕉</option>   
	            <option value="3">  苹果</option>   
	            <option value="4">    红富士</option>   
	            <option value="5">    青苹果</option>
	            <option value="6">家具</option>
	            <option value="7">  椅子</option>
	        </select>
	        <label>文章标题</label><input id="search-input" class="admin-input margin-left-10px" type="text" /><input class="admin-btn margin-left-10px" type="submit" value="搜索" />
	    </div> 
	    
	    <table class="table">
            <thead>
            <tr>
            	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                <th class="column-type">类型</th>
                <th class="column-content">反馈内容</th>
                <th class="column-user">用户</th>
                <th class="column-time">反馈日期</th>
                <th class="column-is-read">已读</th>
                <th class="column-operate">操作</th>
            </tr>
            </thead>
            <tbody>
				<c:choose>  
				<c:when test="${fn:length(page.result) == 0}">     
		         	<tr>
	                	<td class="no-records" colspan="7">没有找到任何记录</td>
	                </tr>
				</c:when>  
				<c:otherwise>  
					<c:forEach var="feedback" items="${page.result}">   
						<tr>
		                	<td><input type="checkbox" name="article-select" value="${feedback.id}"></td>
		                	<td>普通反馈</td>
		                    <td><a href="">${feedback.content}</a></td>
		                    <td><a href="">${feedback.user.name}</a></td>
		                    <td><fmt:formatDate value="${feedback.time}" pattern="yyyy-M-d HH:mm:ss" /></td>
		                    <td><a href="javascript:;" onclick="read(${feedback.id})"><img src="/images/admin/icon_yes.gif" /></a></td>
		                    <td>
		                    	<a class="btn btn-info btn-xs" href="sections/${feedback.id}" target="_blank" title="查看"><i class="fa fa-eye"></i></a>
	                    		<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem(${feedback.id})" title="删除"><i class="fa fa-trash"></i></a>
		                    </td>
						</tr>
					</c:forEach>
					
					
				</c:otherwise>     
				</c:choose> 

                
            </tbody>
        </table>
        
        <jsp:include flush="true" page="../admin/include_page.jsp"/>
        
    </div><!-- /.row -->
    
	<div class="operate">
    	<select class="admin-select" name="selectAge" id="operate-type">   
            <option value="0">请选择...</option>  
            <option value="1">批量删除</option>   
            <option value="2">转移到分类</option>   
        </select>
        <a id="operate-ok" class="admin-btn" href="javascript:;">确定</a>
    </div>
    <div class="admin-section margin-top-10px">
    	<p class="copyright">版权所有 © 2015-2016 Eshop</p>
    </div>
</div>
<script src="http://code.jquery.com/jquery-1.4.4.min.js" type="text/javascript"></script>

<script>
var page = ${page.currentPage}; // 当前页
var totalPage = ${page.totalPage}; // 总页数

function operateOk() {

}

function deleteItem(id) {
	var text = '你确定删除该反馈吗？';
	var url = 'feedbacks_delete?id=' + id;
	var onSuccess = function() {
		location.reload(true); 
	};
	ajaxDelete(text, url, onSuccess);
}

/* 删除反馈，不带提示，同步执行 */
function deleteItem2(id) {
	$.ajax({ 
		url: "/feedback_delete?id=" + id, 
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

function read(id) {
	alert("已读" + id);
}

function unread(id) {
	alert("未读" + id);
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
	 window.location.href = "admin/feedbacks?page=" + page + "&keyword=" + encodeURI(keyword);
}
</script>
</body>
</html>

