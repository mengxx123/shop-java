package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.ShopCollection;
import com.cjh.eshop.service.common.IBaseService;

public interface IShopCollectionService extends IBaseService<ShopCollection, String> {
	public PageInfo<ShopCollection> getShopCollectionByUserId(String id, int page, int pageSize);
}
