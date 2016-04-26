package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 * 
 * @author 陈建杭
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -2483360248727583737L;

	private String id;
	private String content;
	private User user;
	private User sendUser;
	private Date sendTime;
	private Integer isRead;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}