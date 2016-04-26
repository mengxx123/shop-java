package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.controller.common.BaseAdminController;
import com.cjh.eshop.service.ISettingService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 管理平台相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class SettingController extends BaseAdminController {
	
	@Resource(name = "settingService")
	private ISettingService settingService;
	
	private static final String VIEW_ADMIN_SETTING = "admin/setting";
	
	@RequestMapping("admin/settings")
	public String setting(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("websiteName", settingService.getValue(Constant.SETTING_WEBSITE_NAME));
		return VIEW_ADMIN_SETTING;
	}
	
	@RequestMapping(value = "admin/settings", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request,
			@RequestParam(value = "website_name") String websiteName) {
		
		if (TextUtil.isEmpty(websiteName)) {
			return new JsonResult<String>(StateCode.ERROR, "网站名不能为空");
		}
		
		try {
			settingService.updateByKey(Constant.SETTING_WEBSITE_NAME, websiteName);

			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "保存失败");
		}
	}
	
}