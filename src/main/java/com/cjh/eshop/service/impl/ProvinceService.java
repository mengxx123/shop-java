package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.IProvinceDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Province;
import com.cjh.eshop.service.IProvinceService;
import com.cjh.eshop.service.common.BaseService;

@Service("provinceService")
@Transactional(rollbackFor = { Exception.class })
public class ProvinceService extends BaseService<Province, Integer> implements IProvinceService {
	
	@Resource(name = "provinceDao")
	private IProvinceDao dao;

	@Override
	protected IBaseDao<Province, Integer> getDao() {
		return dao;
	}

	@Override
	public List<Province> getAll() {
		return dao.getAll();
	}

}
