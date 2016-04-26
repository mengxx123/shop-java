package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.service.common.IBaseService;

public interface IArticleService extends IBaseService<Article, String> {
	public List<Article> getAll(int page, int pageSize);
	public List<Article> getAll();
	public PageInfo<Article> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public PageInfo<Article> getAllByCategoryId(String categoryId, int pageNo, int pageSize);
}
