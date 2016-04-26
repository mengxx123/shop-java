package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.IArticleService;


/**
 * 店铺相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
@RequestMapping("api/v1.0")
public class ArticleApiController {

	@Resource(name = "articleService")
	private IArticleService articleService;
	
	@RequestMapping("articles/{id}")
	public Object getArticle(@PathVariable("id") String id) {
		return articleService.getById(id);
	}
	
	@RequestMapping("articles")
	public Object getArticles(@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value="cat_id", required = false) String categoryId) {
		
		final int DEFAULT_PAGE_SIZE = 10;
		if (categoryId == null) {
			return articleService.getAll();
		} else {
			return articleService.getAllByCategoryId(categoryId, page, DEFAULT_PAGE_SIZE);
		}
		
	}
}
