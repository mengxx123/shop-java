package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.ISettingDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Setting;
import com.cjh.eshop.service.ISettingService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class SettingService extends BaseService<Setting, Integer> implements ISettingService {
	
	@Resource(name = "settingDao")
	private ISettingDao dao;

	@Override
	protected IBaseDao<Setting, Integer> getDao() {
		return dao;
	}

	@Override
	public String getValue(String key) {
		return dao.getByKey(key).getValue();
	}

	@Override
	public void updateByKey(String key, String value) {
		Setting setting = new Setting();
		setting.setKey(key);
		setting.setValue(value);
		dao.updateByKey(setting);
	}
}
