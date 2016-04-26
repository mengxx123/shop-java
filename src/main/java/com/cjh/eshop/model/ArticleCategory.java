package com.cjh.eshop.model;

import java.io.Serializable;

/**
 * 文章类别
 * @author 陈建杭
 */
public class ArticleCategory implements Serializable {

	private static final long serialVersionUID = 6783505175141959681L;

	private String id;
    private String name;
    private String description;
    private ArticleCategory parent;
    private Integer sortOrder;
    
    public ArticleCategory() {}
    
    public ArticleCategory(String id) {
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

	public ArticleCategory getParent() {
		return parent;
	}

	public void setParent(ArticleCategory parent) {
		this.parent = parent;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}