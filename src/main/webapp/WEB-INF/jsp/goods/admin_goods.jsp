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
<title>商品列表</title>
<jsp:include page="../admin/include_head.jsp"></jsp:include>

<style>
.column-id {
	width: 10px;
}
.column-name {
}
.column-category {
	width: 100px;
}
.column-price {
	width: 100px;
}
.column-on-sale {
	width: 100px;
}
.column-operate {
	width: 100px;
}
.column-num {
	width: 100px;
}
</style>

</head>
  
<body>
<div class="mycontainer">
	<div class="row">
		<ul class="breadcrumb">
            <li><a href="/admin" target="_blank">eSchool管理平台</a></li>
            <li><a href="/admin/goodses">商品列表</a></li>
        </ul>
        
		<div>
			<form class="form-inline pull-left" action="/admin/goodses">
		        <select id="category" class="form-control" name="">   
		            <option value="0">全部分类</option>  
		            <option value="1">水果</option>   
		            <option value="2">  香蕉</option>   
		            <option value="3">  苹果</option>   
		            <option value="4">    红富士</option>   
		            <option value="5">    青苹果</option>
		            <option value="6">家具</option>
		            <option value="7">  椅子</option>
		        </select>
		        <select id="brand" class="form-control" name="">   
		            <option value="0">全部</option>  
		            <option value="1">特步</option>   
		            <option value="2">三星</option>   
		            <option value="3">小米</option>   
		            <option value="4">苹果</option>   
		        </select>
		        <select id="type" class="form-control" name="">   
		            <option value="0">全部</option>  
		            <option value="1">新品</option>   
		            <option value="2">热销</option>   
		            <option value="3">精品</option>   
		            <option value="4">特价</option>   
		        </select>
		        <div class="input-group">
			        <input id="search-input" name="keyword" class="form-control" type="text" value="${keyword}"  placeholder="商品名称">
		        	<div class="input-group-btn">
				        <input class="btn btn-primary" onclick="return searchValid()" type="submit" value="搜索">
		        	</div>
		        </div>
	        </form>
	        
	        <a class="btn btn-primary pull-right" href="/admin/goods_edit">添加商品</a>
		</div>
			

    	<table class="table">
            <thead>
                <tr>
                	<th class="column-id"><input id="select-all" type="checkbox" value="0"></th>
                    <th class="column-name">名称</th>
                    <th class="column-category">分类</th>
                    <th class="column-price">价格</th>
                    <th class="column-num">库存</th>
                    <th class="column-on-sale">上架</th>
                    <th class="column-on-sale">新品</th>
                    <th class="column-on-sale">热销</th>
                    <th class="column-on-sale">特价</th>
                    <th class="column-operate">操作</th>
                </tr>
            </thead>
            <tbody>
				<c:choose>  
				<c:when test="${fn:length(page.result) == 0}">     
		         	<tr>
	                	<td class="no-records" colspan="9">没有找到任何记录</td>
	                </tr>
				</c:when>  
				<c:otherwise>
					<c:forEach var="goods" items="${page.result}">   
						<tr id="goods-${goods.id}">
		                	<td><input type="checkbox" name="article-select" value="${goods.id}"></td>
		                	<td><a href="/admin/goodses/${goods.id}" target="_blank">${goods.name}</a></td>
		                	<c:choose>
		                		<c:when test="${goods.category == null}">
		                			<td>${goods.category.id}</td>
		                		</c:when>
		                		<c:otherwise>
		                			<td>${goods.category.name}</td>
		                		</c:otherwise>
		                	</c:choose>
		                    <td>${goods.price}</td>
		                    <td>${goods.number}</td>
		                    <td><a href="javascript:;" onclick="read(1)"><img src="/images/admin/icon_yes.gif" /></a></td>
		                   	<td><a href="javascript:;" onclick="read(1)"><img src="/images/admin/icon_yes.gif" /></a></td>
		                    <td><a href="javascript:;" onclick="read(1)"><img src="/images/admin/icon_yes.gif" /></a></td>
		                    <td><a href="javascript:;" onclick="read(1)"><img src="/images/admin/icon_yes.gif" /></a></td> 
		                    <td>
		                    	<a class="btn btn-info btn-xs" href="/goodses/${goods.id}" target="_blank" title="查看"><i class="fa fa-eye"></i></a>
	                    		<a class="btn btn-success btn-xs" href="/admin/goodses/${goods.id}" target="_blank" title="编辑"><i class="fa fa-edit"></i></a>
	                    		<a class="btn btn-danger btn-xs" href="javascript:;" onclick="deleteItem('${goods.id}')" target="_blank" title="删除"><i class="fa fa-trash"></i></a>
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
	var text = '你确定删除该商品吗？';
	var url = '/goods_delete?id=' + id;
	var onSuccess = function() {
		$('#goods-' + id).remove();
	};
	ajaxDelete(text, url, onSuccess);
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
	 //window.location.href = "admin/goods?page=" + page + "&keyword=" + encodeURI(keyword);
	 window.location.href = "admin/goodses?page=" + page;
}
</script>
</body>
</html>

