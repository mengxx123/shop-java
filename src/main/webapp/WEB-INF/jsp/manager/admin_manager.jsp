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
<title>管理员列表</title>
<base href="<%=basePath%>">

<jsp:include page="../admin/include_head.jsp"></jsp:include>

<style>
.column-id {
	width: 10px;
}
.column-account {
	width: 100px;
}
.column-name {
	width: 100px;
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
            <li><a href="admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="admin/managers">管理员列表</a></li>
        </ul>
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline pull-left" action="admin/brand" method="get">
			        <input id="search-input" class="form-control" name="keyword" type="text" value="${keyword}" placeholder="管理员姓名"/>
			        <input class="btn btn-primary" onclick="return searchValid()" type="submit" value="搜索" />
		        </form>
		        <div class="pull-right">
					<a class="btn btn-info" href="admin/manager_edit">添加管理员</a>
					<a class="btn btn-info" href="admin/manager_import">导入管理员</a>
					<a class="btn btn-info" href="admin/manager_export">导出管理员</a>
				</div>
			</div>
	    </div> 

    	<table class="table">
            <thead>
                <tr>
                	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                    <th class="column-account">账号</th>
                    <th class="column-name">姓名</th>
                    <th class="column-note">备注</th>
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
					<c:forEach var="manager" items="${page.result}">
						<tr>
		                	<td><input type="checkbox" name="article-select" value="${manager.id}"></td>
		                    <td>${manager.account}</td>
		                    <td>${manager.name}</td>
		                    <td>${manager.note}</td>
		                    <td>
	                    		<a class="btn btn-success btn-xs" href="admin/manager/${manager.id}" target="_blank" title="编辑"><i class="fa fa-edit"></i></a>
	                    		<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem(${manager.id})" target="_blank" title="删除"><i class="fa fa-trash"></i></a>
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
	var text = '你确定删除该管理员吗？';
	var url = 'manager_delete?id=' + id;
	var onSuccess = function() {
		location.reload(true); 
	};
	ajaxDelete(text, url, onSuccess);
}

/* 删除反馈，不带提示，同步执行 */
function deleteItem2(id) {
	$.ajax({ 
		url: "manager_delete?id=" + id, 
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
	 var href = "admin/managers?page=" + page;
	 if (keyword != "") {
		 href += "&keyword=" + encodeURI(keyword);
	 }
	 window.location.href = href;
}

</script>
</body>
</html>

