package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 * @author 陈建杭
 */
public class Manager implements Serializable {

	private static final long serialVersionUID = 3081885686612231887L;

	private String id;

    private String name;

    private String password; // MD5加密后的密码

    private String note; // 备注

    private String account;

    private Date addTime;
    private Date latestLoginTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLatestLoginTime() {
		return latestLoginTime;
	}

	public void setLatestLoginTime(Date latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}
}