package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户反馈
 * @author 陈建杭
 */
public class Feedback implements Serializable {

	private static final long serialVersionUID = -6638332556821915547L;

	private String id;
    private User user;
    private Integer type;
    private String content;
    private Date time;
    private Integer isRead;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

    
}