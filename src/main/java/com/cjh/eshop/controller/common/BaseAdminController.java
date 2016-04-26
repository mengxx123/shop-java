package com.cjh.eshop.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cjh.eshop.common.Constant;

public class BaseAdminController {
	
	public void putSession(HttpServletRequest request, HttpSession session, String name, Object value) {
		session.putValue(Constant.SESSION_MANAGER_ID, value);
		
		request.getSession();
	}
}
