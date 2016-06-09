package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.ICountyDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.County;
import com.cjh.eshop.service.ICountyService;
import com.cjh.eshop.service.common.BaseService;

@Service("countyService")
@Transactional(rollbackFor = { Exception.class })
public class CountyService extends BaseService<County, Integer> implements ICountyService {
	
	@Resource(name = "countyDao")
	private ICountyDao dao;

	@Override
	protected IBaseDao<County, Integer> getDao() {
		return dao;
	}

    @Override
    public List<County> getByCityCode(Integer code) {
        return dao.getByCityCode(code);
    }

}
