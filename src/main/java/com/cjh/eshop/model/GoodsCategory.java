package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类别
 * @author 陈建杭
 */
public class GoodsCategory implements Serializable {

	private static final long serialVersionUID = -4365158223908262795L;

	private String id;
    private String name;
    private String description;
    private GoodsCategory parent; // 父分类
    //private Integer grade; // 分类级别（1级分类、2级分类）
    private Integer sortOrder;
    private Date modifyTime;

    public GoodsCategory() {}
    
    public GoodsCategory(String id) {
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

	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

   
}