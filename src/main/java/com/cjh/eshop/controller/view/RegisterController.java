package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.common.Config;
import com.cjh.eshop.service.IUserService;

@Controller
public class RegisterController {
	
	@Resource(name = "userService")
	private IUserService userService;
	
	private static final String VIEW_REGISTER = "register";
	
	@RequestMapping("register")
	public String register(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("needEmail", Config.NEED_EMAIL_VALID);
		
		return VIEW_REGISTER; 
	}
	
	
	
	
}