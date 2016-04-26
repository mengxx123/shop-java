package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.service.IUserService;

/**
 * 会员登陆相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class LoginController {
	
	@Resource(name = "userService")
	private IUserService userService;
	
	private static final String VIEW_LOGIN = "login";
	
	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		return VIEW_LOGIN;  
	}
}