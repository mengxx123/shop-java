package com.cjh.eshop.controller.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.model.GoodsCollection;
import com.cjh.eshop.model.GoodsComment;
import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.model.Shop;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IArticleService;
import com.cjh.eshop.service.IGoodsCategoryService;
import com.cjh.eshop.service.IGoodsCollectionService;
import com.cjh.eshop.service.IGoodsCommentService;
import com.cjh.eshop.service.IGoodsImageService;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.service.IShopService;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 商品相关页面控制器
 * 
 * @author 陈建杭
 * 
 */
@Controller
public class GoodsController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(GoodsController.class);

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@Resource(name = "goodsCommentService")
	private IGoodsCommentService goodsCommentService;

	@Resource(name = "goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;
	
	@Resource(name = "goodsCollectionService")
	private IGoodsCollectionService goodsCollectionService;

	@Resource(name = "userService")
	private IUserService userService;

	@Resource(name = "shopService")
	private IShopService shopService;

	@Resource(name = "goodsImageService")
	private IGoodsImageService goodsImageService;

	@Resource(name = "goodsCategoryService")
	private IGoodsCategoryService categoryService;

	@Resource(name = "articleService")
	private IArticleService articleService;

	private static final String VIEW_ADMIN_GOODS = "goods/admin_goods";
	private static final String VIEW_ADMIN_GOODS_EDIT = "goods/goods_edit";
	private static final String VIEW_GOODS_DETAIL = "goods/goods_detail";
	private static final String VIEW_GOODS_SEARCH = "goods/search";
	private static final String VIEW_GOODS_EDIT = "goods/releasing";
	private static final String VIEW_GOODS_RELEASED = "goods/released";

	// 商品详情页面
	@RequestMapping("goodses/{id}")
	public String goodsDetail(@PathVariable("id") String goodsId,
			HttpServletRequest request, ModelMap modelMap) {
		commonInit(request, modelMap);

		// 获取商品信息
		Goods goods = (Goods) goodsService.getById(goodsId);
		if (goods == null) {
			System.out.println("查询不到商品");
			modelMap.put("smg", "找不到");
			return "error.jsp";
		}
		goods.setShop(shopService.getById(goods.getShop().getId()));
		modelMap.put("goods", goods);
		goodsService.increaseClickCount(goodsId);

		String userId = getLoginUserId(request);
		
		// 获取店铺信息
		Shop shop = shopService.getById(goods.getShop().getId());
		modelMap.put("shop", shop);
		
		// 获取店家信息
		User user = userService.getById(shop.getUser().getId());
		modelMap.put("seller", user);
		
		// 收取收藏信息
		GoodsCollection collection = goodsCollectionService.getByUserIdAndGoodsId(userId, goodsId);
		if (isLogin) {
			if (collection != null) {
				modelMap.put("collectionId", collection.getId());
			}
			modelMap.put("isCollection", collection != null);
		}

		// 获取商品评论
		List<GoodsComment> comments = goodsCommentService.getGoodsCommentsByGoodsId(goodsId, 1, 20);
		modelMap.put("comments", comments);

		// 登录用户是否店家（店家不能评论自己的商品）
		boolean isSelf = isLogin && user.getId().equals(loginUserId);
		modelMap.put("isSelf", isSelf);
		
		// 获取商品图片
		List<GoodsImage> goodsImages = goodsImageService
				.getGoodsImagesByGoodsId(goodsId);
		modelMap.put("goodsImages", goodsImages);
		System.out.println("大小" + goodsImages.size());
		if (goodsImages.size() == 0) {
			GoodsImage img = new GoodsImage();
			img.setUrl("goods1.jpg"); // TODO 改成默认图片
			goodsImages.add(img);
		}

		return VIEW_GOODS_DETAIL;
	}

	@RequestMapping("good_comment")
	@ResponseBody
	public Object goodComment(HttpServletRequest request, ModelMap modelMap) {
		commonInit(request, modelMap);
		String goodsId = request.getParameter("goodsId");

		// try {
		// goodsId = Integer.parseInt(request.getParameter("goodsId"));
		// } catch (Exception e) {
		// System.out.println("查询不到商品");
		// modelMap.put("smg", "找不到");
		// return new JsonResult<String>(StateCode.ERROR, "商品ID为空");
		// }
		// 获取商品评论
		List<GoodsComment> comments = goodsCommentService
				.getGoodCommentsByGoodsId(goodsId, 1, 20);
		return comments;
	}

	@RequestMapping("nomal_comment")
	@ResponseBody
	public Object nomalComment(HttpServletRequest request, ModelMap modelMap) {
		commonInit(request, modelMap);
		String goodsId = request.getParameter("goodsId");

		// try {
		// goodsId = Integer.parseInt(request.getParameter("goodsId"));
		// } catch (Exception e) {
		// System.out.println("查询不到商品");
		// modelMap.put("smg", "找不到");
		// return new JsonResult<String>(StateCode.ERROR, "商品ID为空");
		// }

		// 获取商品评论
		List<GoodsComment> comments = goodsCommentService
				.getNomalCommentsByGoodsId(goodsId, 1, 20);
		return comments;
	}

	@RequestMapping("bad_comment")
	@ResponseBody
	public Object badComment(HttpServletRequest request, ModelMap modelMap) {
		commonInit(request, modelMap);
		String goodsId = request.getParameter("goodsId");

		// try {
		// goodsId = Integer.parseInt(request.getParameter("goodsId"));
		// } catch (Exception e) {
		// System.out.println("查询不到商品");
		// modelMap.put("smg", "找不到");
		// return new JsonResult<String>(StateCode.ERROR, "商品ID为空");
		// }

		// 获取商品评论
		List<GoodsComment> comments = goodsCommentService
				.getBadCommentsByGoodsId(goodsId, 1, 20);
		return comments;
	}

	// 管理平台商品列表
	@RequestMapping("admin/goodses")
	public String goods(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		String shopId = "1"; // TODO 暂时写死
		if (page == null) {
			page = 1;
		}

		PageInfo<Goods> pageInfo = goodsService.getGoodsesByShopId(shopId,
				page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return VIEW_ADMIN_GOODS;
	}

	// 管理平台商品添加/编辑页面
	@RequestMapping("admin/goodses/{id}")
	public String adminGoods(@PathVariable("id") String goodsId,
			HttpServletRequest request, ModelMap modelMap) {

		// 获取商品信息
		Goods goods = (Goods) goodsService.getById(goodsId);
		if (goods == null) {
			System.out.println("查询不到商品");
			modelMap.put("smg", "找不到");
			return "error.jsp"; // TODO
		}
		modelMap.put("goods", goods);
		
		// 获取商品分类信息
		List<GoodsCategory> guide = new ArrayList<GoodsCategory>();
		List<GoodsCategory> guide2 = new ArrayList<GoodsCategory>();
		GoodsCategory category = categoryService.getById(goods.getCategory()
				.getId());
		guide.add(category);
		while (!category.getParent().getId().equals("0")) {
			GoodsCategory c = categoryService.getById(category.getParent()
					.getId());
			guide.add(c);
			category = c;
		}

		for (int i = guide.size() - 1; i >= 0; i--) {
			guide2.add(guide.get(i));
		}
		modelMap.put("guide", guide2);

		// 商品浏览次数加1 TODO 防止重复计数
		goodsService.increaseClickCount(goodsId);

		// 获取商品图片
		List<GoodsImage> goodsImages = goodsImageService
				.getGoodsImagesByGoodsId(goodsId);
		System.out.println("图片数量" + goodsImages.size());
		modelMap.put("images", goodsImages);

		List<GoodsCategory> categories = categoryService.getAllByPage(1, 100)
				.getResult();
		modelMap.put("categories", categories);

		return VIEW_ADMIN_GOODS_EDIT;
	}

	@RequestMapping("admin/goods_edit")
	public String adminGoods(HttpServletRequest request, ModelMap modelMap,
			Goods goods,
			@RequestParam(value = "what", required = false) String what) {

		List<GoodsCategory> categories = categoryService.getAllByPage(1, 100)
				.getResult();
		modelMap.put("categories", categories);
		return VIEW_ADMIN_GOODS_EDIT;
	}

	// 管理平台添加商品接口
	@RequestMapping(value = "goods", method = RequestMethod.POST)
	public String saveOrUpdateGoods(HttpServletRequest request,
			ModelMap modelMap, Goods goods,
			@RequestParam(value = "what", required = false) String what) {
		if (what == null) {
			return VIEW_ADMIN_GOODS_EDIT;
		}

		if (what.equals("1")) {
			if (TextUtil.isEmpty(goods.getId())) {
				// 保存商品
				goods.setCategory(new GoodsCategory("1")); // TODO 这里写死
				goods.setBrand(null);
				goods.setClickCount(0);
				goods.setDescription("");
				goods.setModifyTime(new Date());
				goods.setShop(new Shop("1")); // TODO 这里写死
				goodsService.save(goods);

				modelMap.put("goods", goods);
				modelMap.put("result", "添加成功");

				List<GoodsCategory> categories = categoryService.getAllByPage(
						1, 100).getResult();
				modelMap.put("categories", categories);

				return VIEW_ADMIN_GOODS_EDIT;
			} else {
				Goods oldGoods = goodsService.getById(goods.getId());
				oldGoods.setName(goods.getName());
				oldGoods.setCategory(goods.getCategory());
				oldGoods.setNumber(goods.getNumber());
				oldGoods.setPrice(goods.getPrice());
				oldGoods.setModifyTime(new Date());
				System.out.println("更新");
				goodsService.update(oldGoods);

				modelMap.put("goods", oldGoods);
				modelMap.put("result", "修改成功");

				List<GoodsCategory> categories = categoryService.getAllByPage(
						1, 100).getResult();
				modelMap.put("categories", categories);

				return VIEW_ADMIN_GOODS_EDIT;
			}
		}
		modelMap.put("goods", goods);

		List<GoodsCategory> categories = categoryService.getAllByPage(1, 100)
				.getResult();
		modelMap.put("categories", categories);

		return VIEW_ADMIN_GOODS_EDIT;

	}

	// 用户删除自己发布的商品接口 TODO 重构
	@RequestMapping("goods_delete2")
	@ResponseBody
	public Object delete2(HttpServletRequest request,
			@RequestParam(value = "id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "参数id不能为空");
		}
		// TODO 安全验证
		try {
			goodsService.deleteById(id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}

	// 管理平台删除商品接口
	@RequestMapping("goods_delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,
			@RequestParam(value = "id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "参数id不能为空");
		}
		// TODO 安全验证
		try {
			goodsService.deleteById(id);
			logger.info("[管理员{}]删除商品{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}

	private String dealFile(HttpServletRequest request, ModelMap modelMap) {

		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 先判断request中是否包涵multipart类型的数据，
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获得第1张图片（根据前台的name名称得到上传的文件）
				// MultipartFile imgFile1 = multipartRequest.getFile("imgFile");
				Iterator<String> iter = multipartRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multipartRequest.getFile((String) iter
							.next());
					if (file != null) {

						String fileName = file.getOriginalFilename(); // 上传文件的文件名
						System.out.println("文件名：" + fileName);

						// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
						String ext = fileName.substring(
								fileName.lastIndexOf(".") + 1,
								fileName.length());
						// 对扩展名进行小写转换
						ext = ext.toLowerCase();
						System.out.println("格式是：" + ext);

						// 定义一个数组，用于保存可上传的文件类型
						List<String> fileTypes = new ArrayList<String>();
						fileTypes.add("jpg");
						fileTypes.add("png");
						fileTypes.add("jpeg");
						fileTypes.add("gif");
						if (!fileTypes.contains(ext)) {
							modelMap.put("result",
									"图片格式有误（仅支持jpg/jpeg、png和gif格式）");
							return null;
						}

						fileName = new Date().getTime() + "." + ext;
						// fileName = "123.txt";
						String path = request.getSession().getServletContext()
								.getRealPath(Constant.PATH_GOODS_IMAGE);// TODO
						System.out.println("path：" + path);
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}

						// 写文件到本地
						file.transferTo(targetFile);

						return fileName; // 这样的话只能传一个文件了
					}
				}
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 商品搜索
	@RequestMapping("goodses/search")
	public String getGoodsesByGoodsName(
			HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "category_id", defaultValue = "0") String categoryId,
			@RequestParam(value = "page", defaultValue = "1") int pageNo, ModelMap modelMap) {
		
		commonInit(request, modelMap);
		
		Goods exampleGoods = new Goods();
		if (!TextUtil.isEmpty(keyword)) {
			System.out.println("关键词");
			exampleGoods.setName(keyword);
		} 
		else if (!TextUtil.isEmpty(categoryId) && !categoryId.equals("0")) { // TODO
			System.out.println("不为空");
			exampleGoods.setCategory(new GoodsCategory(categoryId));
		}
		PageInfo<Goods> pageInfo = goodsService.getByExample(exampleGoods, pageNo, DEFAULT_PAGE_SIZE);
		
		modelMap.put("keyword", keyword);
		
		// 分类
		List<GoodsCategory> categories = goodsCategoryService.getAllByParentId("0"); // TODO 封装获取顶级分类
		modelMap.put("categories", categories);
		modelMap.put("categoryId", categoryId);
		System.out.println("商品分类"+categoryId);
		initPage(pageNo, modelMap, pageInfo);
		return VIEW_GOODS_SEARCH;
	}

	// 已发布商品页面
	@RequestMapping("goodses/released")
	public String releasedGoodsPage(HttpServletRequest request, ModelMap modelMap, 
			@RequestParam(value = "page", defaultValue = "1") int pageNo) {
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_UID); 
		
		// 店铺信息
		Shop shop = shopService.getShopByUserId(userId);
		modelMap.put("shop", shop);
		
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

	// 用户发布/编辑商品
	@RequestMapping("goodses/{id}/edit")
	public String releaseGoodsPage(HttpServletRequest request, ModelMap modelMap,
			@PathVariable("id") String goodsId) {
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		
		Goods goods = goodsService.getById(goodsId);
		modelMap.put("goods", goods);
		
		// 商品图片
		List<GoodsImage> images = goodsImageService.getGoodsImagesByGoodsId(goodsId);
		modelMap.put("images", images);

		// 商品分类
		List<GoodsCategory> categories = categoryService.getAllByParentId("0"); // TODO 顶级分类
		modelMap.put("categories", categories);
		
		return VIEW_GOODS_EDIT;
	}
	
	// 用户发布商品页面
	@RequestMapping("goods/release")
	public String releaseGoodsPage(HttpServletRequest request, ModelMap modelMap) {
		commonInit(request, modelMap);
		if (!isLogin) {
			return "redirect:/login";
		}
		
		// 商品分类
		List<GoodsCategory> categories = categoryService.getAllByParentId("0"); // TODO 顶级分类
		modelMap.put("categories", categories);
				
		return VIEW_GOODS_EDIT;
	}
	
	// 商家发布/编辑商品接口
	@RequestMapping(value="goods/releasing", method=RequestMethod.POST)
	@ResponseBody
	private Object releasingGoods2(HttpServletRequest request, ModelMap modelMap, 
			@ModelAttribute Goods goods,
			@RequestParam("catId") String catId,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		
		commonInit(request, modelMap);
		
		if (file != null) {
		    System.out.println("图片大小" + file.getSize());
		    //return new JsonResult<String>(StateCode.ERROR, "图片不能为空");
		}
		
		if (file != null && file.getSize() > 10000000) {
		    return new JsonResult<String>(StateCode.ERROR, "图片大小不能超过10M");
		}
		
		if (TextUtil.isEmpty(goods.getName())) {
		    return new JsonResult<String>(StateCode.ERROR, "参数 name 不能为空");
		} 
		if (goods.getName().length() > 32) {
            return new JsonResult<String>(StateCode.ERROR, "商品名称不能大于 32 个字符");
        }
		if (goods.getPrice() == null) {
		    return new JsonResult<String>(StateCode.ERROR, "参数 price 不能为空");
		}
		if (!isLogin) {
			return new JsonResult<String>(StateCode.ERROR, "未登录，没有权限");
		}
		if (TextUtil.isEmpty(goods.getDescription())) {
		    return new JsonResult<String>(StateCode.ERROR, "商品描述不能为空");
		}
		if (goods.getDescription().length() > 500) {
		    return new JsonResult<String>(StateCode.ERROR, "商品描述不能大于 500 个字符");
		}
		
		System.out.println("商品ID" + goods.getId());
		if (TextUtil.isEmpty(goods.getId())) {
		    
			// 获取用户的店铺
			String uid = (String) request.getSession().getAttribute(Constant.SESSION_UID); 
			Shop shop = shopService.getShopByUserId(uid);
			
			goods.setCategory(new GoodsCategory(catId));
			goods.setNumber(1);
			goods.setIsOnSale(1);
			goods.setDescription(goods.getDescription());
			goods.setShop(shop);
			
			String path = saveFile(request, file);
	        goods.setImage(path);
	        
			String goodsId = goodsService.save(goods);
			
			// TODO 应该与上面的操作同个事务
			GoodsImage goodsImage = new GoodsImage();
	        goodsImage.setGoodsId(goodsId);
	        goodsImage.setUrl(path);
	        goodsImageService.save(goodsImage);
	        
	        return new JsonResult<String>(StateCode.SUCCESS, goodsId);
		} else {
			// 修改商品信息
			Goods oldGoods = goodsService.getById(goods.getId());
			oldGoods.setName(goods.getName());
			oldGoods.setPrice(goods.getPrice());
			oldGoods.setCategory(new GoodsCategory(catId));
			oldGoods.setDescription(goods.getDescription());
			
			try {
				goodsService.update(oldGoods);
				return new JsonResult<String>(StateCode.SUCCESS, oldGoods.getId());
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult<String>(StateCode.ERROR, "系统出错");
			}
			
		}
	}
	
	private void initPage(int pageNo, ModelMap modelMap,
			PageInfo<Goods> pageInfo) {
		List<Goods> list = pageInfo.getResult();
		modelMap.put("list", list);

		modelMap.put("page", pageInfo);
		// 页数
		long totalPage = pageInfo.getTotalPage();
		modelMap.put("totalPage", totalPage);
		modelMap.put("currentPage", pageNo);
		long startPage = pageNo - 3;
		if (startPage < 1) {
			startPage = 1;
		}
		long endPage = pageNo + 3;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);

		// 公告
		final String NOTICE_ID = "3"; // 广告所属分类编号
		final int NOTICE_NUM = 5; // 首页显示公告数量

		List<Article> articles = articleService.getAllByCategoryId(NOTICE_ID,
				1, NOTICE_NUM).getResult(); // 公告的分类ID是3，首页只显示5条公告
		modelMap.put("articles", articles);
	}
	
	@RequestMapping(value = "admin/goods_image", method = RequestMethod.POST)
	public String saveGoodsImage(
			HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "myfile", required = false) CommonsMultipartFile[] files,
			Goods goods) {
		if (goods.getId() == null) {
			modelMap.put("result", "保存失败，商品ID为空"); // TODO
			return VIEW_ADMIN_GOODS_EDIT;
		} else {
			String result = dealFile(request, modelMap);
			if (result != null) {
				GoodsImage image = new GoodsImage();
				image.setGoodsId(goods.getId());
				image.setUrl(result);
				goodsImageService.save(image);

				Goods gs = goodsService.getById(goods.getId());
				if (gs.getImage() == null) {
					gs.setImage(result);
					goodsService.update(gs);
				}

				System.out.println("上传图标");
				modelMap.put("result", "添加图片成功");
				return "redirect:goods/" + goods.getId();
			} else {
				System.out.println("空空空");
			}
			return VIEW_ADMIN_GOODS_EDIT;
		}

	}
}
