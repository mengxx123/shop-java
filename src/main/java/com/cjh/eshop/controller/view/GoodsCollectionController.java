package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsCollection;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.impl.GoodsCollectionService;
import com.cjh.eshop.service.impl.GoodsService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

@Controller
public class GoodsCollectionController extends BaseController {
	
	@Resource(name="goodsCollectionService")
	private GoodsCollectionService goodsCollectionService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	private static final String VIEW_GOODS_COLLECTION = "collection/goods_collection";
	
	// 商品收藏页面
	@RequestMapping("goods_collection")
	public String goodsCollections(ModelMap modelMap,  HttpServletRequest request, 
			@RequestParam(value="page", defaultValue = "1") Integer page){
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		page = page < 1 ? 1 : page;
		String uid = (String) request.getSession().getAttribute(Constant.SESSION_UID);
		PageInfo<GoodsCollection> pageInfo = goodsCollectionService
				.getByUserId(uid, page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);
		return VIEW_GOODS_COLLECTION;
	}
	
	// 取消收藏
	@RequestMapping("cancel_goods_collection")
	public String cancelGoodsCollection(ModelMap modelMap,  HttpServletRequest request) {
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		goodsCollectionService.deleteById(request.getParameter("id"));
		return "redirect:/goods_collection";
	}
	
	// 添加商品收藏接口
	@RequestMapping(value = "api/v1/goods_collection", method=RequestMethod.POST)
	@ResponseBody
	public Object setGoodsImage(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "goods_id") String goodsId) {
		
		if (TextUtil.isEmpty(goodsId)) {
			return new JsonResult<String>(StateCode.ERROR, "参数 goods_id 不能为空");
		}
		
		// TODO 处理长时间未登录再点击收藏的情况
		
		String userId = getLoginUserId(request);
		
		GoodsCollection collect = goodsCollectionService.getByUserIdAndGoodsId(userId, goodsId);
		if (collect != null) {
		    return new JsonResult<String>(StateCode.ERROR, "不能重复收藏");
		}
		
		GoodsCollection collection = new GoodsCollection();
		collection.setGoods(new Goods(goodsId));
		collection.setUser(new User(userId));
		
		try {
			String id = goodsCollectionService.save(collection);
			return new JsonResult<String>(StateCode.SUCCESS, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}
	
	// 取消商品收藏接口
	@RequestMapping(value = "api/v1/goods_collection_cancel", method=RequestMethod.POST)
	@ResponseBody
	public Object cancelGoodsImage(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "collection_id") String collectionId) {
		
		if (TextUtil.isEmpty(collectionId)) {
			return new JsonResult<String>(StateCode.ERROR, "参数 collection_id 不能为空");
		}
		
		// TODO 权限验证，避免收藏数为负数
		
		try {
			goodsCollectionService.deleteById(collectionId);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}
}
