package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Seller;
import com.cjh.eshop.service.common.IBaseService;

public interface ISellerService extends IBaseService<Seller, Integer> {
	public PageInfo<Seller> getAllByPage(int pageNo, int pageSize);
}
