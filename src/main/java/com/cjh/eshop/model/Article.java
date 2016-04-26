package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @author 陈建杭
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 6873488197541051578L;

	private String id;

    private String title;

    private String content;

    private Date addTime;

    private ArticleCategory category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

}