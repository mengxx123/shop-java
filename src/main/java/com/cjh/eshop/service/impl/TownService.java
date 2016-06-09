package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.ITownDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Town;
import com.cjh.eshop.service.ITownService;
import com.cjh.eshop.service.common.BaseService;

@Service("townService")
@Transactional(rollbackFor = { Exception.class })
public class TownService extends BaseService<Town, Integer> implements ITownService {
	
	@Resource(name = "townDao")
	private ITownDao dao;

	@Override
	protected IBaseDao<Town, Integer> getDao() {
		return dao;
	}

    @Override
    public List<Town> getByCountyCode(Integer code) {
        return dao.getByCountyCode(code);
    }

}
