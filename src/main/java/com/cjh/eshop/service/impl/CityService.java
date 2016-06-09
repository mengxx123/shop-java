package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.ICityDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.City;
import com.cjh.eshop.service.ICityService;
import com.cjh.eshop.service.common.BaseService;

@Service("cityService")
@Transactional(rollbackFor = { Exception.class })
public class CityService extends BaseService<City, Integer> implements ICityService {
	
	@Resource(name = "cityDao")
	private ICityDao dao;

	@Override
	protected IBaseDao<City, Integer> getDao() {
		return dao;
	}

    @Override
    public List<City> getByProvinceCode(Integer code) {
        return dao.getByProvinceCode(code);
    }

}
