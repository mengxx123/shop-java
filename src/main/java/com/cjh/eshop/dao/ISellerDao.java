package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Seller;

public interface ISellerDao extends IBaseDao<Seller, Integer> {
	public PageInfo<Seller> getAllByPage(int pageNo, int pageSize);
}
