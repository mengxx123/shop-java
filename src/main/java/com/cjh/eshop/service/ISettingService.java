package com.cjh.eshop.service;

import com.cjh.eshop.model.Setting;
import com.cjh.eshop.service.common.IBaseService;

public interface ISettingService extends IBaseService<Setting, Integer> {
	public String getValue(String key);
	public void updateByKey(String key, String value);
}
