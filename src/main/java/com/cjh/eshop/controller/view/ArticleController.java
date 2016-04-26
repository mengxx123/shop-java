package com.cjh.eshop.controller.view;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.model.ArticleCategory;
import com.cjh.eshop.service.IArticleCategoryService;
import com.cjh.eshop.service.IArticleService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 文章相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class ArticleController extends BaseController {
	
	@Resource(name = "articleService")
	private IArticleService articleService;
	
	@Resource(name = "articleCategoryService")
	private IArticleCategoryService articleCategoryService;
	
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	private static final String VIEW_ADMIN_ARTICLE = "article/admin_article";
	private static final String VIEW_ADMIN_ARTICLE_EDIT = "article/article_edit";
	private static final String VIEW_ARTICLE = "article/article_detail";
	
	@RequestMapping("articles/{id}")
    public String upload2(@PathVariable("id") String articleId, HttpServletRequest request, 
    		ModelMap modelMap) {
		commonInit(request, modelMap);

		List<Article> articles = articleService.getAll();
//		if (articles == null) {
//			System.out.println("查询不到商品");
//			model.put("smg", "找不到");
//			return "error.jsp"; 
//		}
		modelMap.put("articles", articles);
		
		Article article = (Article) articleService.getById(articleId);
		modelMap.put("article", article);
		System.out.println();
		modelMap.put("smg", articles.size() + "");
		
		return VIEW_ARTICLE;
	}

	@RequestMapping("admin/articles")
    public String adminArticle(HttpServletRequest request, 
    		ModelMap modelMap,
    		@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value="keyword", required = false) String keyword) {
		
		modelMap.put("keyword", keyword);
		
		PageInfo<Article> pageInfo = articleService.getAllByKeyword(keyword, page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);
		return VIEW_ADMIN_ARTICLE;
	}
	
	@RequestMapping("admin/articles/{id}")
    public String upload22(@PathVariable("id") String articleId, HttpServletRequest request, 
    		ModelMap modelMap,
    		@RequestParam(value="page", defaultValue="1") Integer page) {
		commonInit(request, modelMap);

		Article article = (Article) articleService.getById(articleId);
		modelMap.put("article", article);
		List<ArticleCategory> categories = articleCategoryService.getAll();
		modelMap.put("categories", categories);
		return VIEW_ADMIN_ARTICLE_EDIT;
	}
	
	@RequestMapping(value = "admin/article_edit", method = RequestMethod.GET)
	public String adminArticle22222(HttpServletRequest request, ModelMap modelMap) {
		List<ArticleCategory> categories = articleCategoryService.getAll();
		modelMap.put("categories", categories);
		return VIEW_ADMIN_ARTICLE_EDIT;
	}
	
	@RequestMapping(value = "admin/articles", method = RequestMethod.POST)
    public String adminArticle2222(HttpServletRequest request, 
    		ModelMap modelMap, Article article,
    		@RequestParam(value="category_id", required = false) String categoryId) {
		List<ArticleCategory> categories = articleCategoryService.getAll();
		modelMap.put("categories", categories);

		if (TextUtil.isEmpty(article.getId())) {
			article.setCategory(new ArticleCategory(categoryId));
			article.setAddTime(new Date());
			
			try {
				articleService.update(article);
				modelMap.put("article", article);
				modelMap.put("result", "添加成功！");
				return VIEW_ADMIN_ARTICLE_EDIT;
			} catch (Exception e) {
				e.printStackTrace();
				
				modelMap.put("article", article);
				modelMap.put("result", "添加失败，服务器出错");
				return VIEW_ADMIN_ARTICLE_EDIT;
			}
		} else {
			Article oldArticle = articleService.getById(article.getId());
			oldArticle.setTitle(article.getTitle());
			oldArticle.setContent(article.getContent());
			oldArticle.setCategory(new ArticleCategory(categoryId));
			
			modelMap.put("article", oldArticle);
			
			try {
				articleService.update(oldArticle);
				
				modelMap.put("result", "保存成功！");
				return VIEW_ADMIN_ARTICLE_EDIT;
			} catch (Exception e) {
				articleService.update(oldArticle);
				modelMap.put("result", "保存失败");
				return VIEW_ADMIN_ARTICLE_EDIT;
			}
		}
		
	}
	
	@RequestMapping("article_delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam(value="id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		// TODO 安全验证
		try {
			articleService.deleteById(id);
			logger.info("[管理员{}]删除文章{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败");
		}
		
	}
}
