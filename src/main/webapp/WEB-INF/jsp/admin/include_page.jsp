<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="form-inline pull-right">
   	<span class="page-text">共 ${page.totalSize} 个记录，第 </span>
   	<select class="form-control input-sm" name="select-page" id="select-page">
		<c:forEach var="s"  begin="1" end="${page.totalPage}">
			<option value="${s}" 
			<c:if test="${s == page.currentPage}">selected = "selected"</c:if>
			>${s}</option>  
		</c:forEach>  
       </select>
       <span class="page-text"> / ${page.totalPage}页，每页 </span>
       <input class="form-control input-sm" type="text" value="${page.pageSize}" />
	<c:choose>
		<c:when test="${page.currentPage != 1}">
			<a class="page-enable" href="javascript:;" id="first-page">第一页</a>
		</c:when>
		<c:otherwise>
			<span class="page-unable">第一页</span>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.currentPage > 1}">
			<a class="page-enable" href="javascript:;" id="prev-page">上一页</a>
		</c:when>
		<c:otherwise>
			<span class="page-unable">上一页</span>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.currentPage < page.totalPage}">
			<a class="page-enable" href="javascript:;" id="next-page">下一页</a>
		</c:when>
		<c:otherwise>
			<span class="page-unable">下一页</span>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.currentPage != page.totalPage}">
			<a class="page-enable" href="javascript:;" id="last-page">最末页</a>
		</c:when>
		<c:otherwise>
			<span class="page-unable">最末页</span>
		</c:otherwise>
	</c:choose>
</div>

