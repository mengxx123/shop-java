package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.ArticleCategory;
import com.cjh.eshop.service.common.IBaseService;

public interface IArticleCategoryService extends IBaseService<ArticleCategory, String> {
	public List<ArticleCategory> getTopCategory();
	public PageInfo<ArticleCategory> getAllByPage(int pageNo, int pageSize);
	public List<ArticleCategory> getAll();
}
