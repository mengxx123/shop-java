package com.cjh.eshop.dao;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Setting;

public interface ISettingDao extends IBaseDao<Setting, Integer> {
	public Setting getByKey(String key);
	public void updateByKey(Setting setting);
}
