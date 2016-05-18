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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Brand;
import com.cjh.eshop.service.IBrandService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 品牌管理相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class BrandController extends BaseController {
	
	@Resource(name = "brandService")
	private IBrandService brandService;
	
	private static final String URL_ADMIN_BRAND = "brand/admin_brand";
	private static final String URL_ADMIN_BRAND_EDIT = "brand/brand_edit";
	
	private static Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	// 后台品牌管理页面
	@RequestMapping("admin/brands")
	public String brand(HttpSession session, HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		
		Brand example = new Brand();
		example.setName(keyword);
		
		PageInfo<Brand> pageInfo = brandService.getByExample(example, page, DEFAULT_PAGE_SIZE);
		//PageInfo<Brand> pageInfo = brandService.getAllByKeyword(keyword, page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);

		return URL_ADMIN_BRAND;
	}
	
	// 品牌删除接口
	@RequestMapping("brand_delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam(value="id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		// TODO 安全验证
		try {
			brandService.deleteById(id);
			logger.info("[管理员{}]删除品牌{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}
		
	}
	
	// 添加品牌页面
	@RequestMapping("admin/brand_edit")
    public String brandEdit(HttpServletRequest request, 
    		ModelMap modelMap) {
		return URL_ADMIN_BRAND_EDIT;	
	}
	
	// 品牌添加/修改接口
	@RequestMapping(value = "brands", method = RequestMethod.POST)
	public String saveOrUpdateBrand(HttpServletRequest request, Brand brand, ModelMap modelMap,
			@RequestParam(value = "myfile", required = false) CommonsMultipartFile[] files) {
		if (TextUtil.isEmpty(brand.getName())) {
			modelMap.put("result", "品牌名称不能为空");
			return URL_ADMIN_BRAND_EDIT;
		}
		
		if (TextUtil.isEmpty(brand.getId())) {
			String result = dealFile1(request, modelMap, "images/brand");
			if (result != null) {
				brand.setIcon(result);
				System.out.println("上传图标");
			} else {
				brand.setIcon("");
				System.out.println("空空空");
			}
			
			brand.setAddTime(new Date());
			
			brandService.save(brand);
			modelMap.put("brand", brand);
			modelMap.put("result", "添加成功");
			return URL_ADMIN_BRAND_EDIT;
		} else {
			Brand oldBrand = brandService.getById(brand.getId());
			oldBrand.setName(brand.getName());
			String result = dealFile1(request, modelMap, "images/brand");
			if (result != null) {
				oldBrand.setIcon(result);
				System.out.println("上传图标");
			} else {
				brand.setIcon("");
				System.out.println("空空空");
			}
			
			oldBrand.setAddTime(new Date());
			
			
			brandService.update(oldBrand);
			modelMap.put("brand", oldBrand);
			System.out.println(brand.getName());
			modelMap.put("result", "保存成功");
			return URL_ADMIN_BRAND_EDIT;
		}
	}
	
	// 品牌修改页面
	@RequestMapping("admin/brand/{id}")
    public String getBrandById(@PathVariable("id") String brandId, HttpServletRequest request, 
    		ModelMap modelMap) {
		Brand brand = brandService.getById(brandId);
		modelMap.put("brand", brand);
		return URL_ADMIN_BRAND_EDIT;
	}
}