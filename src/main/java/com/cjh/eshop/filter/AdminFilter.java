package com.cjh.eshop.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cjh.eshop.common.Constant;

/**
 * 拦截后台管理系统，进行权限控制
 * @author 陈建杭
 *
 */
public class AdminFilter implements Filter {

	/*
	@Resource(name = "managerService")
	private IManagerService managerService;
	*/
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		// 得到session     
		HttpSession session = request.getSession(true);    
		// 得到用户类型     
		//String usertype = (String)session.getAttribute("usertype"); 
		
		// 如果没有登陆，跳转到登陆页面
		if (session.getAttribute(Constant.SESSION_MANAGER_ID) == null) {
			System.out.println("没有登陆，拒绝");
			String redictUrl = request.getRequestURL().toString();//request.getRemoteAddr();
			System.out.println("请求地址" + redictUrl);
			//redict_url
			((HttpServletResponse)servletResponse).sendRedirect(request.getContextPath() 
					+ "/loginin?redict_url=" + URLDecoder.decode(redictUrl));  
			return;
		}
		/*
		boolean isOk = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			
			String account = CookieUtil.getValue(cookies, Constant.COOKIE_ACCOUNT);
			String password = CookieUtil.getValue(cookies, Constant.COOKIE_PASSWORD);
			if (account != null && password != null) {
				if (managerService.login(account, password)) {
					isOk = true;
				}
			}
			
		}
		
		if (!isOk) {
			((HttpServletResponse)servletResponse).sendRedirect("loginin");  
			return;
		}
		*/
		//request.getCookie(new Cookie(Constant.COOKIE_ACCOUNT, account));
		//response.addCookie(new Cookie(Constant.COOKIE_PASSWORD, password));
		
		//session.setAttribute(Constant.SESSION_MANAGER_ID, 1);
		// 继续调用其他的过滤器     
	
		// count++，可以在这里统计访问API接口的次数
		// 激活下一个Filter
		filterChain.doFilter(servletRequest, servletResponse);

		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void destroy() {

	}
}



