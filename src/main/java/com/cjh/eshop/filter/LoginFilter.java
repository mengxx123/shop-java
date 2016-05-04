package com.cjh.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.service.impl.UserService;
import com.cjh.eshop.util.CookieUtil;

/**
 * 拦截后台管理系统，进行权限控制
 * 
 * @author 陈建杭
 * 
 */
public class LoginFilter implements Filter {

    private static IUserService userService; // TODO
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 得到session
        HttpSession session = request.getSession(true);

        if (userService == null) {
            ServletContext servletContext = filterConfig.getServletContext();
            ApplicationContext ctx = WebApplicationContextUtils
                    .getWebApplicationContext(servletContext);
            userService = (UserService) ctx.getBean("userService");
        }

        // 如果没有登陆，跳转到登陆页面
        if (session.getAttribute(Constant.SESSION_UID) == null) {
            System.out.println("没有登陆，拒绝2");

            Cookie[] cookies = request.getCookies();
            String id = CookieUtil.getValue(cookies, Constant.COOKIE_UID);
            String password = CookieUtil.getValue(cookies,
                    Constant.COOKIE_PASSWORD);

            if (id != null && password != null) {
                System.out.println("不为空");
                System.out.println(id);
                String account = userService.getById(id).getName(); // TODO 这里有时会空指针异常
                User user = userService.getUserByUserName(account);
                if (userService.login(account, password)) {
                    System.out.println("OK");
                    session.setAttribute(Constant.SESSION_UID, user.getId());
                } else {
                    System.out.println("cookie错误");
                }
            }

            // ((HttpServletResponse)servletResponse).sendRedirect("loginin");
            // return;
        }

        //response.sendRedirect("login?backurl=" + backUrl);
        // return;
        /*
         * boolean isOk = false; Cookie[] cookies = request.getCookies(); if
         * (cookies != null) {
         * 
         * 
         * if (account != null && password != null) { if
         * (managerService.login(account, password)) { isOk = true; } }
         * 
         * }
         * 
         * if (!isOk) {
         * ((HttpServletResponse)servletResponse).sendRedirect("loginin");
         * return; }
         */
        // request.getCookie(new Cookie(Constant.COOKIE_ACCOUNT, account));
        // response.addCookie(new Cookie(Constant.COOKIE_PASSWORD, password));

        // session.setAttribute(Constant.SESSION_MANAGER_ID, 1);
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
