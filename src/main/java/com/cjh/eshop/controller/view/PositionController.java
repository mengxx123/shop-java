package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.service.IShopService;

@Controller
public class PositionController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(PositionController.class);

	@Resource(name = "shopService")
	private IShopService shopService;

	private static final String VIEW_SHOP_DETAIL = "shop/shop_detail";
	
	@RequestMapping("position")
	public String releasedGoodsPage(HttpServletRequest request, ModelMap modelMap) {
		return "";
	}
		
	

}