package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.ISellerDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Seller;
import com.cjh.eshop.service.ISellerService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class SellerService extends BaseService<Seller, Integer> implements ISellerService {
	
	@Resource(name = "sellerDao")
	private ISellerDao dao;

	@Override
	protected IBaseDao<Seller, Integer> getDao() {
		return dao;
	}

	@Override
	public PageInfo<Seller> getAllByPage(int pageNo, int pageSize) {
		return dao.getAllByPage(pageNo, pageSize);
	}
}
