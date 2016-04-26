package com.cjh.eshop.controller.view;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.Shop;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IArticleService;
import com.cjh.eshop.service.IGoodsCategoryService;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.service.IShopService;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

@Controller
public class ShopController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(ShopController.class);

	@Resource(name = "shopService")
	private IShopService shopService;

	/*
	 * @Resource(name = "shopCollectionService") private IShopCollectionService
	 * shopCollectionService;
	 */

	@Resource(name = "articleService")
	private IArticleService articleService;

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@Resource(name = "goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;

	@Resource(name = "userService")
	private IUserService userService;

	private static final String VIEW_SHOP_DETAIL = "shop/shop_detail";
	private static final String VIEW_ADMIN_SHOP = "shop/admin_shop";
	private static final String VIEW_ADMIN_SHOP_EDIT = "shop/shop_edit";
	private static final String VIEW_GOODS_RELEASED = "goods/released";
	
	// 已发布商品页面
	@RequestMapping("shops2/released")
	public String releasedGoodsPage(HttpServletRequest request, ModelMap modelMap, 
			@RequestParam(defaultValue = "1") int pageNo) {
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_UID); 
		Shop shop = shopService.getShopByUserId(userId);
		
		// 如果用户没有店铺，则创建一间店铺
		if (shop == null) {
			// TODO
			Shop newShop = new Shop();
			newShop.setName(userService.getById(userId).getNickname()+"的店");
			newShop.setUser(new User(userId));
			newShop.setDescription("");
			shopService.save(newShop);
		} else {
			PageInfo<Goods> pageInfo = goodsService.getGoodsesByShopId(shop.getId(), pageNo, DEFAULT_PAGE_SIZE);
			modelMap.put("page", pageInfo);
		}
		return VIEW_GOODS_RELEASED;
	}
		
	// 店铺详情页面
	@RequestMapping("shops/{id}")
	public String shop(@PathVariable("id") String shopId, HttpSession session,
			HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "page", defaultValue="1") Integer page) {
		
		commonInit(request, modelMap);

		final int PAGE_SIZE = 9; // 店铺详情页面每页显示商品数量

		// 店铺信息
		Shop shop = (Shop) shopService.getById(shopId);
		if (shop == null) {
			// TODO 跳转到店铺找不到页面
		}
		modelMap.put("shop", shop);
		
		// 商家
		User user = userService.getById(shop.getUser().getId());
		modelMap.put("seller", user);
		
		/*
		 * // 收藏 ShopCollection collection =
		 * shopCollectionService.getCollectionByUserIdAndShopId(userId, shopId);
		 * if (isLogin) { modelMap.put("isCollection", collection != null); }
		 */
		
		// 商品
		PageInfo<Goods> pageInfo = goodsService.getGoodsesByShopId(shopId, page, PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return VIEW_SHOP_DETAIL;
	}

	// 管理平台店铺列表
	@RequestMapping("admin/shops")
	public String manager(HttpSession session, HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		if (page == null) {
			page = 1;
		}

		PageInfo<Shop> pageInfo = shopService.getAllByPage(page,
				DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return VIEW_ADMIN_SHOP;
	}

	// 管理平台店铺添加/编辑页面
	@RequestMapping(value = "admin/shop_edit", method = RequestMethod.GET)
	public String brandEdit(HttpServletRequest request, ModelMap modelMap) {
		return VIEW_ADMIN_SHOP_EDIT;
	}

	// 管理平台店铺编辑页面
	@RequestMapping("admin/shops/{id}")
	public String getManagerById(@PathVariable("id") String id,
			HttpServletRequest request, ModelMap modelMap) {
		Shop shop = shopService.getById(id);
		modelMap.put("shop", shop);
		return VIEW_ADMIN_SHOP_EDIT;
	}

	// 管理平台店铺添加/编辑接口
	@RequestMapping(value = "shop", method = RequestMethod.POST)
	public String saveOrUpdateBrand(HttpServletRequest request, Shop shop,
			ModelMap modelMap) {
		if (TextUtil.isEmpty(shop.getName())) {
			modelMap.put("result", "店铺名称不能为空");
			return VIEW_ADMIN_SHOP_EDIT;
		}
		if (shop.getUser().getId() == null) {
			modelMap.put("result", "商家编号不能为空");
			return VIEW_ADMIN_SHOP_EDIT;
		}

		if (TextUtil.isEmpty(shop.getId())) {
			shop.setCreateTime(new Date());
			try {
				String id = shopService.save(shop);
				logger.info("[管理员{}]添加店铺{}", getManagerName(request), id);
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("result", "保存失败，可能商家编号不存在"); // TODO
				return VIEW_ADMIN_SHOP_EDIT;
			}

			modelMap.put("shop", shop);
			modelMap.put("result", "添加成功");
			return VIEW_ADMIN_SHOP_EDIT;
		} else {
			Shop oldShop = shopService.getById(shop.getId());
			oldShop.setName(shop.getName());
			oldShop.setDescription(shop.getDescription());
			oldShop.getUser().setId(shop.getUser().getId());

			try {
				shopService.update(oldShop);
			} catch (Exception e) {
				modelMap.put("result", "保存失败");
				return VIEW_ADMIN_SHOP_EDIT;
			}
			modelMap.put("shop", shop);
			modelMap.put("result", "保存成功");
			return VIEW_ADMIN_SHOP_EDIT;
		}
	}

	// 管理平台店铺删除接口
	@RequestMapping(value = "shop_delete", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(HttpServletRequest request,
			@RequestParam(value = "id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "参数id不能为空");
		}
		// TODO 安全验证
		try {
			shopService.deleteById(id);
			logger.info("[管理员{}]删除店铺{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}

	


}