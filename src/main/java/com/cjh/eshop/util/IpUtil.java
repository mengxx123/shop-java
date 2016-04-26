package com.cjh.eshop.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	
	public static String getIpAddr(HttpServletRequest request) { 
		
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;
	    /*
		String realIP = request.getHeader("x-forwarded-for");
        if (realIP != null && realIP.length() != 0) {
            return realIP;
        } else {
            String ip = request.getRemoteAddr();
            return ip;
        }
        */

	}
}
