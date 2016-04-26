package com.cjh.eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cjh.eshop.common.Constant;
 
/**
 * @author 陈建杭
 * 
 */
public class UserInterceptor implements HandlerInterceptor {
 
    public final static String SEESION_MEMBER = "seesion_member";

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
    	
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {
 
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        // 请求的路径
        String contextPath = request.getContextPath();
        //String url = request.getServletPath().toString();
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute(Constant.SESSION_MANAGER_ID);
        //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
        if (StringUtils.isEmpty(user)) {
            //被拦截，重定向到login界面
           /* response.sendRedirect(contextPath + "/login?redict_url="
                    + URLEncoder.encode(url));*/
            response.sendRedirect(contextPath + "/loginin");
            return false;
        }
        return true;
    }
 
}
