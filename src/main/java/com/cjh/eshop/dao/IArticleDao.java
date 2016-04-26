package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Article;

public interface IArticleDao extends IBaseDao<Article, String> {
	public List<Article> getAllArticle(int page, int pageSize);
	public PageInfo<Article> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public PageInfo<Article> getAllByCategoryId(String categoryId, int pageNo, int pageSize);
}
