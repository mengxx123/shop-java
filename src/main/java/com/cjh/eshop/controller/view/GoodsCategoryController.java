package com.cjh.eshop.controller.view;

import java.util.Date;
import java.util.List;

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

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.service.IGoodsCategoryService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

@Controller
public class GoodsCategoryController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
	
	@Resource(name = "goodsCategoryService")
	private IGoodsCategoryService categoryService;

	private static final String VIEW_ADMIN_CATEGORY = "category/admin_category";
	private static final String VIEW_ADMIN_CATEGORY_EDIT = "category/category_edit";
	
	@RequestMapping("admin/category")
	public String feedback(HttpSession session, HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value="page", defaultValue = "1") Integer page,
			@RequestParam(value="keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		
		//Category category = categoryService.getById(3);
		//System.out.println(category.getName());
		
		PageInfo<GoodsCategory> pageInfo = categoryService.getAllByPage(page, DEFAULT_PAGE_SIZE);
		

		modelMap.put("page", pageInfo);
		
		return VIEW_ADMIN_CATEGORY;
	}
	
	@RequestMapping(value = "admin/category_edit")
    public String categoryEdit(ModelMap modelMap) {
		List<GoodsCategory> parents = categoryService.getAllByParentId(null);
		System.out.println(parents.size()+" ");
		modelMap.put("parents", parents);
		return VIEW_ADMIN_CATEGORY_EDIT;	
	}

	@RequestMapping("admin/category/{id}")
    public String getCategoryById(@PathVariable("id") String id, HttpServletRequest request, 
    		ModelMap modelMap) {
		GoodsCategory category = categoryService.getById(id);
		modelMap.put("category", category);
		
		List<GoodsCategory> parents = categoryService.getAllByParentId(null);
		modelMap.put("parents", parents);
		
		return VIEW_ADMIN_CATEGORY_EDIT;
	}
	
	@RequestMapping(value = "admin/category", method = RequestMethod.POST)
	public String saveOrUpdateBrand(HttpServletRequest request, GoodsCategory category, ModelMap modelMap) {
		if (TextUtil.isEmpty(category.getName())) {
			modelMap.put("result", "名称不能为空");
			return VIEW_ADMIN_CATEGORY_EDIT;
		}
		
		if (TextUtil.isEmpty(category.getId())) {
			System.out.println("父："+category.getParent());
			if (category.getParent().getId() == "0") { // 顶级分类 TODO
				category.setParent(null);
			}
			category.setSortOrder(50); // TODO 不写
			category.setModifyTime(new Date());
			categoryService.save(category);
			modelMap.put("category", category);
			modelMap.put("result", "添加成功");
			List<GoodsCategory> parents = categoryService.getAllByParentId(null); // TODO
			modelMap.put("parents", parents);
			
			return VIEW_ADMIN_CATEGORY_EDIT;
		} else {
			GoodsCategory oldCategory = categoryService.getById(category.getId());
			oldCategory.setName(category.getName());
			oldCategory.setDescription(category.getDescription());
			oldCategory.setParent(category.getParent());
			modelMap.put("category", category);
			categoryService.update(oldCategory);
			modelMap.put("result", "保存成功");
			List<GoodsCategory> parents = categoryService.getAllByParentId(null);
			modelMap.put("parents", parents);
			return VIEW_ADMIN_CATEGORY_EDIT;
		}
	}
	
	@RequestMapping(value = "category_delete", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam(value="id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		// TODO 安全验证 没有权限时写入日志
		try {
			categoryService.deleteById(id);
			logger.info("[管理员{}]删除商品类别{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "删除失败");
		}
		
	}
	
}
