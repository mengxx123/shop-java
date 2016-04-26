package com.cjh.eshop.controller.view;

import java.util.Hashtable;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.controller.common.BaseAdminController;
import com.cjh.eshop.model.Manager;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IManagerService;
import com.cjh.eshop.service.ISettingService;
import com.cjh.eshop.service.IUserService;

/**
 * 管理平台相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class AdminController extends BaseAdminController {
	
	@Resource(name = "managerService")
	private IManagerService managerService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "settingService")
	private ISettingService settingService;
	
	private static final String URL_ADMIN = "admin";
	
	private static final String VIEW_ADMIN = "admin/index";
	private static final String VIEW_ADMIN_ABOUT = "admin/about";
	private static final String VIEW_ADMIN_HELP = "admin/help";
	private static final String VIEW_ADMIN_404 = "admin/404";
	private static final String VIEW_ADMIN_INDEX = "admin/main";
	
	@RequestMapping(URL_ADMIN)
	public String admin(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		
		String managerId = (String) session.getAttribute(Constant.SESSION_MANAGER_ID);
		Manager manager = managerService.getById(managerId);
		modelMap.put("manager", manager);
		
		User user = userService.getById(managerId);
		modelMap.put("user", user);
		
		return VIEW_ADMIN;
	}
	
	/** 管理平台404页面 */
	@RequestMapping("admin/*")
	public String admin404(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		return VIEW_ADMIN_404;
	}

	@RequestMapping("admin/about")
	public String about() {
		return VIEW_ADMIN_ABOUT;
	}

	@RequestMapping("admin/help")
	public String help() {
		return VIEW_ADMIN_HELP;
	}
	
	@RequestMapping("admin/main")
	public String index(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> setting = new Hashtable<String, Object>();
		setting.put("test", "呵呵哒");
		modelMap.put("settings", setting);
		
		String managerId = (String) session.getAttribute(Constant.SESSION_MANAGER_ID);
		Manager manager = managerService.getById(managerId);
		modelMap.put("manager", manager);
		
		return VIEW_ADMIN_INDEX;
	}
}