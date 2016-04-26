package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.CookieUtil;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;


/**
 * 登陆相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
@RequestMapping("api/v1.0")
public class LoginApiController {

	private static Logger logger = LoggerFactory.getLogger(LoginApiController.class);
	
	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object registPost2(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		if (account == null || account.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "账号不能为空");
		}
		if (password == null || password.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "密码不能为空");
		}
		
		if (userService.login(account, password)) {
			User user = userService.getUserByUserName(account);
			session.setAttribute(Constant.SESSION_UID, user.getId());
			session.setAttribute(Constant.SESSION_USER_NAME, user.getName());
			logger.info("[用户{}]用户登陆成功", user.getId());
			
			CookieUtil.add(response, Constant.COOKIE_UID, user.getId());
			CookieUtil.add(response, Constant.COOKIE_PASSWORD, password);
			
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} else {
			return new JsonResult<String>(StateCode.ERROR, "账号不存在或密码错误");
		}
		
	}

	@RequestMapping("loginout")
	public Object loginOut(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = (String) session.getAttribute(Constant.SESSION_UID);
		logger.info("[用户{}]退出登陆", userId);
		
		session.setAttribute(Constant.SESSION_UID, null);
		
		//CookieUtil.add(response, Constant.COOKIE_PASSWORD, "");
		CookieUtil.clear(response, Constant.COOKIE_PASSWORD);
		
		return new JsonResult<String>(StateCode.SUCCESS, "");
	}
}
