package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Brand;
import com.cjh.eshop.service.common.IBaseService;

public interface IBrandService extends IBaseService<Brand, String> {
	public PageInfo<Brand> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public PageInfo<Brand> getByExample(Brand brand, int pageNo, int pageSize);
}
