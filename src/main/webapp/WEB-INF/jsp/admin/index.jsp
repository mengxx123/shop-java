<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="eadmin,后台主题,后台框架,响应式">
<meta name="description" content="eAdmin 是一个基于 html5 和 css3 的响应式后台UI框架，基于 Bootstrap3 最新版本开发的扁平化主题">
<title>e校园管理平台</title>

<jsp:include page="../admin/include_head.jsp"></jsp:include>
</head>

<body>
<!-- 侧栏 -->
<aside class="esidebar" id="sidebar">
    <ul class="eui-sidemenu" id="menu">
        <li class="emenu-header">
            <div>
                <img class="emenu-manager-img" src="${user.headPath}">
            </div>
        </li>
        <li>
            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">商城系统管理</span><i class="fa arrow"></i></a>
            <ul>
                <li>
		            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">商品品牌管理</span><i class="fa arrow"></i></a>
		            <ul>
		                <li><a href="/admin/brands" target="frame"><span class="menu-lable">品牌列表</span></a></li>
		                <li><a href="/admin/brand_edit" target="frame"><span class="menu-lable">添加品牌</span></a></li>
		            </ul>
		        </li>
                <li>
		            <a href="#"><i class="menu-icon fa fa-user"></i><span class="menu-lable">商家管理</span><i class="fa arrow"></i></a>
		            <ul>
		                <li><a href="/admin/sellers" target="frame"><span class="menu-lable">商家列表</span></a></li>
		                <li><a href="/admin/seller_edit" target="frame"><span class="menu-lable">添加商家</span></a></li>
		            </ul>
		        </li>
		        <li>
		            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">商品管理</span><i class="fa arrow"></i></a>
		            <ul>
		                <li><a href="/admin/goodses" target="frame"><span class="menu-lable">商品列表</span></a></li>
		                <li><a href="/admin/goods_edit" target="frame"><span class="menu-lable">添加商品</span></a></li>
		            </ul>
		        </li>
		        <li>
		            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">商品分类管理</span><i class="fa arrow"></i></a>
		            <ul>
		                <li><a href="/admin/category" target="frame"><span class="menu-lable">分类列表</span></a></li>
		                <li><a href="/admin/category_edit" target="frame"><span class="menu-lable">添加分类</span></a></li>
		            </ul>
		        </li>
		        <li>
		            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">店铺管理</span><i class="fa arrow"></i></a>
		            <ul>
		                <li><a href="/admin/shops" target="frame"><span class="menu-lable">店铺列表</span></a></li>
		                <li><a href="/admin/shop_edit" target="frame"><span class="menu-lable">添加店铺</span></a></li>
		            </ul>
		        </li>
            </ul>
        </li>
        
        <li>
            <a href="#"><i class="menu-icon fa fa-user"></i><span class="menu-lable">用户管理</span><i class="fa arrow"></i></a>
            <ul>
                <li><a href="/admin/users" target="frame"><span class="menu-lable">用户列表</span></a></li>
                <li><a href="/admin/users_edit" target="frame"><span class="menu-lable">添加用户</span></a></li>
            </ul>
        </li>
        <li>
            <a href="#" aria-expanded="true"><i class="menu-icon fa fa-envelope"></i><span class="menu-lable">管理员管理</span><span class="label label-info pull-right">10</span></a>
            <ul>
                <li><a href="/admin/managers" target="frame"><span class="menu-lable">管理员列表</span></a></li>
                <li><a href="/admin/manager_edit" target="frame"><span class="menu-lable">添加管理员</span></a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">文章管理</span><i class="fa arrow"></i></a>
            <ul>
                <li><a href="/admin/articles" target="frame"><span class="menu-lable">文章列表</span></a></li>
                <li><a href="/admin/article_edit" target="frame"><span class="menu-lable">添加文章</span></a></li>
                <li><a href="/admin/article_categorys" target="frame"><span class="menu-lable">文章分类列表</span></a></li>
                <li><a href="/admin/article_category_edit" target="frame"><span class="menu-lable">添加文章分类</span></a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">反馈管理</span><i class="fa arrow"></i></a>
            <ul>
                <li><a href="/admin/feedbacks" target="frame"><span class="menu-lable">反馈列表</span></a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="menu-icon fa fa-bar-chart"></i><span class="menu-lable">网站管理</span><i class="fa arrow"></i></a>
            <ul>
                <li><a href="/admin/settings" target="frame"><span class="menu-lable">网站配置</span></a></li>
            </ul>
        </li>
    </ul>
</aside>
<!-- /侧栏 -->
<!-- 主体内容 -->
<div class="econtent">
    <nav class="econtent-header navbar navbar-light" role="navigation">
    	<div class="navbar-header">
          <a id="menu-minimalize" class="navbar-brand" href="./">eSchool</a>
       </div>
       <div>
          <ul class="nav navbar-nav">
             <li class="nav-item"><a class="nav-link" href="/admin/main" target="frame">首页</a></li>
             <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                   	更多 
                   <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="./" target="_blank">网站首页</a></li>
                	<li><a href="/admin/help" target="frame">帮助</a></li>
                </ul>
             </li>
             <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                	${manager.name}
                	<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                   <li><a href="#">个人信息</a></li>
                   <li class="divider"></li>
                   <li><a href="adloginout">退出</a></li>
                </ul>
             </li>
          </ul>
       </div>
    </nav>
	<iframe id="right-frame" class="econtent-body main-frame" name="frame" src="admin/main"></iframe>
    <footer class="efooter">
        <p class="efooter-copyright">Theme by <a href="#">eAdmin</a></p>
    </footer>
</div>
<!-- /主体内容 -->
<jsp:include flush="true" page="admin_footer.jsp"/>
<script src="/asset/lib/metisMenu/metisMenu.min.js"></script> 
<script src="/asset/lib/slimscroll/jquery.slimscroll.min.js"></script> 
<script src="/asset/js/eadmin.js"></script>
</body>
</html>