package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.common.Config;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.service.IUserService;

/**
 * 页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class EshopController extends BaseController {
	
	@Resource(name = "userService")
	private IUserService userService;
	
	private static final String VIEW_LOGIN = "login";
	private static final String VIEW_REGISTER = "register";
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, ModelMap modelMap) {
	    
	    commonInit(request, modelMap);
	    
		return VIEW_LOGIN;  
	}
	
	@RequestMapping("register")
    public String register(HttpServletRequest request, ModelMap modelMap) {
        
	    commonInit(request, modelMap);
	    
	    modelMap.put("needEmail", Config.NEED_EMAIL_VALID);
        
        return VIEW_REGISTER; 
    }
}