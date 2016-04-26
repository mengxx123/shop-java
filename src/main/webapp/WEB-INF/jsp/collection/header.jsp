<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 头部开始 -->
<header class="header">
	<div class="header-top">
		<c:if test="${isLogin == false}" >
			<ul class="header-top-left">
        	
	        </ul>
	        <ul class="header-top-right">
	        	<li><a id="login" href="javascript:;">你好，请登录</a></li>
	            <li><a href="register">注册</a></li>
	            <li><a href="">我的订单</a></li>
	        </ul>
		</c:if>  
		
	        
        <c:if test="${isLogin == true}" >
        	<ul class="header-top-left">
	        	<li><a href="javascript:;">Hi，${userName}</a> 
	                <div class="sub-menu user-info">
	                	<div>
	                    	<img src="images/head_default.png" alt="用户头像" />
	                        <div><span class="vip"></span></div>
	                    </div>
	                    <div><a href="user_center">个人中心</a></div>
	                    <div>
	                    	<ul>
	                        	<li>积分：200</li>
	                            <li>消息：0</li>
	                        </ul>
	                    </div>
	                    <div><a id="loginout" href="javascript:;">退出</a></div>
	                </div>
	            
	            </li>
	        </ul>
	        <ul class="header-top-right">
	        	<li><a href="javascript:;">与我相关</a>
	            	<ul class="sub-menu">
	                	<li><a href="">我的订单</a></li>
	                    <li><a href="">我的积分</a></li>
	                    <li><a href="">我的收藏夹</a></li>
	                   	<li><a href="">我的评论</a></li> 
	                </ul>
	            
	            </li>
	        	<li><a href="javascript:;">收藏夹</a>
	            	<ul class="sub-menu">
	                	<li><a href="goods_collection">商品收藏</a></li>
	                    <li><a href="shop_collection">店铺收藏</a></li>
	                </ul>
	            
	            </li>
	            <li><i class="app-icon"></i><a href="javascript:;">掌上商城</a>
	            	<div class="sub-menu app">
	                	<img class="app-qrcode" src="images/qrcode.png" alt="二维码" />
	                    <div class="app-right">
	                    	<p class="title">掌上商城</p>
	                        <div><a class="apps" href="">手机购物更优惠</a></div>
	                        <div><a href="http://m.yhd.com/downloads/10600518746/TheStoreApp.apk" target="_blank" rel="nofollow" class="hd_iconfont"></a></div>
	                    </div>
	                </div>
	            </li>
	            <li><a href="javascript:;">客服服务</a>
	            	<ul class="sub-menu">
	                	<li><a href="">常见问题</a></li>
	                    <li><a href="">在线投诉</a></li>
	                </ul>
	            
	            </li>
	            <li><a href="javascript:;">关注我们</a>
	           	
	            
	            </li>
	        </ul>

		</c:if>  
		
    </div>
    <div class="header-main">
        <div class="logo"><a href="./"><img src="images/logo.png" alt="图标" /></a></div>
        <div class="search">
            <div class="search-box">
            	<form action="search">
            		<input class="search-input" type="text" name="keyword" placeholder="请输入关键词" />
                	<input class="search-submit" type="submit" value="搜索" />
				</form>
                <ul class="hot-keyword">
                    <li><a href="search?keyword=苹果">苹果</a></li>
                    <li><a href="search?keyword=西瓜">西瓜</a></li>
                </ul>
            </div>
        </div>
    </div>
    <ul class="nav-menu clearfix">
    	<li><a href="./">首页</a></li>
        <li><a href="">美食</a></li>
        <li><a href="">团购</a></li>
        <li><a href="">拍卖</a></li>
        <li><a href="">智能</a></li>
    </ul>
</header>
<!-- 头部结束 -->