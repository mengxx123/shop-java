package com.cjh.eshop.common;

import java.util.Hashtable;
import java.util.Map;

public class SettingHelper {
	
	private Map<String, Object> setting = new Hashtable<String, Object>();
	
	private static SettingHelper instance = new SettingHelper();
	
	private SettingHelper() {}
	
	public static SettingHelper getInstance() {
		return instance;
	}
	
	@SuppressWarnings("rawtypes")
    public Map getSetting() {
		return setting;
	}
	
	public void put(String key, Object value) {
		setting.put(key, value);
	}
	
}
