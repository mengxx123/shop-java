package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IShopCollectionDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.ShopCollection;
import com.cjh.eshop.service.IShopCollectionService;
import com.cjh.eshop.service.common.BaseService;

@Service("shopCollectionService")
@Transactional(rollbackFor = Exception.class)
public class ShopCollectionService extends BaseService<ShopCollection, String> implements IShopCollectionService {
	
	@Resource(name = "shopCollectionDao")
	private IShopCollectionDao dao;

	@Override
	public String save(ShopCollection entity) {
		return null;
	}

	@Override
	public void update(ShopCollection entity) {
	}

	@Override
	public PageInfo<ShopCollection> getShopCollectionByUserId(String id,
			int page, int pageSize) {
		return dao.getShopCollectionsByUserId(id, page, pageSize);
	}

	@Override
	protected IBaseDao<ShopCollection, String> getDao() {
		return dao;
	}
	
	 
}
