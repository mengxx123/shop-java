package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Brand;

public interface IBrandDao extends IBaseDao<Brand, String> {
	public PageInfo<Brand> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public PageInfo<Brand> getAllByPage(int pageNo, int pageSize);
	public PageInfo<Brand> getByExample(Brand brand, int pageNo, int pageSize);
}
