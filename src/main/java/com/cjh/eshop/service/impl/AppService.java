package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.IAppDao;
import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.App;
import com.cjh.eshop.service.IAppService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AppService extends BaseService<App, String> implements IAppService {
	
	@Resource(name = "appDao")
	private IAppDao dao;

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	@Override
	protected IBaseDao<App, String> getDao() {
		return dao;
	}
	
}
