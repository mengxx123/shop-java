package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Seller;
import com.cjh.eshop.service.ISellerService;

/**
 * 商家相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class SellerController extends BaseController {
	
	@Resource(name = "sellerService")
	private ISellerService sellerService;
	
	private static final String VIEW_ADMIN_SELLER = "seller/admin_seller";
	
	@RequestMapping("admin/sellers")
	public String manager(HttpSession session, HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value="keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		
		final int PAGE_SIZE = 20;
		
		PageInfo<Seller> pageInfo = sellerService.getAllByPage(page, PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return VIEW_ADMIN_SELLER;
	}
}