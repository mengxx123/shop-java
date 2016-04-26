package com.cjh.eshop.controller.view;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.service.IArticleService;
import com.cjh.eshop.service.IGoodsCategoryService;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.service.IShopService;

/** 首页控制器 */
@Controller
public class IndexController extends BaseController {
	
    @Resource(name = "shopService")
    private IShopService shopService;
    
    @Resource(name = "goodsService")
    private IGoodsService goodsService;
    
    @Resource(name = "articleService")
    private IArticleService articleService;
    
    @Resource(name = "goodsCategoryService")
    private IGoodsCategoryService goodsCategoryService;
    
	private static final String VIEW_INDEX = "eshop/index";
	
	// 商城首页
    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, HttpSession session,
            ModelMap modelMap, @RequestParam(defaultValue = "1") int page) {

        commonInit(request, modelMap);

        final int GOODS_NUM = 9; // 首页显示的商品数量

        // 商品
        PageInfo<Goods> pageInfo = goodsService.getGoodses(page, GOODS_NUM);
        List<Goods> list = pageInfo.getResult();
        modelMap.put("page", pageInfo);
        modelMap.put("list", list);

        // 分类
        List<GoodsCategory> categories = goodsCategoryService.getAllByParentId("0"); // TODO 封装获取顶级分类
        modelMap.put("categories", categories);

        // 公告
        final String NOTICE_ID = "3"; // 公告所属分类编号
        final int NOTICE_NUM = 5; // 首页显示公告数量
        List<Article> articles = articleService.getAllByCategoryId(NOTICE_ID,
                1, NOTICE_NUM).getResult();
        modelMap.put("notices", articles);

        return VIEW_INDEX;
    }
}