package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.impl.GoodsCategoryService;

@RestController
@RequestMapping("api/v1.0")
public class GoodsCategoryApiController {
	
	@Resource(name="goodsCategoryService")
	private GoodsCategoryService goodsCategoryService;
	
	@RequestMapping("categories")
	public Object getAllCategoriesByParentId(@RequestParam(value="parent_id", required = false) String parentId){
		if (parentId == null) {
			return goodsCategoryService.getAllByParentId(null); // TODO
		} else {
			return goodsCategoryService.getAllByParentId(parentId);
		}
	}

}
