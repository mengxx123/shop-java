package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

public class ShopCollection implements Serializable {

	private static final long serialVersionUID = 730374386008424199L;

	private String id;
	private User user;
	private Shop shop;
	private Date createTime;

	public ShopCollection() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
