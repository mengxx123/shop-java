package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.ShopCollection;

public interface IShopCollectionDao extends IBaseDao<ShopCollection, String> {
	public PageInfo<ShopCollection> getShopCollectionsByUserId(String id, int page, int pageSize);
}
