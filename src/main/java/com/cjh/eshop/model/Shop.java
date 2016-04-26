package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺
 * @author 陈建杭
 */
public class Shop implements Serializable {

	private static final long serialVersionUID = -5654204582206059121L;

	private String id;
	private String name;
	private String description;
	private Date createTime;
	private User user;  // 店家

	public Shop() {}
	
	public Shop(String id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
