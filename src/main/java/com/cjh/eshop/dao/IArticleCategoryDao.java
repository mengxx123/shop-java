package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.ArticleCategory;

public interface IArticleCategoryDao extends IBaseDao<ArticleCategory, String> {
	public List<ArticleCategory> getTopCategory();
	public PageInfo<ArticleCategory> getAllByPage(int pageNo, int pageSize);
	public List<ArticleCategory> getAll();
}
