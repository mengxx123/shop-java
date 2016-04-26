package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IShopDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Shop;
import com.cjh.eshop.service.IShopService;
import com.cjh.eshop.service.common.BaseService;

@Service("shopService")
@Transactional(rollbackFor = { Exception.class })
public class ShopService extends BaseService<Shop, String> implements IShopService {
	
	@Resource(name = "shopDao")
	private IShopDao dao;

	@Override
	protected IBaseDao<Shop, String> getDao() {
		return dao;
	}

	@Override
	public PageInfo<Shop> getAllByPage(int pageNo, int pageSize) {
		return dao.getAllByPage(pageNo, pageSize);
	}

	@Override
	public Shop getShopByUserId(String uid) {
		return dao.getByUserId(uid);
	}
	
	@Override
	public void deleteById(String id) {
		// TODO 删除店铺下的所有商品
		
		dao.deleteById(id);
	}
}
