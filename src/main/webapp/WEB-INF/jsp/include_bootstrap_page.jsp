<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<ul class="pagination">
	<c:if test="${page.currentPage > 1}">
		<li>
			<a href="topics?page=${page.currentPage - 1}">
				<span>&laquo;</span>
		    </a>
		</li>
	</c:if>
	<c:forEach var="curPage" begin="${page.startPage}" end="${page.endPage}" step="1"> 
		<c:if test="${curPage == page.currentPage}">
			<li class="active"><a href="topics?page=${curPage}">${curPage}</a></li>
		</c:if>
		<c:if test="${curPage != page.currentPage}">
			<li><a href="topics?page=${curPage}">${curPage}</a></li>
		</c:if>
	</c:forEach> 
	
	<c:if test="${page.currentPage < page.totalPage}">     
        <li>
			<a href="topics?page=${page.currentPage + 1}">
		    	<span>&raquo;</span>
			</a>
		</li>
	</c:if>
</ul>
