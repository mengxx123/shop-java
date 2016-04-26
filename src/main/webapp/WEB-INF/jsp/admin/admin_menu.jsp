<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!-- 侧栏 -->
<aside class="sidebar">
    <nav class="sidebar-nav">
        <ul class="menu" id="menu">
            <li>
            	<a href="#"><i class="menu-icon fa fa-flask"></i><span class="menu-lable">文章管理</span><i class="fa arrow"></i></a>
                <ul>
                    <li><a href="admin/article"><span class="menu-lable">文章列表</span></a></li>
                    <li><a href="admin/article_edit"><span class="menu-lable">添加文章</span></a></li>
                    <li><a href="admin/article_category"><span class="menu-lable">文章类别</span></a></li>
                    <li><a href="admin/article_category_edit"><span class="menu-lable">添加文章类别</span></a></li>
                </ul>
            </li>
            <li>
            	<a href="#"><i class="menu-icon fa fa-flask"></i><span class="menu-lable">帖子管理</span><i class="fa arrow"></i></a>
                <ul>
                    <li><a href="admin/article"><span class="menu-lable">帖子列表</span></a></li>
                    <li><a href="admin/article_category"><span class="menu-lable">文章类别</span></a></li>
                    <li><a href="admin/article_category_edit"><span class="menu-lable">添加文章类别</span></a></li>
                </ul>
            </li>
         	<li>
            	<a href="#"><i class="menu-icon fa fa-flask"></i><span class="menu-lable">管理员管理</span><i class="fa arrow"></i></a>
                <ul>
                    <li><a href="admin/manager"><span class="menu-lable">管理员列表</span></a></li>
                    <li><a href="admin/manager_edit"><span class="menu-lable">添加管理员</span></a></li>
                </ul>
            </li>
            <li>
            	<a href="#"><i class="menu-icon fa fa-flask"></i><span class="menu-lable">页面</span><i class="fa arrow"></i></a>
                <ul>
                    <li><a href="404.html" target="frame"><span class="menu-lable">404页面</span></a></li>
                    <li><a href="500.html" target="frame"><span class="menu-lable">500页面</span></a></li>
                    <li><a href="#"><i class="menu-icon fa fa-home"></i><span class="menu-lable">主页2</span></a></li>
                    <li><a href="#"><i class="menu-icon fa fa-home"></i><span class="menu-lable">主页3</span></a></li>
                </ul>
            </li>
          
            <li> <a href="#" aria-expanded="false">Menu 2 <span class="glyphicon arrow"></span></a>
                <ul aria-expanded="false">
                    <li><a href="#">item 2.1</a></li>
                    <li><a href="#">item 2.2</a></li>
                    <li><a href="#">item 2.3</a></li>
                    <li><a href="#">item 2.4</a></li>
                </ul>
            </li>
            <li> <a href="#" aria-expanded="false">Menu 2 <span class="glyphicon arrow"></span></a>
                <ul aria-expanded="false">
                    <li><a href="#">item 2.1</a></li>
                    <li><a href="#">item 2.2</a></li>
                    <li><a href="#">item 2.3</a></li>
                    <li><a href="#">item 2.4</a></li>
                </ul>
            </li>
            <li> <a href="#" aria-expanded="false">Menu 2 <span class="glyphicon arrow"></span></a>
                <ul aria-expanded="false">
                    <li><a href="#">item 2.1</a></li>
                    <li><a href="#">item 2.2</a></li>
                    <li><a href="#">item 2.3</a></li>
                    <li><a href="#">item 2.4</a></li>
                </ul>
            </li>
            <li> <a href="#" aria-expanded="false">Menu 2 <span class="glyphicon arrow"></span></a>
                <ul aria-expanded="false">
                    <li><a href="#">item 2.1</a></li>
                    <li><a href="#">item 2.2</a></li>
                    <li><a href="#">item 2.3</a></li>
                    <li><a href="#">item 2.4</a></li>
                </ul>
            </li>
    
        </ul>
    </nav>
</aside>
<!-- /侧栏 -->
<div id="content">
    <nav class="navbar navbar-default" role="navigation">
    	<div class="navbar-header">
          <a class="navbar-brand" href="admin">管理平台</a>
       </div>
       <div>
          <ul class="nav navbar-nav">
             <li class="active"><a href="#">iOS</a></li>
             <li><a href="#">SVN</a></li>
             <li class="dropdown pull-right">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                   Java 
                   <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                   <li><a href="#">jmeter</a></li>
                   <li><a href="#">EJB</a></li>
                   <li><a href="#">Jasper Report</a></li>
                   <li class="divider"></li>
                   <li><a href="#">分离的链接</a></li>
                   <li class="divider"></li>
                   <li><a href="#">另一个分离的链接</a></li>
                </ul>
             </li>
          </ul>
       </div>
    </nav>