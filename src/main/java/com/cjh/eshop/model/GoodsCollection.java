package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

public class GoodsCollection implements Serializable {

	private static final long serialVersionUID = 5440070702103824678L;
	private String id;
	private User user;
	private Goods goods;
	private Date addTime;

	public GoodsCollection() {
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
