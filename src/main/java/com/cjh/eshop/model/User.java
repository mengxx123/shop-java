package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author 陈建杭
 */
public class User implements Serializable {

	private static final long serialVersionUID = 7707501765152021540L;

	private String id;
    private String email;
    private String name;
    private String password; // MD5加密后的密码
    private String nickname;
    private String headPath; // 头像路径
    private Integer isEmailValid;
    private Date registerTime;
    private String description;
    
    public User() {}
    
    public User(String id) {
    	this.id = id;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public Integer getIsEmailValid() {
		return isEmailValid;
	}

	public void setIsEmailValid(Integer isEmailValid) {
		this.isEmailValid = isEmailValid;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}