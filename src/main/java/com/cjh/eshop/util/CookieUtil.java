package com.cjh.eshop.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjh.eshop.common.Constant;

/**
 * cookie 工具类
 * 
 * @author 陈建杭
 * 
 */
public class CookieUtil {

    // TODO cookie加密保存
    public static String encode(String password) {
        return Md5Util.encode(Md5Util.encode(password) + Constant.APP_KEY);
    }
    
	public static String getValue(Cookie[] cookies, String key) {
		if (cookies == null) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (key.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}

	/**
	 * 添加一个cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void add(HttpServletResponse response, String name, String value,
			int maxAge) {
		Cookie cookie = new Cookie(name, value);
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/** 保存7天 */
	public static void add(HttpServletResponse response, String name, String value) {
		final int SEVEN_DAY = 7 * 24 * 60 * 60; 
		add(response, name, value, SEVEN_DAY);
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getValue(HttpServletRequest request, String name) {
		Cookie cookie = get(request, name);
		String cookieVal = (cookie == null) ? null : cookie.getValue();
		return cookieVal;
	}

	public static String getValue(HttpServletRequest request, String name, String defaultValue) {
		Cookie cookie = get(request, name);
		String cookieVal = (cookie == null) ? null : cookie.getValue();
		if (cookieVal == null || cookie.equals("")) {
			return defaultValue;
		}
		return cookieVal;
	}
	
	public static Cookie get(HttpServletRequest request, String key) {
		Map<String, Cookie> cookieMap = _readCookieMap(request);
		if (cookieMap.containsKey(key)) {
			return (Cookie) cookieMap.get(key);
		} else {
			return null;
		}
	}

	/**
	 * 清除cookie
	 * 
	 * @param req
	 * @param res
	 * @param name
	 */
	public static void remove(HttpServletRequest req, HttpServletResponse res,
			String name) {
		String cookieName = getValue(req, name);
		if (null != cookieName) {
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
	}

	/**
	 * 清除所有cookie
	 * 
	 * @param req
	 * @param res
	 */
	public static void clear(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		for (int i = 0, len = cookies.length; i < len; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
	}

	/**
	 * 情况某个cookie
	 * @param response
	 * @param key
	 */
	public static void clear(HttpServletResponse response, String key) {
		Cookie cookie = new Cookie(key, "");
		cookie.setMaxAge(0);
		cookie.setPath("/"); // 不要漏掉
		response.addCookie(cookie);
	}
	
	private static Map<String, Cookie> _readCookieMap(HttpServletRequest req) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	/**
	 // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..  
	  final String sSession = password + userName;  //密码是SHA1加密,长度为40个字符(160位)  
    oItem = new Cookie("SSO", CookieUtil.encode(sSession));  
	 */
}
