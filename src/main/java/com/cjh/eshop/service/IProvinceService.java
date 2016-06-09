package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.Province;
import com.cjh.eshop.service.common.IBaseService;

public interface IProvinceService extends IBaseService<Province, Integer> {
	public List<Province> getAll();
}
