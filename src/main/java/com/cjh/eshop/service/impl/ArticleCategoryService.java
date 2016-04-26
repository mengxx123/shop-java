package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IArticleCategoryDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.ArticleCategory;
import com.cjh.eshop.service.IArticleCategoryService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class ArticleCategoryService extends BaseService<ArticleCategory, String> implements IArticleCategoryService {
	
	@Resource(name = "articleCategoryDao")
	private IArticleCategoryDao dao;

	@Override
	protected IBaseDao<ArticleCategory, String> getDao() {
		return dao;
	}

	@Override
	public List<ArticleCategory> getTopCategory() {
		return dao.getTopCategory();
	}

	@Override
	public PageInfo<ArticleCategory> getAllByPage(int pageNo, int pageSize) {
		return dao.getAllByPage(pageNo, pageSize);
	}

	@Override
	public List<ArticleCategory> getAll() {
		return dao.getAll();
	}
}
