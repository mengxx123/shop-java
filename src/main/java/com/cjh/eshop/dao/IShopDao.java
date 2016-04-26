package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Shop;

public interface IShopDao extends IBaseDao<Shop, String> {
	public PageInfo<Shop> getAllByPage(int pageNo, int pageSize);
	public Shop getByUserId(String id);
	public void deleteByShopId(String id);
}
