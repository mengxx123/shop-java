package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品品牌
 * @author 陈建杭
 */
public class Brand implements Serializable {

	private static final long serialVersionUID = 4149326267562174816L;

	private String id;
    private String name; // 品牌名称
    private String icon; // 品牌图标资源路径
    private Date addTime;

    public Brand() {}
    
    public Brand(String id) {
    	this.id = id;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}