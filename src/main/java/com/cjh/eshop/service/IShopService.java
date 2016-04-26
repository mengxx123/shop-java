package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Shop;
import com.cjh.eshop.service.common.IBaseService;

public interface IShopService extends IBaseService<Shop, String> {
	public PageInfo<Shop> getAllByPage(int pageNo, int pageSize);
	public Shop getShopByUserId(String uid);
}
