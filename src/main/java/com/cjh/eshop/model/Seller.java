package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家
 * @author 陈建杭
 */
public class Seller implements Serializable {

	private static final long serialVersionUID = 4353781585899291889L;

	private Integer id;

    private Shop shop;

    private Date addTime;

    private String account;

    private String password; // MD5加密后的密码

    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}