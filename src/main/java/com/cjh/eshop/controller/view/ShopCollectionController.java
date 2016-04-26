package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.ShopCollection;
import com.cjh.eshop.service.impl.ShopCollectionService;

@Controller
public class ShopCollectionController extends BaseController {
	
	@Resource(name="shopCollectionService")
	private ShopCollectionService shopCollectionService;
	
	private static final String VIEW_SHOP_COLLECTION = "collection/shop_collection";
	
	@RequestMapping("shop_collection")
	public String shopCollections(ModelMap modelMap,  HttpServletRequest request, 
			@RequestParam(value = "page", defaultValue = "1") Integer page){
		commonInit(request, modelMap);
		if(!isLogin){
			return "redirect:/login";
		}

		String uid = (String)request.getSession().getAttribute(Constant.SESSION_UID);
		PageInfo<ShopCollection> pageInfo = shopCollectionService.getShopCollectionByUserId(uid, page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);
		return VIEW_SHOP_COLLECTION;
	}
	
	@RequestMapping("cancel_shop_collection")
	public String cancelShopCollection(ModelMap modelMap,  HttpServletRequest request){
		commonInit(request, modelMap);
		if(!isLogin){
			return "redirect:/login";
		}
		shopCollectionService.deleteById(request.getParameter("id"));
		return "redirect:/shop_collection";
	}
}
