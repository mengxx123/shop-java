package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsCategoryDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.service.IGoodsCategoryService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class GoodsCategoryService extends BaseService<GoodsCategory, String> implements IGoodsCategoryService {
	
	@Resource(name = "goodsCategoryDao")
	private IGoodsCategoryDao dao;

	@Override
	protected IBaseDao<GoodsCategory, String> getDao() {
		return dao;
	}

	@Override
	public List<GoodsCategory> getAllByKeyword(String keyword, int pageNo,
			int pageSize) {
		return dao.getAllByKeyword(keyword, pageNo, pageSize);
	}

	@Override
	public PageInfo<GoodsCategory> getAllByPage(int pageNo, int pageSize) {
		return dao.getAllByPage(pageNo, pageSize);
	}

	@Override
	public List<GoodsCategory> getAllByParentId(String parentId) {
		return dao.getAllByParentId(parentId);
	}
}
