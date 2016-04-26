package com.cjh.eshop.controller.view;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.ArticleCategory;
import com.cjh.eshop.service.IArticleCategoryService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

@Controller
public class ArticleCategoryController extends BaseController {
	
	@Resource(name = "articleCategoryService")
	private IArticleCategoryService categoryService;

	private static final String VIEW_ADMIN_ARTICLE_CATEGORY = "article/article_category";
	private static final String VIEW_ADMIN_ARTICLE_CATEGORY_EDIT = "article/article_category_edit";

	@RequestMapping("admin/article_categorys")
	public String feedback(HttpSession session, HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value="keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		
		PageInfo<ArticleCategory> pageInfo = categoryService.getAllByPage(page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return VIEW_ADMIN_ARTICLE_CATEGORY;
	}
	
	@RequestMapping("admin/article_category_edit")
    public String brandEdit(ModelMap modelMap) {
		List<ArticleCategory> parents = categoryService.getTopCategory();
		modelMap.put("parents", parents);
		return VIEW_ADMIN_ARTICLE_CATEGORY_EDIT;	
	}
	
	@RequestMapping("admin/article_categorys/{id}")
    public String getArticleCategoryById(@PathVariable("id") String id, HttpServletRequest request, 
    		ModelMap modelMap) {
		ArticleCategory category = categoryService.getById(id);
		modelMap.put("category", category);
		List<ArticleCategory> parents = categoryService.getTopCategory();
		modelMap.put("parents", parents);
		return VIEW_ADMIN_ARTICLE_CATEGORY_EDIT;
	}
	
	@RequestMapping(value = "article_categorys", method = RequestMethod.POST)
	public String saveOrUpdateBrand(HttpServletRequest request, ArticleCategory category, ModelMap modelMap) {
		if (TextUtil.isEmpty(category.getName())) {
			modelMap.put("result", "名称不能为空");
			return VIEW_ADMIN_ARTICLE_CATEGORY_EDIT;
		}
		
		if (TextUtil.isEmpty(category.getId())) {
			System.out.println("父："+category.getParent());
			System.out.println("ID ：" + category.getParent().getId());
			categoryService.save(category);
			modelMap.put("category", category);
			modelMap.put("result", "添加成功");
			List<ArticleCategory> parents = categoryService.getTopCategory();
			modelMap.put("parents", parents);
			
			return VIEW_ADMIN_ARTICLE_CATEGORY_EDIT;
		} else {
			ArticleCategory oldCategory = categoryService.getById(category.getId());
			oldCategory.setName(category.getName());
			oldCategory.setDescription(category.getDescription());
			oldCategory.setParent(category.getParent());
			modelMap.put("category", category);
			categoryService.update(oldCategory);
			modelMap.put("result", "保存成功");
			List<ArticleCategory> parents = categoryService.getTopCategory();
			modelMap.put("parents", parents);
			return VIEW_ADMIN_ARTICLE_CATEGORY_EDIT;
		}
	}
	
	@RequestMapping(value = "article_category_delete", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam(value="id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		// TODO 安全验证
		try {
			categoryService.deleteById(id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败");
		}
		
	}
}
