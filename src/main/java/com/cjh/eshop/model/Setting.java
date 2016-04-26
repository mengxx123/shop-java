package com.cjh.eshop.model;

import java.io.Serializable;

/**
 * 设置
 * 
 * @author 陈建杭
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -8561699080871973286L;

	private Integer id;
	private String key;
	private String value;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}